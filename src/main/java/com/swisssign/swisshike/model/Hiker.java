package com.swisssign.swisshike.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Hiker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column (name="name")
    private String name;

    @Column (name="age")
    private Integer age;

    @Column (name="experience_level")
    private String experienceLevel;

    @ManytoMany(mappedBy = "hikers")
    @Column (name="tour_id")
    private List<Tour> tours;


    private Hiker(HikerBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.age = builder.age;
        this.experienceLevel = builder.experienceLevel;
        this.tours = builder.tours;
    }

    public Hiker() {
    }

    public static class HikerBuilder {
        private long id;
        private String name;
        private Integer age;
        private String experienceLevel;
        private List<Tour> tours = new ArrayList<>();

        public HikerBuilder id(long id) {
            this.id = id;
            return this;
        }

        public HikerBuilder name(String name) {
            this.name = name;
            return this;
        }

        public HikerBuilder age(Integer age) {
            this.age= age;
            return this;
        }

        public HikerBuilder experienceLevel(String experienceLevel) {
            this.experienceLevel = experienceLevel;
            return this;
        }

        public Hiker build() {
            return new Hiker(this);
        }
    }
}
