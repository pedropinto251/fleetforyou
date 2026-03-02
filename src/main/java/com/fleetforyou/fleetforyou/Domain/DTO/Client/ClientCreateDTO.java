package com.fleetforyou.fleetforyou.Domain.DTO.Client;

import com.fleetforyou.fleetforyou.Domain.DTO.User.UserDTO;
import com.fleetforyou.fleetforyou.Domain.Enums.Permission;
import com.fleetforyou.fleetforyou.Domain.Models.Stand;

import java.util.Set;

public record ClientCreateDTO(String name, String nc, String phone_number, String address, String postalCode, String local, String district) {
    public static ClientCreateDTOBuilder builder(){return new ClientCreateDTOBuilder();}

    public static class ClientCreateDTOBuilder{
        private String name;
        private String nc;
        private String phone_number;
        private String address;
        private String postalCode;
        private String local;
        private String district;

        ClientCreateDTOBuilder() {
        }

        public ClientCreateDTOBuilder name(String name){
            this.name = name;
            return this;
        }

        public ClientCreateDTOBuilder nc(String nc){
            this.nc = nc;
            return this;
        }

        public ClientCreateDTOBuilder phone_number(String phone_number){
            this.phone_number = phone_number;
            return this;
        }

        public ClientCreateDTOBuilder address(String address){
            this.address = address;
            return this;
        }

        public ClientCreateDTOBuilder postalCode(String postalCode){
            this.postalCode = postalCode;
            return this;
        }

        public ClientCreateDTOBuilder local(String local){
            this.local = local;
            return this;
        }

        public ClientCreateDTOBuilder district(String district){
            this.district = district;
            return this;
        }

        public ClientCreateDTO build() {
            return new ClientCreateDTO(this.name, this.nc, this.phone_number, this.address, this.postalCode, this.local, this.district);
        }

        public String toString() {
            return "ClientCreateDTO.ClientCreateDTOBuilder(name=" + this.name + ", nc=" + this.nc + ", phone_number=" + this.phone_number + ", address=" + this.address + ", postalCode=" + this.postalCode + ", local=" + this.local + ", district=" + this.district + ")";
        }
    }
}
