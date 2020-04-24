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


    public String getManufacturer() {
        return manufacturer;
    }

    public String getType() {
        return type;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        TireCreateDTO that = (TireCreateDTO) object;
        return price == that.price &&
                java.util.Objects.equals(manufacturer, that.manufacturer) &&
                java.util.Objects.equals(type, that.type);
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), manufacturer, type, price);
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "TireCreateDTO{" +
                "manufacturer='" + manufacturer + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                '}';
    }

}
