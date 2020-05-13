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
     * Method to get id of the service
     * @return id of the service
     */
    public Long getId() {
        return id;
    }

    /**
     * Method used to set id of the service
     * @param id of the service
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Method used to get name of the service
     * @return name of service
     */
    public String getName() {
        return name;
    }

    /**
     * Method used to set name of the service
     * @param name of the service
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method used to get description of the service
     * @return description of the service
     */
    public String getDescription() {
        return description;
    }

    /**
     * Method used to set description of the service
     * @param description of the service
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Method used to get price of the service
     * @return price of the service
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Method used to set the price of the service
     * @param price of the service
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
