package cz.fi.muni.pa165.dto;

import java.math.BigDecimal;
import java.util.Objects;


/**
 * @author Jakub Malý, 456389
 */
public class TireDTO {

    private long id;
    private String manufacturer;
    private String type;
    private BigDecimal size;
    private String season;
    private BigDecimal price;

    /**
     * Method used to get id of the tire
     * @return id of the tire
     */
    public long getId() {
        return id;
    }

    /**
     * Method used to get manufacturer of the tire
     * @return manufacturer of the tire
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Method used to get type of tire
     * @return type of the tire
     */
    public String getType() {
        return type;
    }

    /**
     * Method used to get size of the tire
     * @return size of the tire
     */
    public BigDecimal getSize() {
        return size;
    }

    /**
     * Method used to get season, when the tire should be used
     * @return season of the year
     */
    public String getSeason() {
        return season;
    }

    /**
     * Method to get price of the tire
     * @return price of the tire
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Method used to set id of the tire
     * @param id of the tire
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Method to set the manufacturer of the tire
     * @param manufacturer of the tire
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * Method to set type of the tire
     * @param type of tire
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Method used to set size of the tire
     * @param size of the tire
     */
    public void setSize(BigDecimal size) {
        this.size = size;
    }

    /**
     * Method used to set season, when the tire should be used
     * @param season of the year
     */
    public void setSeason(String season) {
        this.season = season;
    }

    /**
     * Method used to set price of the tire
     * @param price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        TireDTO tireDTO = (TireDTO) object;
        return id == tireDTO.id &&
                size == tireDTO.size &&
                price == tireDTO.price &&
                Objects.equals(manufacturer, tireDTO.manufacturer) &&
                Objects.equals(type, tireDTO.type) &&
                Objects.equals(season, tireDTO.season);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, manufacturer, type, size, season, price);
    }
}
