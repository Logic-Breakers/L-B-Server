package com.airbnb.airbnb.stay.entity;

import com.airbnb.airbnb.image.entity.Image;
import com.airbnb.airbnb.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    private String houseName;

    @Column(nullable = false)
    private String info;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private Long guest; //사용 가능 인원

    @Column
    private Double star; //별점

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String detailAddress;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

//    @Column(nullable = false)
//    private boolean guestFavourite; //게스트 선호

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PlaceType placeType; //숙소 유형

    public enum PlaceType {
        공간_전체,
        방,
        다인실
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PropertyType propertyType; //건물 유형

    public enum PropertyType {
        단독_또는_다세대_주택,
        아파트,
        게스트용_별채,
        호텔
    }

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private Long beds;

    @Column(nullable = false)
    private Long bedrooms;

    @Column(nullable = false)
    private Long bathrooms;

    @ManyToOne
    @JoinColumn(name = "host_id")
    private Member member;

    @OneToMany(mappedBy = "stay", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<StayCategories> stayCategories = new ArrayList<>();

    @OneToMany(mappedBy = "stay", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Image> images = new ArrayList<>();

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
