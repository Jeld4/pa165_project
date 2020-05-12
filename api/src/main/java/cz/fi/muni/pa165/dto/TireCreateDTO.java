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
     * get manufacturer
     * @return manufacturer
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * get type
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * set manufacturer
     * @param manufacturer
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * set type
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * get price
     * @return
     */
    public int getPrice() {
        return price;
    }

    /**
     * set price
     * @param price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TireCreateDTO that = (TireCreateDTO) o;
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
