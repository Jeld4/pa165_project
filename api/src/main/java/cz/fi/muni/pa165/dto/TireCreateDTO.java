package cz.fi.muni.pa165.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;


/**
 * @author Jakub Malý, 456389
 */
public class TireCreateDTO {

    @NotEmpty
    @Size(min = 2, max=32)
    private String manufacturer;

    @NotEmpty
    @Size(min = 3, max=32)
    private String type;

    @NotEmpty
    private BigDecimal size;

    @NotEmpty
    @Size(min = 3, max=32)
    private String season;

    @NotEmpty
    private BigDecimal price;

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
     * Method to get season
     * @return season
     */
    public String getSeason() {
        return season;
    }

    /**
     * Method to set season
     * @param season
     */
    public void setSeason(String season) {
        this.season = season;
    }

    /**
     * Method to get size
     * @return size
     */
    public BigDecimal getSize() {
        return size;
    }

    /**
     * Method to set size
     * @param size
     */
    public void setSize(BigDecimal size) {
        this.size = size;
    }

    /**
     * Method used to get price of the tire
     * @return price of the tire
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Method used to set price of hte tire
     * @param price of the tire
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TireCreateDTO that = (TireCreateDTO) o;
        return Objects.equals(manufacturer, that.manufacturer) &&
                Objects.equals(type, that.type) &&
                Objects.equals(size, that.size) &&
                Objects.equals(season, that.season) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manufacturer, type, size, season, price);
    }

    @Override
    public String toString() {
        return "TireCreateDTO{" +
                "manufacturer='" + manufacturer + '\'' +
                ", type='" + type + '\'' +
                ", size=" + size +
                ", season='" + season + '\'' +
                ", price=" + price +
                '}';
    }

}
