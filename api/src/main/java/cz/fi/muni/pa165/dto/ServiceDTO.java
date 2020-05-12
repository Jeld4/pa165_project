package cz.fi.muni.pa165.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
/**
 * @author Radim Sasinka
 */
public class ServiceDTO implements Serializable {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;

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
     * get name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * set name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get description
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * set description
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * get price
     * @return
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * set price
     * @param price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceDTO that = (ServiceDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
