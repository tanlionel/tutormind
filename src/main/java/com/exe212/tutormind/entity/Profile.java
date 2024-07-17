package com.exe212.tutormind.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profile", schema = "mydb")
public class Profile {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @MapsId("userId")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Size(max = 1000)
    @Column(name = "personal_introduction", length = 1000)
    private String personalIntroduction;

    @Size(max = 2000)
    @Column(name = "personal_information", length = 2000)
    private String personalInformation;

    @Column(name = "rating_point", precision = 10)
    private Double ratingPoint;

}