<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

<link href="webjars/bootstrap/4.0.0/css/bootstrap.min.css"
        rel="stylesheet">
<title>register to nateg</title></head>
<body background="https://hdqwalls.com/download/material-design-4k-2048x1152.jpg">
<div class="container ">

<div class="card text-center w-175 border-dark  bg-light mb-3">
<div class="card-header">
    Join us 
  </div>
  
<form action="addform" method="post">
  <div class="card-body">
  <div class="form-group">
    <label for="exampleInputEmail1">name</label>
    <input type="text" name="name" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter name">
    </div>
  <div class="form-group">
    <label for="exampleInputPassword1">familyname</label>
    <input type="text" name="familyname" class="form-control" id="exampleInputPassword1" placeholder="enter family Name">
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">email</label>
    <input type="text" name="email" class="form-control" id="exampleInputPassword1" placeholder="enter email">
  	<small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
  
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">why do you want to join NATEG</label>
    <input type="text" name="why" class="form-control" id="exampleInputPassword1" placeholder="enter you answer">
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">do you have any previous volenteering experience</label>
    <input type="text" name="experience" class="form-control" id="exampleInputPassword1" placeholder="enter your answer">
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">do you have any comments</label>
    <input type="text" name="comment" class="form-control" id="exampleInputPassword1" placeholder="enter your answer">
  </div>
  
  </div>
  <div class="card-footer text-muted">
  <input type="submit" class="btn btn-primary">
  </div>
</form>
</div>
</div>

</body>
<script src="webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>

<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
</html>