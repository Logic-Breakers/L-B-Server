package com.airbnb.airbnb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Stay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String info;

    @Column(nullable = false)
    private String country;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PropertyType propertyType;

    public enum PropertyType {
        HOUSE,
        FLAT,
        GUEST_HOUSE,
        HOTEL
    }

    @Column(nullable = false)
    private Long charge;

    @Column(nullable = false)
    private Long beds;

    @Column(nullable = false)
    private Long bedrooms;

    @Column(nullable = false)
    private Long bathrooms;

//    숙소 호스트의 user id
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

    @ManyToMany
    @JoinTable(
            name = "stay_category",
            joinColumns = @JoinColumn(name = "stay_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @JsonIgnore
    private Set<Category> categories = new HashSet<>();


}
