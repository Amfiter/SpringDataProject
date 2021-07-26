package com.syncretis.SpringDataProject.models;

import javax.persistence.*;

@Entity
@Table(name = "language")
public class Language {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
   /* @ManyToMany(cascade = CascadeType.ALL)
    @Column(name = "name",nullable = false)
    private String name;*/

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }
}
