package cz.fi.muni.pa165.entity;


import com.sun.javaws.exceptions.InvalidArgumentException;

import javax.persistence.Entity;


/**
 * class representing car
 * @author Radim Sasinka, 456315
 */

@Entity
public class Car extends AbstractEntity{

    private String licencePlate;
    private String model;
    private String tireType;

    /**
     * car parametric constructor
     * @param licencePlate
     * @param model
     */
    public Car(String licencePlate, String model) throws InvalidArgumentException {
        if(licencePlate.isEmpty()){
            throw new InvalidArgumentException("Licence plate cannot be empty");
        }
        this.licencePlate = licencePlate;
        this.model = model;
    }

    /**
     * car nonparametric constructor
     */
    public Car() {
    }

    /**
     * get licence plate
     * @return licence plate
     */
    public String getLicencePlate() {
        return licencePlate;
    }

    /**
     * set licence plate
     * @param licencePlate
     */
    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    /**
     * get model
     * @return model
     */
    public String getModel() {
        return model;
    }

    /**
     * set model
     * @param model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * get tire type
     * @return tire type
     */
    public String getTireType() {
        return tireType;
    }

    /**
     * set tire type
     * @param tireType
     */
    public void setTireType(String tireType) {
        this.tireType = tireType;
    }

}
