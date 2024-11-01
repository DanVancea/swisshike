package com.swisssign.swisshike.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column (name="name")
    private String name;

    @Column (name="difficulty")
    private String difficulty;

    @Column (name="start_date")
    private Date startDate;

    @Column (name="end_date")
    private Date endDate;


    @ManytoOne
    @Column (name="mountain_huts")
    private MountainHut hut;

    @ManytoMany
    @Column (name="hikers")
    private List<Hiker> hikers;

}
