package com.supportcenter.technicalsupportcenter.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.supportcenter.technicalsupportcenter.domains.enums.ProfileStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_technician")
public class Technician extends User {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @OneToMany(mappedBy = "technician")
    private List<Called> calleds = new ArrayList<>();

    public Technician() {
        addProfile(ProfileStatus.TECHNICIAN);
    }

    public Technician(Long id, String name, String cpf, String email, String password) {
        super(id, name, cpf, email, password);
        addProfile(ProfileStatus.TECHNICIAN);
    }

    public List<Called> getCalleds() {
        return calleds;
    }

    public void setCalleds(List<Called> calleds) {
        this.calleds = calleds;
    }
}
