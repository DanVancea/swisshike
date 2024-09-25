package com.swisssign.swisshike.service;

import com.swisssign.swisshike.model.MountainHut;
import com.swisssign.swisshike.repository.MountainHutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MountainHutService {

    @Autowired
    private MountainHutRepository mountainHutRepository;

    public MountainHut createMountainHut(String name, Integer maxCapacity) {
        MountainHut hut = new MountainHut.MountainHutBuilder()
                .name(name)
                .maxCapacity(maxCapacity)
                .pandemicCapacity(maxCapacity)
                .build();

        return mountainHutRepository.save(hut);
    }

    public void setPandemicMode(int pandemicCapacityProcentage) {
        List<MountainHut> huts = mountainHutRepository.findAll();
        for (MountainHut hut : huts) {
            int adjustedCapacity = (hut.getMaxCapacity() * pandemicCapacityProcentage) / 100;

            MountainHut updatedHut = new MountainHut.MountainHutBuilder()
                    .id(hut.getId())
                    .name(hut.getName())
                    .maxCapacity(hut.getMaxCapacity())
                    .pandemicCapacity((adjustedCapacity < hut.getPandemicCapacity()) ? adjustedCapacity : hut.getPandemicCapacity())
                    .tours(hut.getTours())
                    .build();

            mountainHutRepository.save(updatedHut);
        }
    }
}
