package com.airbnb.airbnb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "stay_id")
//    private Stay stay;

    @Column
    private String imageUrl;

    @Column(nullable = false)
    private LocalDateTime uploadedAt;
}
