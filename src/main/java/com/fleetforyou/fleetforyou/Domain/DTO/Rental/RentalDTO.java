package com.fleetforyou.fleetforyou.Domain.DTO.Rental;

import com.fleetforyou.fleetforyou.Domain.Models.Client;
import com.fleetforyou.fleetforyou.Domain.Models.User;
import com.fleetforyou.fleetforyou.Domain.Models.Vehicle;

import java.util.Date;

public record RentalDTO(int id_rental, Date date_start, Date date_end, Date date_return, Vehicle vehicle, Client client, User user, String vehicle_status) {
    public static RentalDTOBuilder builder() {
        return new RentalDTOBuilder();
    }

    public static class RentalDTOBuilder {
        private int id_rental;
        private Date date_start;
        private Date date_end;
        private Date date_return;
        private Vehicle vehicle;
        private Client client;
        private User user;
        private String vehicle_status;

        public RentalDTOBuilder rental_id_rental (int id_rental) {
            this.id_rental = id_rental;
            return this;
        }
        public RentalDTOBuilder rental_date_start (Date date_start) {
            this.date_start = date_start;
            return this;
        }
        public RentalDTOBuilder rental_date_end (Date date_end) {
            this.date_end = date_end;
            return this;
        }

        public RentalDTOBuilder renta_date_return (Date date_return) {
            this.date_return = date_return;
            return this;
        }

        public RentalDTOBuilder rental_vehicle (Vehicle vehicle) {
            this.vehicle = vehicle;
            return this;
        }

        public RentalDTOBuilder rental_client(Client client) {
            this.client = client;
            return this;
        }

        public RentalDTOBuilder rental_user (User user) {
            this.user = user;
            return this;
        }

        public RentalDTOBuilder vehicle_status (String vehicle_status) {
            this.vehicle_status = vehicle_status;
            return this;
        }

        public RentalDTO build() {
            return new RentalDTO(this.id_rental,this.date_start, this.date_end, this.date_return, this.vehicle, this.client, this.user, this.vehicle_status);
        }
        public String toString() {
            return "RentalDTO.RentalDTOBuilder(id_rental=" + this.id_rental + ", Date Start=" + this.date_start + ", Date End=" + this.date_end + ", Date return=" + this.date_return + ", Vehicle=" + this.vehicle + ", Client=" + this.client + ", User=" + this.user + ", VehicleStatus=" + this.vehicle_status + ")";
        }

        RentalDTOBuilder() {
        }
    }
}

