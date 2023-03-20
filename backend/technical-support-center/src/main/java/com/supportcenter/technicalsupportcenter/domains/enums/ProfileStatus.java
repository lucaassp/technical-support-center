package com.supportcenter.technicalsupportcenter.domains.enums;

public enum ProfileStatus {
    ADMIN(0, "ROLE_ADMINISTRATOR"),
    CLIENT(1, "ROLE_CLIENT"),
    TECHNICIAN(2, "ROLE_TECHNICIAN");

    private Integer code;
    private String description;

    private ProfileStatus(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static ProfileStatus valueOf(Integer code) {
        for (ProfileStatus value : ProfileStatus.values()) {
            if (value.getCode() == code){
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid Profile Status code");
    }
}
