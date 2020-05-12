package cz.fi.muni.pa165.dto;

import java.util.Objects;

/**
 * @author Radim Sasinka
 */
public class CarDTO {

    private Long Id;
    private String licencePlate;
    private String model;
    private String tireType;

    /**
     * get ID
     * @return ID
     */
    public Long getId() {
        return Id;
    }

    /**
     * set ID
     * @param id
     */
    public void setId(Long id) {
        Id = id;
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
        CarDTO carDTO = (CarDTO) o;
        return Id.equals(carDTO.Id) &&
                licencePlate.equals(carDTO.licencePlate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, licencePlate);
    }
}
