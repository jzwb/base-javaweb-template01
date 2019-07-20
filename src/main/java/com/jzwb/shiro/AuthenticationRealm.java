package com.jzwb.shiro;

import com.jzwb.common.Principal;
import com.jzwb.common.Setting;
import com.jzwb.component.CaptchaUtils;
import com.jzwb.entity.Admin;
import com.jzwb.service.AdminService;
import com.jzwb.util.SettingUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 权限认证
 */
public class AuthenticationRealm extends AuthorizingRealm {

	@Resource(name = "adminServiceImpl")
	private AdminService adminService;

	/**
	 * 获取认证信息
	 *
	 * @param token 令牌
	 * @return 认证信息
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken token) {
		AuthenticationToken authenticationToken = (AuthenticationToken) token;
		String username = authenticationToken.getUsername();
		String password = authenticationToken.getPassword() != null ? new String(authenticationToken.getPassword()) : null;
		boolean passCaptcha = authenticationToken.getPassCaptcha();
		String ip = authenticationToken.getHost();
		if (!passCaptcha) {
			throw new UnsupportedTokenException();
		}
		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			throw new UnknownAccountException();
		}
		Admin admin = adminService.findByUsername(username);
		if (admin == null) {
			throw new UnknownAccountException();
		}
		if (!admin.getIsEnabled()) {
			throw new DisabledAccountException();
		}
		//账号锁定处理
		Setting setting = SettingUtils.get();
		if (admin.getIsLocked()) {
			if (ArrayUtils.contains(setting.getAccountLockTypes(), Setting.AccountLockType.admin)) {
				int accountLockTime = setting.getAccountLockTime();
				if (accountLockTime == 0) {
					throw new LockedAccountException();
				}
				Date lockedDate = admin.getLockedDate();
				Date unlockDate = DateUtils.addMinutes(lockedDate, accountLockTime);
				//当前日期超过解锁时间，则账号进行解锁
				if (new Date().after(unlockDate)) {
					admin.setLoginFailCount(0);
					admin.setIsLocked(false);
					admin.setLockedDate(null);
					adminService.update(admin);
				} else {
					throw new LockedAccountException();
				}
			} else {
				admin.setLoginFailCount(0);
				admin.setIsLocked(false);
				admin.setLockedDate(null);
				adminService.update(admin);
			}
		}
		//账号密码校验
		if (!DigestUtils.md5Hex(password).equals(admin.getPassword())) {
			int loginFailureCount = admin.getLoginFailCount() + 1;
			if (loginFailureCount >= setting.getAccountLockCount()) {
				admin.setIsLocked(true);
				admin.setLockedDate(new Date());
			}
			admin.setLoginFailCount(loginFailureCount);
			adminService.update(admin);
			throw new IncorrectCredentialsException();
		}
		admin.setLoginIp(ip);
		admin.setLoginDate(new Date());
		admin.setLoginFailCount(0);
		adminService.update(admin);
		return new SimpleAuthenticationInfo(new Principal(admin.getId(), admin.getUsername()), password, getName());
	}

	/**
	 * 获取授权信息
	 *
	 * @param principals 身份信息
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Principal principal = (Principal) principals.fromRealm(getName()).iterator().next();
		if (principal == null) {
			return null;
		}
		List<String> authorities = adminService.findAuthoritiesById(principal.getId());
		if (authorities == null) {
			return null;
		}
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.addStringPermissions(authorities);
		return authorizationInfo;
	}
}