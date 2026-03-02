package com.fleetforyou.fleetforyou.Domain.Models;

import java.util.Objects;

public class Form {
    private int id_form;
    private int satisfaction;
    private String observation;
    private Rental rental;

    public Form(int id_form, int satisfaction, String observation, Rental rental){
        this.id_form = id_form;
        this.satisfaction = satisfaction;
        this.observation = observation;
        this.rental = rental;
    }

    public Form(){
    }

    public int getId_form() {
        return id_form;
    }

    public void setId_form(int id_form) {
        this.id_form = id_form;
    }

    public int getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(int satisfaction) {
        this.satisfaction = satisfaction;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Form form = (Form) o;
        return Objects.equals(id_form, form.id_form) && Objects.equals(satisfaction, form.satisfaction) && Objects.equals(observation, form.observation) && Objects.equals(rental, form.rental);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_form, satisfaction, observation, rental);
    }

    @Override
    public String toString() {
        return "Form{" + '\n' +
                "id_form=" + id_form + '\n' +
                "satisfaction=" + satisfaction + '\n' +
                "observation=" + observation + '\n' +
                "rental=" + rental + '\n' +
                '}';
    }
}
