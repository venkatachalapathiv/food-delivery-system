package com.jsp.foodapp.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;

@Entity
public class FoodOrder {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;
private String name;
private long mobno;
private double totalbill;
@OneToMany
@Cascade(org.hibernate.annotations.CascadeType.ALL)
private List<Item> item;
@ManyToOne
private User user;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public long getMobno() {
	return mobno;
}
public void setMobno(long mobno) {
	this.mobno = mobno;
}

public List<Item> getItem() {
	return item;
}
public void setItem(List<Item> item) {
	this.item = item;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public double getTotalbill() {
	return totalbill;
}
public void setTotalbill(double totalbill) {
	this.totalbill = totalbill;
}



}
