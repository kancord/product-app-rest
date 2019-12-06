<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product</title>
    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen"/>
    <script src="/static/jquery-3.4.1.min.js"></script>
    <script src="/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<script>
    $(document).ready(function () {
        var productById = ${productById};
        if (productById) {
            $.ajax({
                type: 'GET',
                url: '/api/products/' + ${productId}
            }).done(function (data) {
                $("#name").val(data.productName);
                $("#price").val(data.price);
            }).fail(function () {
                alert('Error!');
            });
        }

        $("#saveForm").submit(function (event) {
            event.preventDefault();
            if (productById) {
                $.ajax({
                    type: 'PUT',
                    contentType: 'application/json',
                    data: JSON.stringify({
                        'productName': $("#name").val(),
                        'price': $("#price").val()
                    }),
                    url: '/api/products/' + ${productId}
                }).done(function (data) {
                    if (data) {
                        // alert('Saved');
                        window.location.href = "/products/" + ${productId};
                    } else {
                        alert('Wrong data!')
                    }
                }).fail(function () {
                    alert('Error!');
                });
            } else {
                $.ajax({
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify({
                        'productName': $("#name").val(),
                        'price': $("#price").val()
                    }),
                    url: '/api/products'
                }).done(function (data) {
                    if (data) {
                        window.location.href = "/products";
                    } else {
                        alert('Wrong data!')
                    }
                }).fail(function () {
                    alert('Error!');
                });
            }
        });

        $("#deleteButton").on("click", function (event) {
            event.preventDefault();
            $.ajax({
                type: 'DELETE',
                url: '/api/products/' + ${productId}
            }).done(function (data) {
                if (data) {
                    window.location.replace("/products")
                } else {
                    alert('Error!')
                }
            }).fail(function () {
                alert('Error!');
            });
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
                    <a class="nav-link" href="/products">Products</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/products/add">Add product</a>
                </li>
            </ul>
        </div>
        <div class="col-md-3">
            <h2>
                <c:if test="${productById == true}">Product</c:if>
                <c:if test="${productById == false}">Add product</c:if>
            </h2>
        </div>
    </nav>
    <div class="row">
        <div class="col-md-4 offset-md-5">
            <div class="container " id="divProduct">
                <form id="saveForm">
                    <div class="row">
                        <div class="form-group">
                            <label for="name">Product name</label>
                            <input type="text" id="name" class="form-control" required/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label for="price">Product price</label>
                            <input type="number" id="price" min="1" max="5000" class="form-control" required/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="pl-1 pr-2">
                            <button id="saveButton" type="submit" class="btn btn-success btn-sm">Save</button>
                        </div>
                        <c:if test="${productById == true}">
                            <div class="pl-2 pr-1">
                                <button id="deleteButton" class="btn btn-danger btn-sm">Delete</button>
                            </div>
                        </c:if>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
