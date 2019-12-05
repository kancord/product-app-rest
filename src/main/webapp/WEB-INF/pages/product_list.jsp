<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products</title>
    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen"/>
    <script src="/static/jquery-3.4.1.min.js"></script>
    <script src="/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<script>
    $(document).ready(function () {
        $.ajax({
            type: 'GET',
            url: 'api/product'
        }).done(function (data, textStatus, jqXHR) {
            $.each(data, function (i, val) {
                $('#divProductList ul').append('<li><a href="/product/' + val.id + '">' + val.productName + '</a></li>');
            });
            // window.location.replace("/product");
        }).fail(function (jqXHR, textStatus, errorThrown) {
            alert('Error!');
        });

    });
</script>

<div class="container">
    <nav class="navbar navbar-light sticky-top bg-light navbar-expand-md">
        <div class="col-md-2 pl-2 pr-2">
            <span class="navbar-brand">productREST</span>
        </div>
        <div class="collapse navbar-collapse col-md-3">
            <ul class="nav navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/product">Products</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/product/add">Add product</a>
                </li>
            </ul>
        </div>
        <div class="col-md-3"><h2>Product list</h2></div>
    </nav>
    <div class="row">
        <%--<div id="divNewProduct col-md-2">--%>
        <%--<a href="/product/add" class="btn btn-primary btn-sm">Add new product</a>--%>
        <%--</div>--%>
        <div class="col-md-4 offset-md-5">

            <div id="divProductList">
                <ul></ul>
            </div>

        </div>
    </div>
</div>

</body>
</html>
