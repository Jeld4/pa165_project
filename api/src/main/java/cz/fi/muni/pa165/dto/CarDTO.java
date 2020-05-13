package cz.fi.muni.pa165.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Radim Sasinka
 */
public class CarDTO implements Serializable {

    private Long Id;
    private String licencePlate;
    private String model;
    private String tireType;

    /**
     * Method which returns id of the car
     * @return id of the car
     */
    public Long getId() {
        return Id;
    }

    /**
     * Method used to set id of the car
     * @param id of car
     */
    public void setId(Long id) {
        Id = id;
    }

    /**
     * This method is used to get licence plate of the car
     * @return licence plate
     */
    public String getLicencePlate() {
        return licencePlate;
    }

    /**
     * This method is used to set licence plate of the car
     * @param licencePlate to be assigned to the car
     */
    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    /**
     * Method to get model of the car
     * @return model of the car
     */
    public String getModel() {
        return model;
    }

    /**
     * Method used to set model of the car
     * @param model of the car
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * This method is used to get tires type of the car
     * @return tires type of car
     */
    public String getTireType() {
        return tireType;
    }

    /**
     * This method is used to set tires type of the car
     * @param tireType type of tires
     */
    public void setTireType(String tireType) {
        this.tireType = tireType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarDTO carDTO = (CarDTO) o;
        return Id.equals(carDTO.Id) &&
                licencePlate.equals(carDTO.licencePlate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, licencePlate);
    }
}
