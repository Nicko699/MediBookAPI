package org.medibook.Model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @ManyToMany(mappedBy = "listRoles")
    private List<User>listUsers;

    public Rol() {
    }

    public Rol(Long id, String name, String description, List<User> listUsers) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.listUsers = listUsers;
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

    public List<User> getListUsers() {
        return listUsers;
    }

    public void setListUsers(List<User> listUsers) {
        this.listUsers = listUsers;
    }
}
