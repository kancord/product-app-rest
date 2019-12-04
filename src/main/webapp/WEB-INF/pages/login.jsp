<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen"/>
    <script src="/static/jquery-3.4.1.min.js"></script>
    <script src="/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<script>
    $(document).ready(function () {
        $('#loginform').submit(function (event) {
            event.preventDefault();
            var data = 'username=' + $('#username').val() + '&password=' + $('#password').val();
            $.ajax({
                data: data,
                timeout: 1000,
                type: 'POST',
                url: '/login'
            }).done(function(data, textStatus, jqXHR) {
                window.location.replace("/product");
            }).fail(function(jqXHR, textStatus, errorThrown) {
                alert('Error, try again!');
            });
        });
    });
</script>
<div class="container">
    <div class="row">
        <div class="col-md-4 offset-md-4">
            <h2 class="text-center">Login</h2>
            <form id="loginform" name="loginform">
                <input type="text" name="username" id="username" class="form-control" placeholder="username"/>
                <input type="password" name="password" id="password" class="form-control" placeholder="password"/>
                <div class="text-center pt-2">
                    <button type="submit" class="btn btn-success" id="loginButton">Submit</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
