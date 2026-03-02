package com.fleetforyou.fleetforyou.Domain.DTO.User;

import com.fleetforyou.fleetforyou.Domain.Enums.Permission;
import com.fleetforyou.fleetforyou.Domain.Models.User;

import java.util.Set;

public record UserLoggedDTO(int id_user, String name, String email, Set<Permission> permissions) {
    public static UserLoggedDTOBuilder builder(){return new UserLoggedDTOBuilder();}

    public static class UserLoggedDTOBuilder{
        private int id_user;
        private String name;
        private String email;
        private Set<Permission> permissions;

        UserLoggedDTOBuilder(){

        }

        public UserLoggedDTOBuilder id_user(int id_user){
            this.id_user = id_user;
            return this;
        }

        public UserLoggedDTOBuilder name(String name){
            this.name = name;
            return this;
        }

        public UserLoggedDTOBuilder email(String email){
            this.email = email;
            return this;
        }

        public UserLoggedDTOBuilder permissions(Set<Permission> permissions){
            this.permissions = permissions;
            return this;
        }

        public UserLoggedDTO build() {
            return new UserLoggedDTO(this.id_user, this.name, this.email, this.permissions);
        }

        public String toString() {
            return "UserLoggedDTO.UserLoggedDTOBuilder(id_user=" + this.id_user + ", name=" + this.name + ", email=" + this.email + ", permissions=" + this.permissions + ")";
        }
    }
}
