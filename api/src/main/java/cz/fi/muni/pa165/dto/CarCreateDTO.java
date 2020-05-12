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
     * get licence plate string
     * @return licence plate string
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
