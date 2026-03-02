package com.fleetforyou.fleetforyou.Domain.DTO.Rental;

import com.fleetforyou.fleetforyou.Domain.DTO.Stand.StandCreateDTO;
import com.fleetforyou.fleetforyou.Domain.Models.Client;
import com.fleetforyou.fleetforyou.Domain.Models.User;
import com.fleetforyou.fleetforyou.Domain.Models.Vehicle;

import java.util.Date;

public record RentalCreateDTO(Date date_start, Date date_end, Date date_return, Vehicle vehicle, Client client, User user, String vehicle_status, double rental_price) {
    public static RentalCreateDTOBuilder builder() {
        return new RentalCreateDTOBuilder();
    }

    public static class RentalCreateDTOBuilder {
        private Date date_start;
        private Date date_end;
        private Date date_return;
        private Vehicle vehicle;
        private Client client;
        private User user;
        private String vehicle_status;

        private double rental_price;



        public RentalCreateDTOBuilder rental_date_start (Date date_start) {
            this.date_start = date_start;
            return this;
        }
        public RentalCreateDTOBuilder rental_date_end (Date date_end) {
            this.date_end = date_end;
            return this;
        }

        public RentalCreateDTOBuilder rental_date_return (Date date_return) {
            this.date_return = date_return;
            return this;
        }

        public RentalCreateDTOBuilder rental_vehicle (Vehicle vehicle) {
            this.vehicle = vehicle;
            return this;
        }

        public RentalCreateDTOBuilder rental_client(Client client) {
            this.client = client;
            return this;
        }

        public RentalCreateDTOBuilder rental_user (User user) {
            this.user = user;
            return this;
        }

        public RentalCreateDTOBuilder vehicle_status (String vehicle_status) {
            this.vehicle_status = vehicle_status;
            return this;
        }

        public RentalCreateDTOBuilder rental_price(double rental_price) {
            this.rental_price = rental_price;
            return this;
        }

        public RentalCreateDTO build() {
            return new RentalCreateDTO(this.date_start, this.date_end, this.date_return, this.vehicle, this.client, this.user, this.vehicle_status, this.rental_price);
        }
        public String toString() {
            return "RentalCreateDTO.RentalCreateDTOBuilder(Date Start=" + this.date_start + ", Date End=" + this.date_end + ", Date return=" + this.date_return + ", Vehicle=" + this.vehicle + ", Client=" + this.client + ", User=" + this.user + ", Vehicle Status=" + this.vehicle_status + ", Rental Price=" + this.rental_price + ")";
        }

        RentalCreateDTOBuilder() {
        }
    }
}

