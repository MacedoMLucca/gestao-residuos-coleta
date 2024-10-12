package br.com.fiap.coletalixo.model.enums;

public enum StatusColeta {

    PENDENTE("pendente"),
    EM_ANDAMENTO("andamento"),
    CONCLUIDA("concluida");

    private String status;

    StatusColeta(String status){
        this.status = status;
    }

    public String getStatus(){
        return this.status;
    }

    public static StatusColeta fromString(String status) {
        for (StatusColeta s : StatusColeta.values()) {
            if (s.getStatus().equalsIgnoreCase(status)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Status inválido: " + status);
    }
}