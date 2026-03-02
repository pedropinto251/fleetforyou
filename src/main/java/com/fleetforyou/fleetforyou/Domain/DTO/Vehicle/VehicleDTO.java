package com.fleetforyou.fleetforyou.Domain.DTO.Vehicle;

public record VehicleDTO(int id_vehicle, String registration, String brand, String model, String segment, boolean status, String fuel, int year_built, String num_doors, int num_km, int engine_capacity, int potency, String color, double rental_price, String type, boolean deleted,
                         double selling_price, boolean sold) {



    public static VehicleDTOBuilder builder() {
        return new VehicleDTOBuilder();
    }

    public static class VehicleDTOBuilder {
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

        VehicleDTOBuilder() {
        }

        public VehicleDTOBuilder id_vehicle(int id_vehicle) {
            this.id_vehicle = id_vehicle;
            return this;
        }

        public VehicleDTOBuilder registration(String registration) {
            this.registration = registration;
            return this;
        }

        public VehicleDTOBuilder brand(String brand) {
            this.brand = brand;
            return this;
        }

        public VehicleDTOBuilder model(String model) {
            this.model = model;
            return this;
        }

        public VehicleDTOBuilder segment(String segment) {
            this.segment = segment;
            return this;
        }

        public VehicleDTOBuilder status(boolean status) {
            this.status = status;
            return this;
        }

        public VehicleDTOBuilder fuel(String fuel) {
            this.fuel = fuel;
            return this;
        }

        public VehicleDTOBuilder year_built(int year_built) {
            this.year_built = year_built;
            return this;
        }

        public VehicleDTOBuilder num_doors(String num_doors) {
            this.num_doors = num_doors;
            return this;
        }

        public VehicleDTOBuilder num_km(int num_km) {
            this.num_km = num_km;
            return this;
        }

        public VehicleDTOBuilder engine_capacity(int engine_capacity) {
            this.engine_capacity = engine_capacity;
            return this;
        }

        public VehicleDTOBuilder potency(int potency) {
            this.potency = potency;
            return this;
        }

        public VehicleDTOBuilder color(String color) {
            this.color = color;
            return this;
        }

        public VehicleDTOBuilder rental_price(double rental_price) {
            this.rental_price = rental_price;
            return this;
        }

        public VehicleDTOBuilder type(String type) {
            this.type = type;
            return this;
        }

        public VehicleDTOBuilder deleted(boolean deleted){
            this.deleted = deleted;
            return this;
        }

        public VehicleDTO build() {
            return new VehicleDTO(this.id_vehicle, this.registration, this.brand, this.model, this.segment, this.status, this.fuel, this.year_built, this.num_doors, this.num_km, this.engine_capacity, this.potency, this.color, this.rental_price, this.type, this.deleted, this.selling_price ,this.sold);
        }
        public VehicleDTOBuilder selling_price (double selling_price) {
            this.selling_price = selling_price;
            return this;
        }

        public VehicleDTOBuilder sold (boolean sold) {
            this.sold = sold;
            return this;
        }


        public String toString() {
            return "VehicleDTO.VehicleDTOBuilder(id_vehicle =" + this.id_vehicle + "registration=" + this.registration + ", brand=" + this.brand + ", model=" + this.model + ", segment=" + this.segment + ", status=" + this.status + ", fuel=" + this.fuel + ", year_built=" + this.year_built + ", num_doors=" + this.num_doors + ", num_km=" + this.num_km + ", engine_capacity=" + this.engine_capacity + ", potency=" + this.potency + ", color=" + this.color + ", rental_price=" + this.rental_price + ", type=" + this.type + ", deleted=" + this.deleted + " , selling_price=" + this.selling_price + " , sold=" + this.sold + ")";
        }
    }
}
