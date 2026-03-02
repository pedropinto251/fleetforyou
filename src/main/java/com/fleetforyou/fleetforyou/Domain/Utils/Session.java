package com.fleetforyou.fleetforyou.Domain.Utils;

import com.fleetforyou.fleetforyou.Domain.DTO.User.UserLoggedDTO;

public class Session {
    private static UserLoggedDTO user;

    /**
     * Define o usuário logado na sessão.
     *
     * @param user O usuário logado.
     */
    public static void setUser(UserLoggedDTO user) {
        Session.user = user;
    }

    /**
     * Obtém o usuário logado na sessão.
     *
     * @return O usuário logado.
     */
    public static UserLoggedDTO getUser() {
        return user;
    }

    /**
     * Faz o logout do usuário da sessão.
     */
    public static void logOut() {
        Session.user = null;
    }
}
