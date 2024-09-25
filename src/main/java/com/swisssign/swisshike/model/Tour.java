package com.swisssign.swisshike.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
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

    public Tour() {
    }

    private Tour(TourBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.difficulty = builder.difficulty;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.hut = builder.hut;
        this.hikers = builder.hikers;
    }



    public static class TourBuilder {
        private long id;
        private String name;
        private String difficulty;
        private Date startDate;
        private Date endDate;
        private MountainHut hut;
        private List<Hiker> hikers = new ArrayList<>();

        public TourBuilder id(long id) {
            this.id = id;
            return this;
        }

        public TourBuilder name(String name) {
            this.name = name;
            return this;
        }

        public TourBuilder difficulty(String difficulty) {
            this.difficulty = difficulty;
            return this;
        }

        public TourBuilder startDate(Date startDate) {
            this.startDate = startDate;
            return this;
        }

        public TourBuilder endDate(Date endDate) {
            this.endDate = endDate;
            return this;
        }

        public TourBuilder hut(MountainHut hut) {
            this.hut = hut;
            return this;
        }

        public TourBuilder hikers(List<Hiker> hikers) {
            this.hikers = hikers;
            return this;
        }

        public Tour build() {
            return new Tour(this);
        }

    }
}
