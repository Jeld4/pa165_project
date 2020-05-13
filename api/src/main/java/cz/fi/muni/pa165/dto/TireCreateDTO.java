package cz.fi.muni.pa165.dto;

import javax.validation.constraints.NotNull;
import java.util.Objects;


/**
 * @author Jakub Malý, 456389
 */
public class TireCreateDTO {

    @NotNull
    private String manufacturer;

    @NotNull
    private String type;

    private int price;

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
     * Method used to get price of the tire
     * @return price of the tire
     */
    public int getPrice() {
        return price;
    }

    /**
     * Method used to set price of hte tire
     * @param price of the tire
     */
    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        TireCreateDTO that = (TireCreateDTO) object;
        return price == that.price &&
                Objects.equals(manufacturer, that.manufacturer) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manufacturer, type, price);
    }

    @Override
    public String toString() {
        return "TireCreateDTO{" +
                "manufacturer='" + manufacturer + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                '}';
    }
}
