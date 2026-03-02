package com.fleetforyou.fleetforyou.Domain.Utils;

import com.fleetforyou.fleetforyou.Domain.Enums.Responses;

public class Response {
    private final Responses status;
    private Object data;

    /**
     * Construtor do objeto Response.
     *
     * @param status O status da resposta.
     * @param data Os dados da resposta.
     */
    public Response(Responses status, Object data) {
        this.status = status;
        this.data = data;
    }

    /**
     * Construtor do objeto Response com status.
     *
     * @param status O status da resposta.
     */
    public Response(Responses status) {
        this.status = status;
    }

    /**
     * Obtém o status da resposta.
     *
     * @return O status da resposta.
     */
    public Responses getStatus() {
        return status;
    }

    /**
     * Obtém os dados da resposta.
     *
     * @return Os dados da resposta.
     */
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "status=" + status +
                '}';
    }
}
