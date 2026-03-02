package com.fleetforyou.fleetforyou.Domain.Models;

import java.util.Objects;

public class Client {
    private int id;
    private String name;
    private String nc;
    private String phone_number;
    private String address;
    private String postalCode;
    private String local;
    private String district;
    private boolean deleted;

    public Client(int id, String name, String nc, String phone_number, String address, String postalCode, String local, String district, boolean deleted) {
        this.id = id;
        this.name = name;
        this.nc = nc;
        this.phone_number = phone_number;
        this.address = address;
        this.postalCode = postalCode;
        this.local = local;
        this.district = district;
        this.deleted = deleted;
    }

    public Client() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNc() {
        return nc;
    }

    public void setNc(String nc) {
        this.nc = nc;
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
        Client client = (Client) o;
        return Objects.equals(id, client.id) && Objects.equals(name, client.name) && Objects.equals(nc, client.nc) && Objects.equals(phone_number, client.phone_number) && Objects.equals(address, client.address) && Objects.equals(postalCode, client.postalCode) && Objects.equals(local, client.local) && Objects.equals(district, client.district);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, nc, phone_number, address, postalCode, local, district);
    }

    @Override
    public String toString() {
        return "Client{" + '\n' +
                "id=" + id + '\n' +
                "name=" + name + '\n' +
                "nc=" + nc + '\n' +
                "phone_number=" + phone_number + '\n' +
                "address=" + address + '\n' +
                "postalCode=" + postalCode + '\n' +
                "local=" + local + '\n' +
                "district=" + district + '\n' +
                '}';
    }
}
