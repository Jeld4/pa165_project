package cz.fi.muni.pa165.entity;


import javax.persistence.*;
import java.util.Objects;


/**
 * class representing car
 * @author Radim Sasinka, 456315
 */

@Entity
@Table(name="Car", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String licencePlate;
    private String model;
    private String tireType;

    /**
     * car parametric constructor
     * @param licencePlate
     * @param model
     */
    public Car(String licencePlate, String model) {
        this.licencePlate = licencePlate;
        this.model = model;
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
        return Id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(Id, car.Id) &&
                Objects.equals(licencePlate, car.licencePlate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, licencePlate);
    }
}
