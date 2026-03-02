package com.fleetforyou.fleetforyou.Domain.Enums;

public enum Permission {
    GOD_ADMIN, // GOD ADMIN (REGISTERS ADMINS AND OTHER GOD ADMINS)
    MANAGE_CLIENTS, // OPERATOR - CHANGES CLIENTS DATA
    MANAGE_VEHICLES, // OPERATOR - CHANGES VEHICLES DATA
    MANAGE_RENTAL_PRICE, // OPERATOR - CHANGE RENTAL PRICE
    CREATE_RENTAL, // SELLER - CREATES RENTAL
    MANAGE_EMPLOYEES, // MANAGER - CHANGES EMPLOYEES DATA
    MANAGE_STATISTICS, // MANAGER - CAN SEE STATISTICS
    VIEW_RENTAL_HISTORY; // MANAGER - VIEW RENTAL HISTORY

    public static Permission fromString(String permission) {
        for (Permission perm : Permission.values()) {
            if (perm.toString().equalsIgnoreCase(permission)) {
                return perm;
            }
        }
        throw new IllegalArgumentException("Permissão não existente: " + permission);
    }

    public int getIdPerm(Permission permission) {
        return switch (permission) {
            case GOD_ADMIN -> 1;
            case MANAGE_CLIENTS -> 2;
            case MANAGE_VEHICLES -> 3;
            case CREATE_RENTAL -> 4;
            case MANAGE_EMPLOYEES -> 5;
            case MANAGE_STATISTICS -> 6;
            case MANAGE_RENTAL_PRICE -> 7;
            case VIEW_RENTAL_HISTORY -> 8;
        };
    }

    @Override
    public String toString() {
        return switch (this) {
            case GOD_ADMIN -> "God Admin";
            case MANAGE_CLIENTS -> "Gerir clientes";
            case MANAGE_VEHICLES -> "Gerir veículos";
            case CREATE_RENTAL -> "Criar aluguer";
            case MANAGE_EMPLOYEES -> "Gerir funcionários";
            case MANAGE_STATISTICS -> "Gerir estatísticas";
            case MANAGE_RENTAL_PRICE -> "Gerir preço de aluguer";
            case VIEW_RENTAL_HISTORY -> "Ver histórico de aluguer";
        };
    }
}