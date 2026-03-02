package com.fleetforyou.fleetforyou.Domain.DTO.Vehicle;

public record VehicleCreateDTO(String registration, String brand, String model, String segment, String fuel, int year_built, String num_doors, int num_km, int engine_capacity, int potency, String color, String type,
                               double selling_price, double rental_price, boolean sold) {
    public static VehicleCreateDTOBuilder builder() {
        return new VehicleCreateDTOBuilder();
    }

    public static class VehicleCreateDTOBuilder {
        private String registration;
        private String brand;
        private String model;
        private String segment;
        private String fuel;
        private int year_built;
        private String num_doors;
        private int num_km;
        private int engine_capacity;
        private int potency;
        private String color;
        private String type;
        private double selling_price;
        private double rental_price;
        private boolean sold;

        VehicleCreateDTOBuilder() {
        }

        public VehicleCreateDTOBuilder registration(String registration) {
            this.registration = registration;
            return this;
        }

        public VehicleCreateDTOBuilder brand(String brand) {
            this.brand = brand;
            return this;
        }

        public VehicleCreateDTOBuilder model(String model) {
            this.model = model;
            return this;
        }

        public VehicleCreateDTOBuilder segment(String segment) {
            this.segment = segment;
            return this;
        }

        public VehicleCreateDTOBuilder fuel(String fuel) {
            this.fuel = fuel;
            return this;
        }

        public VehicleCreateDTOBuilder year_built(int year_built) {
            this.year_built = year_built;
            return this;
        }

        public VehicleCreateDTOBuilder num_doors(String num_doors) {
            this.num_doors = num_doors;
            return this;
        }

        public VehicleCreateDTOBuilder num_km(int num_km) {
            this.num_km = num_km;
            return this;
        }

        public VehicleCreateDTOBuilder engine_capacity(int engine_capacity) {
            this.engine_capacity = engine_capacity;
            return this;
        }

        public VehicleCreateDTOBuilder potency(int potency) {
            this.potency = potency;
            return this;
        }

        public VehicleCreateDTOBuilder color(String color) {
            this.color = color;
            return this;
        }
        public VehicleCreateDTOBuilder type(String type) {
            this.type = type;
            return this;
        }
        public VehicleCreateDTOBuilder selling_price (double selling_price) {
            this.selling_price = selling_price;
            return this;
        }
        public VehicleCreateDTOBuilder price (double rental_price) {
            this.rental_price = rental_price;
            return this;
        }

        public VehicleCreateDTOBuilder sold (boolean sold) {
            this.sold = sold;
            return this;
        }

        public VehicleCreateDTO build() {
            return new VehicleCreateDTO(this.registration, this.brand, this.model, this.segment, this.fuel, this.year_built, this.num_doors, this.num_km, this.engine_capacity, this.potency, this.color, this.type, this.selling_price, this.rental_price, this.sold);
        }


        public String toString() {
            return "VehicleCreateDTO.VehicleCreateDTOBuilder(registration=" + this.registration + ", brand=" + this.brand + ", model=" + this.model + ", segment=" + this.segment + ", fuel=" + this.fuel + ", year_built=" + this.year_built + ", num_doors=" + this.num_doors + ", num_km=" + this.num_km + ", engine_capacity=" + this.engine_capacity + ", potency=" + this.potency + ", color=" + this.color + ", type=" + this.type + " , selling_price=" + this.selling_price + " , price=" + this.rental_price + " , sold=" + this.sold +")";
        }
    }
}
