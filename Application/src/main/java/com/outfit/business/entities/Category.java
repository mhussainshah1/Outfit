package com.outfit.business.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Category implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "category", orphanRemoval = true)
    public Set<Item> items;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty
    @Column(unique = true)
    private String name;

    public Category() {
        items = new HashSet<>();
    }

    public Category(@NotEmpty String name) {
        this();
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}
