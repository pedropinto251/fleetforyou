package com.fleetforyou.fleetforyou.Domain.DTO.User;

import com.fleetforyou.fleetforyou.Domain.Enums.Permission;
import com.fleetforyou.fleetforyou.Domain.Models.Stand;

import java.util.Set;

public record UserRegisterDTO(String name, String phone_number, String address, String postal_code, String local, String district, String email, String password, Set<Permission> permissions, Stand stand, boolean deleted) {
    public static UserRegisterDTOBuilder builder() {
        return new UserRegisterDTOBuilder();
    }

    public static class UserRegisterDTOBuilder {
        private String name;
        private String phone_number;
        private String address;
        private String postal_code;
        private String local;
        private String district;
        private String email;
        private String password;
        private Stand stand;
        private Set<Permission> permissions;
        private boolean deleted;

        UserRegisterDTOBuilder() {
        }

        public UserRegisterDTOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserRegisterDTOBuilder phone_number(String phone_number){
            this.phone_number = phone_number;
            return this;
        }

        public UserRegisterDTOBuilder address(String address){
            this.address = address;
            return this;
        }

        public UserRegisterDTOBuilder postal_code(String postal_code){
            this.postal_code = postal_code;
            return this;
        }

        public UserRegisterDTOBuilder local(String local){
            this.local = local;
            return this;
        }

        public UserRegisterDTOBuilder district(String district){
            this.district = district;
            return this;
        }

        public UserRegisterDTOBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserRegisterDTOBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserRegisterDTOBuilder permissions(Set<Permission> permissions) {
            this.permissions = permissions;
            return this;
        }

        public UserRegisterDTOBuilder stand(Stand stand) {
            this.stand = stand;
            return this;
        }

        public UserRegisterDTOBuilder deleted(boolean deleted){
            this.deleted = deleted;
            return this;
        }

        public UserRegisterDTO build() {
            return new UserRegisterDTO(this.name, this.phone_number, this.address, this.postal_code, this.local, this.district, this.email, this.password, this.permissions, this.stand, this.deleted);
        }

        public String toString() {
            return "UserRegisterDTO.UserRegisterDTOBuilder(name=" + this.name + ", phone_number=" + this.phone_number + ", address=" + this.address + ", postal_code=" + this.postal_code + ", local=" + this.local + ", district=" + this.district + ", email=" + this.email + ", password=" + this.password + ", permissions=" + this.permissions + ", stand=" + this.stand + ", deleted=" + this.deleted + ")";
        }
    }
}
