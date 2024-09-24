package com.swisssign.swisshike.model;

import java.util.List;

@Entity
@Getter
@Setter
public class MountainHut {

    @Id
    @GemeratedValut(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private long id;

    @Column (name = "name")
    private String name;

    @Column (name = "capacity")
    private Integer maxCapacity;

    private Integer pandemicCapacity;

    @OneToMany(mapping = "hut")
    private List<Tour> tours;

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
