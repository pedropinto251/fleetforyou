package com.fleetforyou.fleetforyou.Domain.DTO.User;

import com.fleetforyou.fleetforyou.Domain.Enums.Permission;
import com.fleetforyou.fleetforyou.Domain.Models.Stand;

import java.util.Set;

public record UserDTO(int id_user, String name, String phone_number, String address, String postal_code, String local, String district, String email, String password, Stand stand, Set<Permission> permissions, boolean deleted) {
    public static UserDTOBuilder builder(){return new UserDTOBuilder();}

    public static class UserDTOBuilder{
        private int id_user;
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

        UserDTOBuilder() {
        }

        public UserDTOBuilder id_user(int id_user){
            this.id_user = id_user;
            return this;
        }

        public UserDTOBuilder name(String name){
            this.name = name;
            return this;
        }

        public UserDTOBuilder phone_number(String phone_number){
            this.phone_number = phone_number;
            return this;
        }

        public UserDTOBuilder address(String address){
            this.address = address;
            return this;
        }

        public UserDTOBuilder postal_code(String postal_code){
            this.postal_code = postal_code;
            return this;
        }

        public UserDTOBuilder local(String local){
            this.local = local;
            return this;
        }

        public UserDTOBuilder district(String district){
            this.district = district;
            return this;
        }

        public UserDTOBuilder email(String email){
            this.email = email;
            return this;
        }

        public UserDTOBuilder password(String password){
            this.password = password;
            return this;
        }

        public UserDTOBuilder stand(Stand stand){
            this.stand = stand;
            return this;
        }

        public UserDTOBuilder permissions(Set<Permission> permissions){
            this.permissions = permissions;
            return this;
        }

        public UserDTOBuilder deleted(boolean deleted){
            this.deleted = deleted;
            return this;
        }

        public UserDTO build() {
            return new UserDTO(this.id_user, this.name, this.phone_number, this.address, this.postal_code, this.local, this.district, this.email, this.password, this.stand, this.permissions, this.deleted);
        }

        public String toString() {
            return "UserDTO.UserDTOBuilder(id_user=" + this.id_user + ", name=" + this.name + ", phone_number=" + this.phone_number + ", address=" + this.address + ", postal_code=" + this.postal_code + ", local=" + this.local + ", district=" + this.district + ", email=" + this.email + ", password=" + this.password + ", stand=" + this.stand + ", permissions=" + this.permissions +", deleted=" + this.deleted + ")";
        }
    }
}
