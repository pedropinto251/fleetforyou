package com.fleetforyou.fleetforyou.Domain.Models;

import com.fleetforyou.fleetforyou.Domain.Enums.Permission;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class User {
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

    /**
     * Constrói um Utilizador
     *
     * @param id_user                      O identificador único do utilizador.
     * @param name                         O nome do utilizador.
     * @param phone_number                 O numero de telefone do utilizador.
     * @param address                      A morada do utilizador.
     * @param postal_code                  O código-postal do utilizador.
     * @param local                        A localidade do utilizador.
     * @param district                     O distrito do utilizador.
     * @param email                        O email do utilizador.
     * @param password                     A password do utilizador.
     * @param stand                        O stand do utilizador.
     * @param permissions                  O conjunto de funções (permissions) atribuídas ao utilizador.
     */
    public User(int id_user, String name, String phone_number, String address, String postal_code, String local, String district, String email, String password, Stand stand, Set<Permission> permissions, boolean deleted) {
        this.id_user = id_user;
        this.name = name;
        this.phone_number = phone_number;
        this.address = address;
        this.postal_code = postal_code;
        this.local = local;
        this.district = district;
        this.email = email;
        this.password = password;
        this.stand = stand;
        this.permissions = permissions;
        this.deleted = deleted;
    }

    public User(String name, String phone_number, String address, String postal_code, String local, String district, String email, String password, Stand stand, Set<Permission> permissions) {
        this.name = name;
        this.phone_number = phone_number;
        this.address = address;
        this.postal_code = postal_code;
        this.local = local;
        this.district = district;
        this.email = email;
        this.password = password;
        this.stand = stand;
        this.permissions = permissions;
    }

    public User() {
    }

    public static UserBuilder userBuilder() {
        return new UserBuilder();
    }

    public void addPermission(Permission permission) {
        if (permissions == null) {
            permissions = new HashSet<>();
            permissions.add(permission);
        }
        permissions.add(permission);
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Stand getStand() {
        return stand;
    }

    public void setStand(Stand stand) {
        this.stand = stand;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id_user, user.id_user) && Objects.equals(name, user.name) && Objects.equals(phone_number, user.phone_number) && Objects.equals(address, user.address) && Objects.equals(postal_code, user.postal_code) && Objects.equals(local, user.local) && Objects.equals(district, user.district) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(stand, user.stand) && Objects.equals(permissions, user.permissions) && Objects.equals(deleted, user.deleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_user, name, phone_number, address, postal_code, local, district, email, password, stand, permissions, deleted);
    }

    public String toString() {
        return "User(id_user=" + this.getId_user() + ", name=" + this.getName() + ", phone_number=" + this.getPhone_number() + ", address=" + this.getAddress() + ", postal_code=" + this.getPostal_code() + ", local=" + this.getLocal() + ", district=" + this.getDistrict() + ", email=" + this.getEmail() + ", password=" + this.getPassword() + ", stand=" + this.getStand() + ",permissions=" + this.getPermissions() + ", deleted=" + this.isDeleted() + ")";
    }

    public static class UserBuilder {
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

        UserBuilder() {
        }

        public UserBuilder id_user(int id_user) {
            this.id_user = id_user;
            return this;
        }

        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder phone_number(String phone_number) {
            this.phone_number = phone_number;
            return this;
        }

        public UserBuilder address(String address) {
            this.address = address;
            return this;
        }

        public UserBuilder postal_code(String postal_code) {
            this.postal_code = postal_code;
            return this;
        }

        public UserBuilder local(String local) {
            this.local = local;
            return this;
        }

        public UserBuilder district(String district) {
            this.district = district;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder stand(Stand stand) {
            this.stand = stand;
            return this;
        }

        public UserBuilder permissions(Set<Permission> permissions) {
            this.permissions = permissions;
            return this;
        }

        public UserBuilder deleted(boolean deleted){
            this.deleted = deleted;
            return this;
        }

        public User build() {
            return new User(this.id_user, this.name, this.phone_number, this.address, this.postal_code, this.local, this.district, this.email, this.password, this.stand, this.permissions, this.deleted);
        }

        public String toString() {
            return "User(" + '\n' +
                    "id_user=" + this.id_user + '\n' +
                    "name=" + this.name + '\n' +
                    "phone_number=" + this.phone_number + '\n' +
                    "address=" + this.address + '\n' +
                    "postal_code=" + this.postal_code + '\n' +
                    "local=" + this.local + '\n' +
                    "district=" + this.district + '\n' +
                    "email=" + this.email + '\n' +
                    "password=" + this.password + '\n' +
                    "stand=" + this.stand + '\n' +
                    "permissions=" + this.permissions + '\n' +
                    "deleted=" + this.deleted + '\n' +
                    ")";
        }
    }
}