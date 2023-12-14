package com.airbnb.airbnb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

//    @ManyToOne
//    @JoinColumn(name = "category_id")
//    private Category category;


}
