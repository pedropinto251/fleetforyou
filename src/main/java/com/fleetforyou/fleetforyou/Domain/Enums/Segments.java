package com.fleetforyou.fleetforyou.Domain.Enums;

import java.util.*;

public enum Segments {
    CARRINHA ("Carrinha"),
    CITADINHO("Citadinho"),
    MONOVOLUME("Monovolume"),
    SUV("SUV"),
    SCOOTER ("Scooter"),
    OFFROAD ("Off Road"),
    TRAIL ("Trail"),
    SPORT ("Sport"),

    UTILITARIO("Utilitário"),
    ;
    private final String segments;
    Segments(String segments) {
        this.segments = segments;
    }
    public static List<String> iterateBySegments() {
        Set<String> segments = new HashSet<>();
        for (Segments segmento : Segments.values() ) {
            segments.add(segmento.segments);
        }
        List<String> segmentsNames = new ArrayList<>(segments);
        Collections.sort(segmentsNames);
        return segmentsNames;
    }
}
