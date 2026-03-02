package com.fleetforyou.fleetforyou.Domain.DTO.Stand;

import com.fleetforyou.fleetforyou.Domain.DTO.User.UserDTO;
import com.fleetforyou.fleetforyou.Domain.Enums.Permission;
import com.fleetforyou.fleetforyou.Domain.Models.Stand;

import java.util.Set;

public record StandDTO(int id_stand, String name, String phone_number, String address, String postalCode, String local, String district, boolean deleted) {
    public static StandDTOBuilder builder(){return new StandDTOBuilder();}

    public static class StandDTOBuilder{
        private int id_stand;
        private String name;
        private String phone_number;
        private String address;
        private String postalCode;
        private String local;
        private String district;
        private boolean deleted;

        StandDTOBuilder() {
        }

        public StandDTOBuilder id_stand(int id_stand){
            this.id_stand = id_stand;
            return this;
        }

        public StandDTOBuilder name(String name){
            this.name = name;
            return this;
        }

        public StandDTOBuilder phone_number(String phone_number){
            this.phone_number = phone_number;
            return this;
        }

        public StandDTOBuilder address(String address){
            this.address = address;
            return this;
        }

        public StandDTOBuilder postalCode(String postalCode){
            this.postalCode = postalCode;
            return this;
        }

        public StandDTOBuilder local(String local){
            this.local = local;
            return this;
        }

        public StandDTOBuilder district(String district){
            this.district = district;
            return this;
        }

        public StandDTOBuilder deleted(boolean deleted){
            this.deleted = deleted;
            return this;
        }

        public StandDTO build() {
            return new StandDTO(this.id_stand, this.name, this.phone_number, this.address, this.postalCode, this.local, this.district, this.deleted);
        }

        public String toString() {
            return "StandDTO.StandDTOBuilder(id_stand=" + this.id_stand + ", name=" + this.name + ", phone_number=" + this.phone_number + ", address=" + this.address + ", postalCode=" + this.postalCode + ", local=" + this.local + ", district=" + this.district + ", deleted=" + this.deleted + ")";
        }
    }
}
