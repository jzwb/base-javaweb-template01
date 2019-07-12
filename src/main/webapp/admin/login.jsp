<%@ page import="com.jzwb.util.SettingUtils" %>
<%@ page import="java.util.UUID" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String base = request.getContextPath();
    String staticUrl = SettingUtils.get().getStaticUrl();
    String captchaId = UUID.randomUUID().toString();
%>
<!DOCTYPE html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="<%=staticUrl%>/resources/admin/materialize/css/materialize.css"  />
    <script type="text/javascript" src="<%=staticUrl%>/resources/admin/materialize/js/materialize.js" ></script>
</head>
<body>
    <div class="container">
        <div class="row">
            <form class="col s12">
                <div class="row">
                    <div class="input-field col s12">
                        <input id="username" type="text" class="validate">
                        <label for="username">用户名</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <input id="password" type="password" class="validate">
                        <label for="password">密码</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s10">
                        <input id="captcha" type="text" class="validate">
                        <label for="captcha">验证码</label>
                    </div>
                    <div class="input-field col s2">
                        <img height="35" src="<%=base%>/admin/common/captcha/?captchaId=<%=captchaId%>">
                    </div>
                </div>
                <button class="btn waves-effect waves-light" type="submit" name="action">登陆</button>
            </form>
        </div>
    </div>
</body>
</html>
