package com.oreilly.restclient.services;

import com.oreilly.restclient.entities.Site;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class GeocoderServiceTest {

    private Logger logger = LoggerFactory.getLogger(GeocoderService.class);

    @Autowired
    private GeocoderService service;

    @Test
    void getLatLngForGoogleHQ() throws UnsupportedEncodingException {
        Site google = service.getLatLng("1600 Ampitheatre Parkway", "Mountain View", "CA");
        logger.info(google.toString());
        assertEquals(37.42, google.getLatitude(), 0.01);
        assertEquals(-122.09, google.getLongitude(), 0.01);
    }

    @Test
    void getLatLngWithoutStreet() throws UnsupportedEncodingException {
        Site boston = service.getLatLng("Boston", "MA");
        logger.info(boston.toString());
        assertEquals(42.36, boston.getLatitude(), 0.01);
        assertEquals(-71.06, boston.getLongitude(), 0.01);
    }

    @Test
    void getLatLngTheClub() throws UnsupportedEncodingException {
        Site theClub = service.getLatLng("2193 NE 106th Ave", "Hillsboro", "OR");
        logger.info(theClub.toString());
        assertEquals(45.53, theClub.getLatitude(), 0.01);
        assertEquals(-122.55, theClub.getLongitude(), 0.01);
    }
}