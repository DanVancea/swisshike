package com.swisssign.swisshike.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class MountainHut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column (name = "name")
    private String name;

    @Column (name = "capacity")
    private Integer maxCapacity;

    private Integer pandemicCapacity;

    @OneToMany(mapping = "hut")
    private List<Tour> tours;

    public MountainHut(MountainHutBuilder mountainHutBuilder) {
       this.id = mountainHutBuilder.id;
       this.name= mountainHutBuilder.name;
       this.maxCapacity = mountainHutBuilder.maxCapacity;
       this.pandemicCapacity = mountainHutBuilder.pandemicCapacity;
       this.tours = mountainHutBuilder.tours;
    }

    public MountainHut() {

    }

    public static class MountainHutBuilder {
        private long id;
        private String name;
        private Integer maxCapacity;
        private Integer pandemicCapacity;
        private List<Tour> tours = new ArrayList<>();

        public MountainHutBuilder id(long id) {
            this.id = id;
            return this;
        }

        public MountainHutBuilder name(String name) {
            this.name = name;
            return this;
        }

        public MountainHutBuilder maxCapacity(Integer maxCapacity) {
            this.maxCapacity = maxCapacity;
            return this;
        }

        public MountainHutBuilder pandemicCapacity(Integer pandemicCapacity) {
            this.pandemicCapacity = pandemicCapacity;
            return this;
        }

        public MountainHutBuilder tours(List<Tour> tours) {
            this.tours = tours;
            return this;
        }

        public MountainHut build() {
            return new MountainHut(this);
        }
    }
}
