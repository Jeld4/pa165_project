package cz.fi.muni.pa165.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author Radim Sasinka
 */
public class ServiceCreateDTO implements Serializable {

    @NotEmpty
    @Size(min = 3, max = 50)
    private String name;
    @NotEmpty
    @Size(min = 3, max = 200)
    private String description;

    private BigDecimal price;

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
    public String toString() {
        return "ServiceCreateDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceCreateDTO that = (ServiceCreateDTO) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, price);
    }
}
