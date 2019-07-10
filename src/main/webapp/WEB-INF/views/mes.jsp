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
        <a class="nav-link" href="main">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="main">main</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="ajouterpage">add covoiturage</a>
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
<table class="table">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Dipart</th>
      <th scope="col">Destination</th>
      <th scope="col">heure</th>
      <th scope="col">Nbr Places</th>
      <th scope="col">Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">1</th>
      <td>Tunis</td>
      <td>Sousse</td>
      <td>10.00</td>
      <td>2</td>
      <td>Voiture confort</td>
    </tr>
  </tbody>
</table>
</body></html>