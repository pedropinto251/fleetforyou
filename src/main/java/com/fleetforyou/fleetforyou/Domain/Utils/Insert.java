package com.fleetforyou.fleetforyou.Domain.Utils;

public class Insert {
    private final boolean isInserted;
    private int id;

    /**
     * Construtor do objeto `Insert`.
     *
     * @param isInserted Indica se o registro foi inserido com sucesso.
     */
    public Insert(boolean isInserted) {
        this.isInserted = isInserted;
    }

    /**
     * Construtor do objeto `Insert`.
     *
     * @param isInserted Indica se o registro foi inserido com sucesso.
     * @param id O ID do registro inserido.
     */
    public Insert(boolean isInserted, int id) {
        this.isInserted = isInserted;
        this.id = id;
    }

    /**
     * Retorna um valor booleano e indica se o registro foi inserido com sucesso.
     *
     * @return true se o registro foi inserido com sucesso, false caso contrário.
     */
    public boolean isInserted() {
        return isInserted;
    }

    /**
     * Retorna o ID do registro inserido.
     *
     * @return O ID do registro inserido.
     */
    public int getId() {
        return id;
    }
}
