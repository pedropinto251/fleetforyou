package com.fleetforyou.fleetforyou.Domain.Models;

import java.util.Date;
import java.util.Objects;

public class Rental {
    private int id_rental;
    private Date date_start;
    private Date date_end;
    private Date date_return;
    private Vehicle vehicle;
    private Client client;
    private User user;
    private String vehicle_status;

    public Rental(int id_rental, Date date_start, Date date_end, Date date_return, Vehicle vehicle, Client client, User user, String vehicle_status){
        this.id_rental = id_rental;
        this.date_start = date_start;
        this.date_end = date_end;
        this.date_return = date_return;
        this.vehicle = vehicle;
        this.client = client;
        this.user = user;
        this.vehicle_status = vehicle_status;
    }

    public Rental(){
    }

    public int getId_rental() {
        return id_rental;
    }

    public void setId_rental(int id_rental) {
        this.id_rental = id_rental;
    }

    public Date getDate_start() {
        return date_start;
    }

    public void setDate_start(Date date_start) {
        this.date_start = date_start;
    }

    public Date getDate_end() {
        return date_end;
    }

    public void setDate_end(Date date_end) {
        this.date_end = date_end;
    }

    public Date getDate_return() {
        return date_return;
    }

    public void setDate_return(Date date_return) {
        this.date_return = date_return;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getVehicleStatus() {
        return vehicle_status;
    }

    public void setVehicleStatus(String vehicle_status) {
        this.vehicle_status = vehicle_status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rental rental = (Rental) o;
        return Objects.equals(id_rental, rental.id_rental) && Objects.equals(date_start, rental.date_start) && Objects.equals(date_end, rental.date_end) && Objects.equals(date_return, rental.date_return) && Objects.equals(vehicle, rental.vehicle) && Objects.equals(client, rental.client) && Objects.equals(user, rental.user) && Objects.equals(vehicle_status, rental.vehicle_status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_rental, date_start, date_end, date_return, vehicle, client, user, vehicle_status);
    }

    @Override
    public String toString() {
        return "Rental{" + '\n' +
                "id_rental=" + id_rental + '\n' +
                "date_start=" + date_start + '\n' +
                "date_end=" + date_end + '\n' +
                "date_return=" + date_return + '\n' +
                "vehicle=" + vehicle + '\n' +
                "client=" + client + '\n' +
                "user=" + user + '\n' +
                "vehicle_status=" + vehicle_status + '\n' +
                '}';
    }
}
