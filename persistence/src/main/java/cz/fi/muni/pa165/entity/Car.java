package cz.fi.muni.pa165.entity;


import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Objects;


/**
 * class representing car
 * @author Radim Sasinka, 456315
 */

@Entity
@Table(name="Car", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Car extends AbstractEntity{
public class Car {

    private String licencePlate;
    private String model;
    private String tireType;

    /**
     * car parametric constructor
     * @param licencePlate
     * @param model
     */
    public Car(String licencePlate, String model){
        if(licencePlate.isEmpty()){
            throw new IllegalArgumentException("Licence plate cannot be empty");
        }
        if(model.isEmpty()){
            throw new IllegalArgumentException("Car model cannot be empty");
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
