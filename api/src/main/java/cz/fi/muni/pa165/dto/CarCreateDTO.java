package cz.fi.muni.pa165.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Radim Sasinka
 */

public class CarCreateDTO implements Serializable {

    @NotNull
    @Size(min = 4, max=20)
    private String licencePlate;
    @NotNull
    private String model;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarCreateDTO that = (CarCreateDTO) o;
        return Objects.equals(licencePlate, that.licencePlate) &&
                Objects.equals(model, that.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(licencePlate, model);
    }

    @Override
    public String toString() {
        return "CarCreateDTO{" +
                "licencePlate='" + licencePlate + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
