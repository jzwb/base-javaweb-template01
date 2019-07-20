<%@page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page import="com.jzwb.util.SettingUtils" %>
<%@ page import="org.apache.commons.lang.ArrayUtils" %>
<%@ page import="com.jzwb.common.Setting" %>
<%@ page import="java.util.UUID" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="com.jzwb.component.SpringUtils" %>
<%@ page import="com.jzwb.component.RSAUtils" %>
<%@ page import="java.security.interfaces.RSAPublicKey" %>
<%@ page import="org.apache.commons.codec.binary.Base64" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter" %>
<%
    String base = request.getContextPath();
	String staticUrl = SettingUtils.get().getStaticUrl();
	Setting setting = SettingUtils.get();
	String captchaId = UUID.randomUUID().toString();
    String loginFailure = null;
    String modulus = null;
    String exponent = null;
    ApplicationContext applicationContext = SpringUtils.getApplicationContext();
    if (applicationContext != null) {
        RSAUtils rsaUtils = SpringUtils.getBean("rsaUtils", RSAUtils.class);
        RSAPublicKey publicKey = rsaUtils.generateKey(request);
        modulus = Base64.encodeBase64String(publicKey.getModulus().toByteArray());
        exponent = Base64.encodeBase64String(publicKey.getPublicExponent().toByteArray());
        loginFailure = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
        System.out.println(loginFailure);
    }
%>
<shiro:authenticated>
    <%
        if (applicationContext != null) {
            response.sendRedirect(base + "/admin/common/index/");
        }
    %>
</shiro:authenticated>
<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>Admin 系统 | 登陆</title>
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	<link rel="stylesheet" href="<%=staticUrl%>/resources/admin/components/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="<%=staticUrl%>/resources/admin/components/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="<%=staticUrl%>/resources/admin/components/Ionicons/css/ionicons.min.css">
	<link rel="stylesheet" href="<%=staticUrl%>/resources/admin/adminLTE/css/AdminLTE.min.css">
	<link rel="stylesheet" href="<%=staticUrl%>/resources/admin/plugins/iCheck/square/blue.css">
	<link rel="stylesheet" href="<%=staticUrl%>/resources/admin/common/css/google_font.css">
</head>

<body class="hold-transition login-page">
<div class="login-box">
	<div class="login-logo">
		<a href="index.html"><b>Admin</b> 系统</a>
	</div>
	<div class="login-box-body">
		<p class="login-box-msg"></p>
		<form id="loginForm" action="login.jsp" method="post">
			<input type="hidden" name="enPassword" />
			<%if (ArrayUtils.contains(setting.getCaptchaTypes(), Setting.CaptchaType.adminLogin)) {%>
			<input type="hidden" name="captchaId" value="<%=captchaId%>" />
			<%}%>
			<div class="form-group has-feedback">
				<input name="username" type="text" class="form-control" placeholder="账号">
				<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
			</div>
			<div class="form-group has-feedback">
				<input name="password" type="password" class="form-control" placeholder="密码">
				<span class="glyphicon glyphicon-lock form-control-feedback"></span>
			</div>
			<div class="form-group has-feedback">
				<input name="captcha" type="text" class="form-control" placeholder="验证码">
                <img src="<%=base%>/admin/common/captcha/?captchaId=<%=captchaId%>" title="" />
			</div>
			<div class="row">
				<div class="col-xs-8">
					<div class="checkbox icheck">
						<label>
							<input type="checkbox"> 记住我
						</label>
					</div>
				</div>
				<div class="col-xs-4">
					<button type="submit" class="btn btn-primary btn-block btn-flat">登陆</button>
				</div>
			</div>
		</form>
	</div>
</div>
<script src="<%=staticUrl%>/resources/admin/components/jquery/jquery.min.js"></script>
<script src="<%=staticUrl%>/resources/admin/components/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=staticUrl%>/resources/admin/plugins/iCheck/icheck.min.js"></script>
<script src="<%=staticUrl%>/resources/admin/common/js/jsbn.js"></script>
<script src="<%=staticUrl%>/resources/admin/common/js/prng4.js"></script>
<script src="<%=staticUrl%>/resources/admin/common/js/rng.js"></script>
<script src="<%=staticUrl%>/resources/admin/common/js/rsa.js"></script>
<script src="<%=staticUrl%>/resources/admin/common/js/base64.js"></script>
<script>
    $(function() {
        $('input').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%'
        });

        var $loginForm = $("#loginForm");
        var $password = $("input[name='password']");
        var $enPassword = $("input[name='enPassword']");
        $loginForm.submit(function(){
            var rsaKey = new RSAKey();
            rsaKey.setPublic(b64tohex("<%=modulus%>"), b64tohex("<%=exponent%>"));
            var enPassword = hex2b64(rsaKey.encrypt($password.val()));
            $enPassword.val(enPassword);
        });
    });
</script>
</body>

</html>