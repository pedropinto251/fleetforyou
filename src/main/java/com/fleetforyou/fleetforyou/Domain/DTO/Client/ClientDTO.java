package com.fleetforyou.fleetforyou.Domain.DTO.Client;

import com.fleetforyou.fleetforyou.Domain.DTO.User.UserDTO;
import com.fleetforyou.fleetforyou.Domain.Enums.Permission;
import com.fleetforyou.fleetforyou.Domain.Models.Stand;

import java.util.Set;

public record ClientDTO(int id, String name, String nc, String phone_number, String address, String postalCode, String local, String district, boolean deleted) {
    public static ClientDTOBuilder builder(){return new ClientDTOBuilder();}

    public static class ClientDTOBuilder{
        private int id;
        private String name;
        private String nc;
        private String phone_number;
        private String address;
        private String postalCode;
        private String local;
        private String district;
        private boolean deleted;

        ClientDTOBuilder() {
        }

        public ClientDTOBuilder id(int id){
            this.id = id;
            return this;
        }

        public ClientDTOBuilder name(String name){
            this.name = name;
            return this;
        }

        public ClientDTOBuilder nc(String nc){
            this.nc = nc;
            return this;
        }

        public ClientDTOBuilder phone_number(String phone_number){
            this.phone_number = phone_number;
            return this;
        }

        public ClientDTOBuilder address(String address){
            this.address = address;
            return this;
        }

        public ClientDTOBuilder postalCode(String postalCode){
            this.postalCode = postalCode;
            return this;
        }

        public ClientDTOBuilder local(String local){
            this.local = local;
            return this;
        }

        public ClientDTOBuilder district(String district){
            this.district = district;
            return this;
        }

        public ClientDTOBuilder deleted(boolean deleted){
            this.deleted = deleted;
            return this;
        }

        public ClientDTO build() {
            return new ClientDTO(this.id, this.name, this.nc, this.phone_number, this.address, this.postalCode, this.local, this.district, this.deleted);
        }

        public String toString() {
            return "ClientDTO.ClientDTOBuilder(id=" + this.id + ", name=" + this.name + ", nc=" + this.nc + ", phone_number=" + this.phone_number + ", address=" + this.address + ", postalCode=" + this.postalCode + ", local=" + this.local + ", district=" + this.district + ", deleted=" + this.deleted + ")";
        }
    }
}
