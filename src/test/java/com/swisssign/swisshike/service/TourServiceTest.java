package com.swisssign.swisshike.service;

import com.swisssign.swisshike.model.Hiker;
import com.swisssign.swisshike.model.MountainHut;
import com.swisssign.swisshike.model.Tour;
import com.swisssign.swisshike.repository.HikerRepository;
import com.swisssign.swisshike.repository.MountainHutRepository;
import com.swisssign.swisshike.repository.TourRepository;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;


public class TourServiceTest {

    @InjectMocks
    private TourService tourService;

    @Mock
    private TourRepository tourRepository;

    @Mock
    private HikerRepository hikerRepository;

    @Mock
    private MountainHutRepository mountainHutRepository;

    private Tour tour;
    private Hiker hiker;
    private MountainHut mountainHut;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mountainHut = new MountainHut.MountainHutBuilder()
                        .id(1L)
                        .name("Alpine Hut")
                        .maxCapacity(10)
                        .pandemicCapacity(10)
                        .build();

        hiker = new Hiker.HikerBuilder()
                    .id(1L)
                    .name("John None")
                    .experienceLevel("Beginner")
                    .build();


        tour = new Tour.TourBuilder()
                   .id(1L)
                   .name("Pilatus Tour")
                   .difficulty("Intermediate")
                   .startDate(new Date())
                   .endDate(new Date())
                   .hikers(Collections.singletonList(hiker)).build();

    }

    @Test
    public void testCreateTour() {
        when(mountainHutRepository.findById(anyLong())).thenReturn(Optional.of(mountainHut));
        when(tourRepository.save(any(Tour.class))).thenReturn(tour);

        Tour createdTour = tourService.createTour("Pilatus Tour", "Intermediate", new Date(), new Date(), 1L, Collections.singletonList(1L));

        assertNotNull(createdTour);
        assertEquals("Pilatus Tour", createdTour.getName());
        assertEquals("Intermediate", createdTour.getDifficulty());

        verify(mountainHutRepository, times(1)).findById(1L);
        verify(tourRepository, times(1)).save(any(Tour.class));
    }

    @Test
    public void testAssignWrongHikerToTour() {
        when(tourRepository.findById(anyLong())).thenReturn(Optional.of(tour));
        when(hikerRepository.findById(anyLong())).thenReturn(Optional.of(hiker));

        InvalidOperationException exception = assertThrows(InvalidOperationException.class, () -> {
            tourService.assignHikerToTour(1L, 1L);
        });

        assertEquals("Hiker experience level does not match tour difficulty", exception.getMessage());
    }

    @Test
    public void testAssignHikerToTour() {

        hiker = new Hiker.HikerBuilder().id(1L).name("John Doe").experienceLevel("Intermediate").build();

        when(tourRepository.findById(anyLong())).thenReturn(Optional.of(tour));
        when(hikerRepository.findById(anyLong())).thenReturn(Optional.of(hiker));
        verify(tourRepository, times(1)).save(any(Tour.class));

        tour updatedTour = tourService.assignHikerToTour(1L, 1L);

        assertNotNull(updatedTour);
        assertTrue(updatedTour.getHikers().contains(hiker));

        verify(tourRepository, times(1)).findById(1L);
        verify(hikerRepository, times(1)).findById(1L);
        verify(tourRepository, times(1)).save(tour);

    }
}
