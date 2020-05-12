package cz.fi.muni.pa165.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Objects;

/**
 * class representing tire
 * @author Jakub Malý, 456389
 */
@Entity
@Table(name = "Tire", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Tire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String manufacturer;
    private String type;
    private int size;
    private String season;
    private int price;

    /**
     * tire nonparametric constructor
     */
    public Tire() {
    }

    /**
     * tire parametric constructor
     * @param manufacturer
     * @param type
     * @param size
     * @param season
     * @param price
     */
    public Tire(String manufacturer, String type, int size, String season, int price) {
        this.manufacturer = manufacturer;
        this.type = type;
        this.size = size;
        this.season = season;
        this.price = price;
    }

    /**
     * get ID
     * @return ID
     */
    public Long getId() {
        return id;
    }

    /**
     * set ID
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * get manufacturer
     * @return manufacturer
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * set manufacturer
     * @param manufacturer
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * get type
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * set type
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * get size
     * @return size
     */
    public int getSize() {
        return size;
    }

    /**
     * set size
     * @param size
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * get season
     * @return season
     */
    public String getSeason() {
        return season;
    }

    /**
     * set season
     * @param season
     */
    public void setSeason(String season) {
        this.season = season;
    }

    /**
     * get price
     * @return price
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
        Tire tire = (Tire) o;
        return Objects.equals(id, tire.id) &&
                Objects.equals(type, tire.type) &&
                Objects.equals(size, tire.size) &&
                Objects.equals(season, tire.season) &&
                Objects.equals(price, tire.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, manufacturer, type, size, season, price);
    }
}
