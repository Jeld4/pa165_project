package entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;


/**
 * @author Radim Sasinka, 456315
 */

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String licencePlate;
    private String model;
    private String tireType; //TODO Potentially refactor to Tire type

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTireType() {
        return tireType;
    }

    public void setTireType(String tireType) {
        this.tireType = tireType;
    }

    public Car(String licencePlate, String model) {
        this.licencePlate = licencePlate;
        this.model = model;
    }

    public Car() {
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
