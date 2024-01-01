package com.airbnb.airbnb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


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

    @Column(nullable = false)
    private Long guest; //사용 가능 인원

    @Column
    private Double star; //별점

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

    @OneToMany(mappedBy = "stay", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<StayCategories> stayCategories = new ArrayList<>();

    @OneToMany(mappedBy = "stay", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Image> images = new ArrayList<>();
}
