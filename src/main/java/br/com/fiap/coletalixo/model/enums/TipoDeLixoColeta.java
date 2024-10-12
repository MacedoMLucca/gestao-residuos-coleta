package br.com.fiap.coletalixo.model.enums;

public enum TipoDeLixoColeta {
    RECICLAVEL ("reciclavel"),
    ORGANICO ("organico"),
    NAO_RECICLAVEL("não reciclavel");

    private String type;

    TipoDeLixoColeta (String type){
        this.type = type;
    }

    public String getType(){
        return this.type;
    }
}

