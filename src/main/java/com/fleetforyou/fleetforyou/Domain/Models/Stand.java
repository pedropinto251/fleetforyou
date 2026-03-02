package com.fleetforyou.fleetforyou.Domain.Models;

import java.util.Objects;

public class Stand {
    private int id_stand;
    private String name;
    private String phone_number;
    private String address;
    private String postalCode;
    private String local;
    private String district;
    private boolean deleted;

    public Stand(int id_stand, String name, String phone_number, String address, String postalCode, String local, String district, boolean deleted) {
        this.id_stand = id_stand;
        this.name = name;
        this.phone_number = phone_number;
        this.address = address;
        this.postalCode = postalCode;
        this.local = local;
        this.district = district;
        this.deleted = deleted;
    }

    public Stand() {
    }

    public int getId() {
        return id_stand;
    }

    public void setId(int id_stand) {
        this.id_stand = id_stand;
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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
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

    public boolean isDeleted(){return deleted;}

    public void setDeleted(boolean deleted){this.deleted = deleted;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stand stand = (Stand) o;
        return Objects.equals(id_stand, stand.id_stand) && Objects.equals(name, stand.name) && Objects.equals(phone_number, stand.phone_number) && Objects.equals(address, stand.address) && Objects.equals(postalCode, stand.postalCode) && Objects.equals(local, stand.local) && Objects.equals(district, stand.district) && Objects.equals(deleted, stand.deleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_stand, name, phone_number, address, postalCode, local, district, deleted);
    }

    @Override
    public String toString() {
        return "Stand{" + '\n' +
                "id_stand=" + id_stand + '\n' +
                "name=" + name + '\n' +
                "phone_number=" + phone_number + '\n' +
                "address=" + address + '\n' +
                "postalCode=" + postalCode + '\n' +
                "local=" + local + '\n' +
                "district=" + district + '\n' +
                "deleted=" + deleted + '\n' +
                '}';
    }
}
