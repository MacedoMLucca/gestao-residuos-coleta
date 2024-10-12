package br.com.fiap.coletalixo.model.enums;

public enum UsuarioRole {
    ADMIN ("ADMIN"),
    USER ("USER");

    private String role;

    UsuarioRole(String role){
        this.role = role;
    }

    public String getRole(){
        return this.role;
    }
}

