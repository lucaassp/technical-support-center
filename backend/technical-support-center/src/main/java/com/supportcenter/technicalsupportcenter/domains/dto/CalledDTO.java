package com.supportcenter.technicalsupportcenter.domains.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.supportcenter.technicalsupportcenter.domains.Called;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.time.LocalDateTime;

public class CalledDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    private Long id;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime openingDate = LocalDateTime.now();
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDateTime closingDate;
    @NotNull(message = "Priority cannot be null")
    private Integer priority;
    @NotNull(message = "Status cannot be null")
    private Integer status;
    @NotNull(message = "Title cannot be null")
    private String title;
    @NotNull(message = "Observation cannot be null")
    private String observation;
    @NotNull(message = "Technician cannot be null")
    private Long technician;
    @NotNull(message = "Client cannot be null")
    private Long client;

    private String nameTechnician;
    private String nameClient;


    public CalledDTO() {
    }

    public CalledDTO(Called obj) {
        this.id = obj.getId();
        this.openingDate = obj.getOpeningDate();
        this.closingDate = obj.getClosingDate();
        this.priority = obj.getPriority().getCode();
        this.status = obj.getStatus().getCode();
        this.title = obj.getTitle();
        this.observation = obj.getObservation();
        this.technician = obj.getTechnician().getId();
        this.client = obj.getClient().getId();
        this.nameTechnician = obj.getTechnician().getName();
        this.nameClient = obj.getClient().getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDateTime openingDate) {
        this.openingDate = openingDate;
    }

    public LocalDateTime getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(LocalDateTime closingDate) {
        this.closingDate = closingDate;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Long getTechnician() {
        return technician;
    }

    public void setTechnician(Long technician) {
        this.technician = technician;
    }

    public Long getClient() {
        return client;
    }

    public void setClient(Long client) {
        this.client = client;
    }

    public String getNameTechnician() {
        return nameTechnician;
    }

    public void setNameTechnician(String nameTechnician) {
        this.nameTechnician = nameTechnician;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }
}
