package cz.fi.muni.pa165;

import cz.fi.muni.pa165.entity.Tire;
import cz.fi.muni.pa165.service.TireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;

@Component
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

    @Autowired
    private TireService tireService;

    @Override
    public void loadData() throws IOException {

        Tire t1 = tire("Barum","Zima", "BIG AF", BigDecimal.valueOf(420));

    }

    private Tire tire(String man, String season, String type, BigDecimal price){
        Tire t = new Tire();
        t.setManufacturer(man);
        t.setSeason(season);
        t.setPrice(price);
        t.setType(type);

        tireService.create(t);
        return t;
    }
}
