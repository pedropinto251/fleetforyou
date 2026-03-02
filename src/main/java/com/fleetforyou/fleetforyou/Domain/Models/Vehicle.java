package com.fleetforyou.fleetforyou.Domain.Models;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

public class Vehicle {
    private int id_vehicle;
    private String registration;
    private String brand;
    private String model;
    private String segment;
    private boolean status;
    private String fuel;
    private int year_built;
    private String num_doors;
    private int num_km;
    private int engine_capacity;
    private int potency;
    private String color;
    private double rental_price;
    private String type;
    private boolean deleted;
    private double selling_price;
    private boolean sold;


    public Vehicle(int id_vehicle, String registration, String brand, String model, String segment, boolean status, String fuel, int year_built, String num_doors, int num_km, int engine_capacity, int potency, String color, double rental_price, String type, boolean deleted,double selling_price,boolean sold){
        this.id_vehicle = id_vehicle;
        this.registration = registration;
        this.brand = brand;
        this.model = model;
        this.segment = segment;
        this.status = status; //TODO definir o status para que se o veiculo não for usado o status fique como novo;
        this.fuel = fuel;
        this.year_built = year_built;
        this.num_doors = num_doors;
        this.num_km = num_km;
        this.engine_capacity = engine_capacity;
        this.potency = potency;
        this.color = color;
        this.rental_price = rental_price;
        this.type = type;
        this.deleted = deleted;
        this.selling_price = selling_price;
        this.sold = sold;

    }

    public Vehicle(){
    }

    public int getId_vehicle() {
        return id_vehicle;
    }

    public void setId_vehicle(int id_vehicle) {
        this.id_vehicle = id_vehicle;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public int getYear_built() {
        return year_built;
    }

    public void setYear_built(int year_built) {
        this.year_built = year_built;
    }

    public String getNum_doors() {
        return num_doors;
    }

    public void setNum_doors(String num_doors) {
        this.num_doors = num_doors;
    }

    public int getNum_km() {
        return num_km;
    }

    public void setNum_km(int num_km) {
        this.num_km = num_km;
    }

    public int getEngine_capacity() {
        return engine_capacity;
    }

    public void setEngine_capacity(int engine_capacity) {
        this.engine_capacity = engine_capacity;
    }

    public int getPotency() {
        return potency;
    }

    public void setPotency(int potency) {
        this.potency = potency;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getRental_price() {
        return rental_price;
    }

    public void setRental_price(double rental_price) {
        this.rental_price = rental_price;
    }

    public String getType (){
        return type;
    }

    public void setType (String type){
        this.type = type;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public double getSelling_price() {
        return selling_price;
    }

    public void setSelling_price(double selling_price) {
        this.selling_price = selling_price;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(id_vehicle, vehicle.id_vehicle) && Objects.equals(registration, vehicle.registration) && Objects.equals(brand, vehicle.brand) && Objects.equals(model, vehicle.model) && Objects.equals(segment, vehicle.segment) && Objects.equals(status, vehicle.status) && Objects.equals(fuel, vehicle.fuel) && Objects.equals(year_built, vehicle.year_built) && Objects.equals(num_doors, vehicle.num_doors) && Objects.equals(num_km, vehicle.num_km) && Objects.equals(engine_capacity, vehicle.engine_capacity) && Objects.equals(potency, vehicle.potency) && Objects.equals(color, vehicle.color) && Objects.equals(rental_price, vehicle.rental_price) && Objects.equals(type,vehicle.type) && Objects.equals(deleted, vehicle.deleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_vehicle, registration, brand, model, segment, status, fuel, year_built, num_doors, num_km, engine_capacity, potency, color, rental_price, type, deleted);
    }

    @Override
    public String toString() {
        return "Vehicle{" + '\n' +
                "id_vehicle=" + id_vehicle + '\n' +
                "registration=" + registration + '\n' +
                "brand=" + brand + '\n' +
                "model=" + model + '\n' +
                "segment=" + segment + '\n' +
                "status=" + status + '\n' +
                "fuel=" + fuel + '\n' +
                "year_built=" + year_built + '\n' +
                "num_doors=" + num_doors + '\n' +
                "num_km=" + num_km + '\n' +
                "engine_capacity=" + engine_capacity + '\n' +
                "potency=" + potency + '\n' +
                "color=" + color + '\n' +
                "rental_price=" + rental_price + '\n' +
                "type='" + type + '\n' +
                "deleted=" + deleted + '\n' +
                "selling_price=" + selling_price +
                ", sold=" + sold +
                '}';
    }
}
