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

    /**
     * get ID
     * @return ID
     */
    public long getId() {
        return id;
    }

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
     * get size
     * @return size
     */
    public int getSize() {
        return size;
    }

    /**
     * get season
     * @return season
     */
    public String getSeason() {
        return season;
    }

    /**
     * get price
     * @return price
     */
    public int getPrice() {
        return price;
    }

    /**
     * set ID
     * @param id
     */
    public void setId(long id) {
        this.id = id;
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
     * set size
     * @param size
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * set season
     * @param season
     */
    public void setSeason(String season) {
        this.season = season;
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
