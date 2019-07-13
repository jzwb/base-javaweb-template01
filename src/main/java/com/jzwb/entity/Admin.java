package com.jzwb.entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity - 管理员
 */
@Entity
@Table(name = "t_admin")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_admin_sequence")
public class Admin extends BaseEntity {

	//管理员登录状况
	public static final String ADMIN_LOGIN_STATUS = "adminLoginStatus";

	//用户名
	private String username;
	//密码
	private String password;
	//邮箱
	private String email;
	//手机号码
	private String mobile;
	//姓名
	private String name;
	//是否启用
	private Boolean isEnabled = Boolean.FALSE;
	//是否锁定
	private Boolean isLocked = Boolean.FALSE;
	//登陆失败次数
	private Integer loginFailCount = 0;
	//锁定时间
	private Date lockedDate;
	//最后登陆时间
	private Date loginDate;
	//最后登陆IP
	private String loginIp;
	//角色
	private Set<Role> roles = new HashSet<>();

	@NotEmpty
	@Length(min = 6, max = 20)
	@Column(nullable = false, updatable = false, unique = true)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@NotEmpty
	@Length(min = 6, max = 20)
	@Column(nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Email
	@Length(max = 200)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Length(max = 200)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Length(max = 200)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotNull
	@Column(nullable = false)
	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	@NotNull
	@Column(nullable = false)
	public Boolean getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}

	@NotNull
	@Min(0)
	@Column(nullable = false)
	public Integer getLoginFailCount() {
		return loginFailCount;
	}

	public void setLoginFailCount(Integer loginFailCount) {
		this.loginFailCount = loginFailCount;
	}

	public Date getLockedDate() {
		return lockedDate;
	}

	public void setLockedDate(Date lockedDate) {
		this.lockedDate = lockedDate;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	@NotEmpty
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "t_admin_role")
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}