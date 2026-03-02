package com.fleetforyou.fleetforyou.Domain.Enums;

import java.util.*;

public enum Fuel {
    DIESEL("Diesel"),
    GASOLINA("Gasolina"),
    GPL("GPL"),
    ELETRIC("Eletrico"),
    HIBRIDO("Hibrido")
    ;

    private final String fuel;
    Fuel(String fuel) {
        this.fuel = fuel;
    }
    public static List<String> iterateByFuel() {
        Set<String> fuel = new HashSet<>();
        for (Fuel type : Fuel.values()) {
            fuel.add(type.fuel);
        }

        List<String> fuelType = new ArrayList<>(fuel);
        Collections.sort(fuelType);
        return fuelType;
    }
}
