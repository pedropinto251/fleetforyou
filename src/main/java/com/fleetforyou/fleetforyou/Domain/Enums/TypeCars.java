package com.fleetforyou.fleetforyou.Domain.Enums;

import java.util.*;

public enum TypeCars {
    LIGEIROS ("Ligeiros"),
    MERCADORIAS ("Mercadorias"),
    PESADOS ("Pesados"),
    PASSAGEIROS ("Passageiros"),
    ;
private  final String TypeCars ;
    TypeCars(String TypeCars) {
        this.TypeCars = TypeCars;
    }
    public static List<String> iterateByType() {
        Set<String> type = new HashSet<>();
        for (TypeCars typeCars : com.fleetforyou.fleetforyou.Domain.Enums.TypeCars.values()) {
            type.add(typeCars.TypeCars);
        }
        List<String> typeNames = new ArrayList<>(type);
        Collections.sort(typeNames);
        return typeNames;
    }
}
