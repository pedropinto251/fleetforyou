package com.fleetforyou.fleetforyou.Domain.DTO.User;

public record UserLoginDTO(String email, String password) {
    public static UserLoginDTOBuilder builder() {
        return new UserLoginDTOBuilder();
    }

    public static class UserLoginDTOBuilder {
        private String email;
        private String password;

        UserLoginDTOBuilder() {
        }

        public UserLoginDTOBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserLoginDTOBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserLoginDTO build() {
            return new UserLoginDTO(this.email, this.password);
        }

        public String toString() {
            return "UserLoginDTO.UserLoginDTOBuilder(email=" + this.email + ", password=" + this.password + ")";
        }
    }
}
