package cz.fi.muni.pa165.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * class representing service
 * @author Radim Sasinka, 456315
 */

@Entity
@Table(name = "Services")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private BigDecimal price;

    /**
     * service nonparametric constructor
     */
    public Service() {
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
     * @return price
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
}
