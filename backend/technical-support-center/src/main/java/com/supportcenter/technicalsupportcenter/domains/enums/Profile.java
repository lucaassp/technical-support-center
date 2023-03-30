package com.supportcenter.technicalsupportcenter.domains.enums;
public enum Profile {
    ADMIN(0, "ROLE_ADMINISTRATOR"),  CLIENT(1, "ROLE_CLIENT"), TECHNICIAN(2, "ROLE_TECHNICIAN");
    private Integer code;
    private String description;
    private Profile(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
    public Integer getCode() {
        return code;
    }
    public String getDescription() {
        return description;
    }
    public static Profile valueOf(Integer code) { // Não preciso criar uma instancia de perfil pra usar esse metodo em outras partes do codigo
        for (Profile value : Profile.values()) {
            if (value.getCode() == code){
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid Profile Status code");
    }
}
