package com.fleetforyou.fleetforyou.Domain.Enums;

import java.util.*;

public enum Districts {
    ACORES("Açores"),
    AVEIRO("Aveiro"),
    BEJA("Beja"),
    BRAGA("Braga"),
    BRAGANCA("Bragança"),
    CASTELO_BRANCO("Castelo Branco"),
    COIMBRA("Coimbra"),
    EVORA("Évora"),
    FARO("Faro"),
    GUARDA("Guarda"),
    LEIRIA("Leiria"),
    LISBOA("Lisboa"),
    MADEIRA("Madeira"),
    PORTALEGRE("Portalegre"),
    PORTO("Porto"),
    SANTAREM("Santarém"),
    SETUBAL("Setúbal"),
    VIANA_DO_CASTELO("Viana do Castelo"),
    VILA_REAL("Vila Real"),
    VISEU("Viseu");

    private final String district;

    Districts(String district) {
        this.district = district;
    }

    public static List<String> iterateByDistricts() {
        Set<String> names = new HashSet<>();
        for (Districts districts : Districts.values()) {
            names.add(districts.district);
        }

        List<String> districtNames = new ArrayList<>(names);
        Collections.sort(districtNames);
        return districtNames;
    }
}