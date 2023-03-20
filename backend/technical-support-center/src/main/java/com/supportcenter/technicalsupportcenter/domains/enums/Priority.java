package com.supportcenter.technicalsupportcenter.domains.enums;

public enum Priority {
    LOW(0, "LOW"),
    MEDIUM(1, "AVERAGE"),
    HIGH(2, "HIGH");

    private Integer code;
    private String description;

    private Priority(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static Priority valueOf(Integer code) {
        for (Priority value : Priority.values()) {
            if (value.getCode() == code){
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid Priority code");
    }
}
