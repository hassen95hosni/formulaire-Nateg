<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html><body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">Navbar</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
      <li class="nav-item active">
        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="main">main</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="ajouter">add covoiturage</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="mescovoiturage">mes covoiturages</a>
      </li>
      <li class="nav-item">
        <a class="nav-link disabled" href="logout">logout</a>
      </li>
    </ul>
  </div>
</nav>
<form action="ajouter" method="post">
  <div class="form-group">
    <label for="example1">depart</label>
    <input type="text" class="form-control" id="example1" aria-describedby="emailHelp" placeholder="Depart">
    </div>
  <div class="form-group">
    <label for="example2">destination</label>
    <input type="text" name="destination" class="form-control" id="example2" placeholder="Destination">
  </div>
  <div class="form-group">
    <label for="example3">heure</label>
    <input type="text" name="heure" class="form-control" id="example3" placeholder="heure">
  </div>
  <div class="form-group">
    <label for="example4">nombre de place</label>
    <input type="number" name="nombredeplace"class="form-control" id="example4" placeholder="Ex: 2">
  </div>
  <div class="form-group">
    <label for="example5">description</label>
    <input type="text" name="description" class="form-control" id="example5" placeholder="Description">
  </div>
  
  <a href="/main">login</a>
  <input type="submit" class="btn btn-primary">Submit</input>
</form>
</body></html>