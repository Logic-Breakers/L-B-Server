package com.airbnb.airbnb.member.entity;

import com.airbnb.airbnb.stay.entity.Stay;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private LocalDateTime birthDate;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "member", cascade = {CascadeType.REMOVE})
    private List<Stay> stay = new ArrayList<>();

    public Member(String email, String password, Collection<? extends GrantedAuthority> authorities) {
    }

//    @Override
//    public String getUsername() {
//        return getEmail();
//    }

    public enum MemberRole {
        ROLE_USER,
        ROLE_ADMIN
    }
}

