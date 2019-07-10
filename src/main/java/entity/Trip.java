package entity;

import java.util.List;

public class Trip {
public String id ;
public String depart ;
public String destination ;
public String date ;
public String heuredepart;
public int nombreDeplace;
public int nombreDePlaceDisponible;
public List<String> passenger=null;

public List<String> getPassenger() {
	return passenger;
}
public void setPassenger(List<String> passenger) {
	this.passenger = passenger;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getDepart() {
	return depart;
}
public void setDepart(String depart) {
	this.depart = depart;
}
public String getDestination() {
	return destination;
}
public void setDestination(String destination) {
	this.destination = destination;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String getHeuredepart() {
	return heuredepart;
}
public void setHeuredepart(String heuredepart) {
	this.heuredepart = heuredepart;
}
public int getNombreDeplace() {
	return nombreDeplace;
}
public void setNombreDeplace(int nombreDeplace) {
	this.nombreDeplace = nombreDeplace;
}
public int getNombreDePlaceDisponible() {
	return nombreDePlaceDisponible;
}
public void setNombreDePlaceDisponible(int nombreDePlaceDisponible) {
	this.nombreDePlaceDisponible = nombreDePlaceDisponible;
}

}
