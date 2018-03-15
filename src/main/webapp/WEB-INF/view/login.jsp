<%--
  Created by IntelliJ IDEA.
  User: tanghaibin
  Date: 2018/3/15
  Time: 下午4:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆认证</title>
    <script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script type="text/javascript">
        function login() {
            var params = {username:null, password: null};
            params.username = $("#username").val();
            params.password = $("#password").val();
            $.ajax({
               url: '/sso/loginCheck',
               type: 'POST',
               data:params,
               success: function(response) {
                   if(response.code == 200) {
                       window.location = "http://localhost:8084/test/index";
                   } else {
                       $("#error").val(response.msg);
                   }
               }
            });
        }
    </script>
</head>
<body>
    错误信息<input id="error" placeholder="这里是错误信息" /><br/>
    <input id="username" placeholder="用户名" /><br/>
    <input id="password" placeholder="密码" /><br/>
    <button onclick="login()" >登陆</button>
</body>
</html>
