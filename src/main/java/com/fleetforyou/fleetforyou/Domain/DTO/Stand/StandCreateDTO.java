package com.fleetforyou.fleetforyou.Domain.DTO.Stand;

public record StandCreateDTO(String name, String phone_number, String address, String postalCode, String local, String district) {
    public static StandCreateDTOBuilder builder() {
        return new StandCreateDTOBuilder();
    }

    public static class StandCreateDTOBuilder {
        private String name;
        private String phone_number;
        private String address;
        private String postalCode;
        private String local;
        private String district;

        public StandCreateDTOBuilder() {
        }

        public StandCreateDTOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public StandCreateDTOBuilder phone_number(String phone_number) {
            this.phone_number = phone_number;
            return this;
        }

        public StandCreateDTOBuilder address(String address) {
            this.address = address;
            return this;
        }

        public StandCreateDTOBuilder postalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public StandCreateDTOBuilder local(String local) {
            this.local = local;
            return this;
        }

        public StandCreateDTOBuilder district(String district) {
            this.district = district;
            return this;
        }

        public StandCreateDTO build() {
            return new StandCreateDTO(this.name, this.phone_number, this.address, this.postalCode, this.local, this.district);
        }

        public String toString() {
            return "StandCreateDTO.StandCreateDTOBuilder(name=" + this.name + ", phone_number=" + this.phone_number + ", address=" + this.address + ", postalCode=" + this.postalCode + ", local=" + this.local + ", district=" + this.district + ")";
        }
    }
}
