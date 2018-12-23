package iHeart.demo.iHeartDemo.controllers;

import iHeart.demo.iHeartDemo.Exception.NotFoundException;
import iHeart.demo.iHeartDemo.models.StationModel;
import iHeart.demo.iHeartDemo.services.face.StationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class StationControllerTest {
    private static final String STATIONMOCK_ONE_NAME = "Radio1";
    private static final Long INVALID_STATION_ID = 10L;
    private static final String INVALID_STATIONMOCK_NAME = "Radio12";
    private static final String STATIONMOCK_TWO_NAME = "RockR";
    @MockBean
    private StationService stationService;

    private StationController stationController;
    private List<StationModel> mockStations;

    @Before
    public void setUp() throws Exception {
        stationController = new StationController(stationService);
        mockStations = new ArrayList<>();

        StationModel station = new StationModel();
        station.setName("kjRadio");
        station.setHdEnabled(true);
        station.setCallSign("KJONE4");
        station.setStationId(0L);
        mockStations.add(station);

        station = new StationModel();
        station.setName(STATIONMOCK_ONE_NAME);
        station.setHdEnabled(true);
        station.setCallSign("R1");
        station.setStationId(1L);
        mockStations.add(station);

        station = new StationModel();
        station.setName(STATIONMOCK_TWO_NAME);
        station.setHdEnabled(false);
        station.setCallSign("RR");
        station.setStationId(2L);
        mockStations.add(station);

        when(stationService.getAllStations()).thenReturn(mockStations);
        // station by id
        when(stationService.getStationById(Mockito.eq(1L))).thenReturn(mockStations.get(1));
        when(stationService.getStationById(Mockito.eq(INVALID_STATION_ID))).thenThrow(new NotFoundException(String.format("Station ID %d was not found.", INVALID_STATION_ID)));
        // station by name
        when(stationService.getStationByName(Mockito.eq(STATIONMOCK_ONE_NAME))).thenReturn(mockStations.get(1));
        when(stationService.getStationByName(Mockito.eq(INVALID_STATIONMOCK_NAME))).thenThrow(new NotFoundException(String.format("Station ID %d was not found.", INVALID_STATION_ID)));
        // station by HdEnabled
        when(stationService.getStationsByHdEnabled(Mockito.eq(true))).thenReturn(mockStations.stream().filter(s -> s.isHdEnabled() == true).collect(Collectors.toList()));
        when(stationService.getStationsByHdEnabled(Mockito.eq(false))).thenReturn(mockStations.stream().filter(s -> s.isHdEnabled() == false).collect(Collectors.toList()));
    }

    @Test
    public void findAllStations() throws Exception {

        // when
     ResponseEntity<List<StationModel>> response = stationController.findAllStations();

        // then
        assertNotNull(response);

    }

    @Test
    public void retrieveStationById() {

        // when
        ResponseEntity<StationModel> station = stationController.findStationById(1L);

        // then
        assertNotNull(station);
        assertTrue(station.getBody().getName().compareTo(STATIONMOCK_ONE_NAME) == 0);
    }

    @Test(expected = NotFoundException.class)
    public void findStationById_notFound_test() {
        // when
        ResponseEntity<StationModel> station = stationController.findStationById(INVALID_STATION_ID);
    }

    @Test
    public void retrieveStationByName_happyPath_test() {

        // when
      ResponseEntity<StationModel> station = stationController.findStationByName(STATIONMOCK_ONE_NAME);

        // then
        assertNotNull(station);
        assertTrue(station.getBody().getName().compareTo(STATIONMOCK_ONE_NAME) == 0);
    }

    @Test(expected = NotFoundException.class)
    public void findStationByName_notFound_test() {
        // when
       ResponseEntity <StationModel> station = stationController.findStationByName(INVALID_STATIONMOCK_NAME);
    }

    @Test
    public void findStationByHdEnabled_true_test() {

        // when
       ResponseEntity<List<StationModel>> stations = stationController.findStationByHdEnabled(true);

        // then
        assertNotNull(stations);
        assertTrue(!stations.getBody().isEmpty());
        assertTrue(stations.getBody().size() == 2);
    }

    @Test
    public void retrieveStationByHdEnabled_false_test() {

        // when
       ResponseEntity <List<StationModel>> stations = stationController.findStationByHdEnabled(false);

        // then
        assertNotNull(stations);
        assertTrue(!stations.getBody().isEmpty());
        assertTrue(stations.getBody().size() == 1);
    }


    @Test
    public void createOrUpdateStation() {
        // given
        StationModel station = new StationModel();
        when(stationService.createOrUpdate(Mockito.any(StationModel.class)))
                .thenReturn(station);

        // when
       ResponseEntity<StationModel>  stations = stationController.createOrUpdateStation(station);

        // then
        assertNotNull(stations);
    }

}