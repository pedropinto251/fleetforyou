package com.fleetforyou.fleetforyou.Domain.Enums;

import java.text.CollationElementIterator;
import java.util.*;

public enum UserType {
    OPERADOR("Operador"),
    VENDEDOR("Vendedor"),
    GESTOR("Gestor"),
    ;
    private final String userType;

    UserType(String userType) {
        this.userType = userType;
    }

    public static List<String> iterateByUserTypes() {
        Set<String> userType = new HashSet<>();
        for (UserType userType1 : UserType.values()) {
            userType.add(userType1.userType);
        }
        List<String> userCo = new ArrayList<>(userType);
        Collections.sort(userCo);

        return userCo;
    }
}
