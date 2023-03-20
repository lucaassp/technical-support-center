package com.supportcenter.technicalsupportcenter.domains.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.supportcenter.technicalsupportcenter.domains.Technician;
import com.supportcenter.technicalsupportcenter.domains.enums.ProfileStatus;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TechnicianDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    protected Long id;
    @NotNull(message = "Name cannot be null")
    protected String name;
    @NotNull(message = "CPF cannot be null")
    protected String cpf;
    @NotNull(message = "Email cannot be null")
    protected String email;
    @NotNull(message = "Password cannot be null")
    protected String password;
    protected Set<Integer> profiles = new HashSet<>();
    @JsonFormat(pattern = "yyyy-MM-dd")
    protected LocalDate creationDate = LocalDate.now();

    public TechnicianDTO() {
    }

    public TechnicianDTO(Technician obj) {
        this.id = obj.getId();
        this.name = obj.getName();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.password = obj.getPassword();
        this.profiles = obj.getProfiles().stream().map(p -> p.getCode()).collect(Collectors.toSet());
        this.creationDate = obj.getCreationDate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<ProfileStatus> getProfiles() {
        return profiles.stream().map(x -> ProfileStatus.valueOf(x)).collect(Collectors.toSet());
    }

    public void setProfiles(Set<Integer> profiles) {
        this.profiles = profiles;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
