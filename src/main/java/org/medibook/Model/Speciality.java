package org.medibook.Model;

import jakarta.persistence.*;

import java.util.List;
@Entity
public class Speciality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "speciality")
    private List<Doctor> listDoctors;

    public Speciality() {
    }

    public Speciality(Long id, String name, String description, List<Doctor> listDoctors) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.listDoctors = listDoctors;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Doctor> getListDoctors() {
        return listDoctors;
    }

    public void setListDoctors(List<Doctor> listDoctors) {
        this.listDoctors = listDoctors;
    }
}
