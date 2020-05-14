package cz.fi.muni.pa165.rest.sampledata;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cz.fi.muni.pa165.entity.Tire;
import cz.fi.muni.pa165.service.TireService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Random;

/**
 * Loads some sample data to populate the eshop database.
 *
 * @author Martin Kuba makub@ics.muni.cz
 */
@Component
@Transactional //transactions are handled on facade layer
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

    final static Logger log = LoggerFactory.getLogger(SampleDataLoadingFacadeImpl.class);

    public static final String JPEG = "image/jpeg";

    @Autowired
    private TireService tireService;

    @Override
    @SuppressWarnings("unused")
    public void loadData() throws IOException {
        Tire amber = new Tire("manufacturer", "type",50,"season",new BigDecimal(120000));

    }

    private Tire tires(String manufacturer, String type, int size, String season, BigDecimal price) throws IOException {
        Tire pr = new Tire();
        
        pr.setManufacturer(manufacturer);
        pr .setPrice(price);
        pr.setSeason(season);
        pr.setSize(size);
        pr.setType(type);
        tireService.create(pr);
        return pr;
    }
}
