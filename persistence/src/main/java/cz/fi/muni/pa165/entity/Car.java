package cz.fi.muni.pa165.entity;


import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * class representing car
 * @author Radim Sasinka, 456315
 */
@Entity
@Table(name = "Cars")
public class Car {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String licencePlate;

    @NotEmpty
    private String model;

    @NotEmpty
    private String tireType;

    /**
     * car parametric constructor
     * @param licencePlate
     * @param model
     */
    public Car(String licencePlate, String model, String tireType){
        if(licencePlate.isEmpty()){
            throw new IllegalArgumentException("Licence plate cannot be empty");
        }
        if(model.isEmpty()){
            throw new IllegalArgumentException("Car model cannot be empty");
        }

        if(tireType.isEmpty()){
            throw new IllegalArgumentException("Tire type cannot be empty");
        }
        this.licencePlate = licencePlate;
        this.model = model;
        this.tireType = tireType;
    }

    /**
     * car nonparametric constructor
     */
    public Car() {
    }

    /**
     * get ID
     * @return ID
     */
    public Long getId() {
        return id;
    }

    /**
     * set ID
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
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
