<!DOCTYPE html>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head><title>
people that the warning email was sent to theme
</title>

<link href="webjars/bootstrap/4.0.0/css/bootstrap.min.css"
        rel="stylesheet">

</head>
<body>

<nav class="navbar navbar-expand-lg  navbar-dark bg-primary">
  <a class="navbar-brand" href="/main">NATEG</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
      <li class="nav-item ">
        <a class="nav-link" href="/main">Home </a>
      </li>
       <li class="nav-item">
        <a class="nav-link" href="main">main</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="didntpay">outdatedlist</a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="/warning"><span class="sr-only">(current)</span>yet</a>
      </li>
      <li class="nav-item">
        <a class="nav-link " href="logout">logout</a>
      </li>
    </ul>
  </div>
</nav>
<c:choose>
 <c:when test="true">

<table class="table table-striped ">
  <thead class="thead-light">
    <tr>
      <th scope="col">#</th>
      <th scope="col">Name</th>
      <th scope="col">Family Name</th>
     <th scope="col">email</th>
      <th scope="col">why do you want to join</th>
      <th scope="col">previous volenteering experience</th>
      <th scope="col">comments</th>
      <th scope="col">date</th>
      <th scope="col">warning sent</th>
      <th scope="col">final state</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${list}" var="d">
    <tr>
      <th scope="row"></th>
      <td>${d.name }</td>
      <td>${d.familyName }</td>
      <td>${d.email}</td>
      <td>${d.whyDoyouWwannajoinNateg}</td>
      <td>${d.volenteeringeExperience}</td>
      <td>${d.comment}</td>
      <td>${d.date}</td>
      <td>${d.state}</td>
      <td>${d.finalstate}</td>
    </tr>

  </c:forEach>

    
  </tbody>
</table>
<a class="btn btn-primary btn-lg active" href="/dayspass">download list</a>
       
    </c:when>    
    <c:otherwise>
        
<h1>no one to show</h1>        
    </c:otherwise>
</c:choose>
</body>

<script src="webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>

<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
</html>