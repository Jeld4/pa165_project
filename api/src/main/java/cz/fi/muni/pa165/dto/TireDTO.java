package cz.fi.muni.pa165.dto;

import java.util.Objects;


/**
 * @author Jakub Malý, 456389
 */
public class TireDTO {

    private long id;
    private String manufacturer;
    private String type;
    private int size;
    private String season;
    private int price;

    public long getId() {
        return id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getType() {
        return type;
    }

    public int getSize() {
        return size;
    }

    public String getSeason() {
        return season;
    }

    public int getPrice() {
        return price;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TireDTO tireDTO = (TireDTO) o;
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
