package de.db.controller;

import de.db.dto.SectionView;
import de.db.exception.StationException;
import de.db.service.StationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
class StationControllerTest {

    @Mock
    private StationService stationService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(new StationController(stationService))
                .build();
    }

    @Test
    public void getStation_WithValidData_ReturnsOk() throws Exception {
        SectionView expectedSectionView = SectionView.builder().sections(Set.of("A")).build();
        when(stationService.getWagensSections("ABC", 12, 12)).thenReturn(expectedSectionView);
        this.mockMvc.perform(get("/station/ABC/train/12/waggon/12"))
                .andExpect(status().isOk());
    }

    @Test
    public void getStation_With_Station_Exception() throws Exception {
        when(stationService.getWagensSections("ABC", 12, 12)).thenThrow(StationException.class);
        this.mockMvc.perform(get("/station/ABC/train/12/waggon/12"))
                .andExpect(status().isNotFound());
    }
}