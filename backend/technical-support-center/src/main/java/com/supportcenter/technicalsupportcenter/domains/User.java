package com.supportcenter.technicalsupportcenter.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.supportcenter.technicalsupportcenter.domains.enums.ProfileStatus;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "tb_user")
public abstract class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String name;
    @Column(unique = true)
    protected String cpf;
    @Column(unique = true)
    protected String email;
    protected String password;
    @ElementCollection(fetch = FetchType.EAGER) // quando o usuario for Called a coleção venha junto
    @CollectionTable(name = "tb_profiles")
    protected Set<Integer> profiles = new HashSet<>();
    @JsonFormat(pattern = "yyyy-MM-dd")
    protected LocalDate creationDate = LocalDate.now();

    public User() {
        addProfile(ProfileStatus.CLIENT);
    }

    public User(Long id, String name, String cpf, String email, String password) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        addProfile(ProfileStatus.CLIENT);
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

    public void addProfile(ProfileStatus profile) {
        this.profiles.add(profile.getCode());
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return getId().equals(user.getId()) && getCpf().equals(user.getCpf());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCpf());
    }
}
