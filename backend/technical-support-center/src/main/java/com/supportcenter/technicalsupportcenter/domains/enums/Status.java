package com.supportcenter.technicalsupportcenter.domains.enums;

public enum Status {

    OPEN(0, "OPEN"),
    PROGRESS(1, "PROGRESS"),
    CLOSED(2, "CLOSED");
    private Integer code;
    private String description;

    private Status(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static Status valueOf(Integer code) {
        for (Status value : Status.values()) {
            if (value.getCode() == code){
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid Status code");
    }
}
