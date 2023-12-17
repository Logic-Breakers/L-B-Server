package com.airbnb.airbnb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String categoryName;

    @ManyToMany(mappedBy = "categories")
    private Set<Stay> stays = new HashSet<>();

    public Category(Long id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }
}
