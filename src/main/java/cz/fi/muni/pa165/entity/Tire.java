package cz.fi.muni.pa165.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Objects;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
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

    public Tire() {
    }

    public Tire(String manufacturer, String type, int size, String season, int price) {
        this.manufacturer = manufacturer;
        this.type = type;
        this.size = size;
        this.season = season;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long i) {
        id = i;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public int getPrice() {
        return price;
    }

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
