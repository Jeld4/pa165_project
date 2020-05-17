package cz.fi.muni.pa165.entity;

import cz.fi.muni.pa165.enums.OrderState;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Class representing cz.fi.muni.pa165.entity Order
 * @author Jan Jelínek
 */
@Entity
@Table(name = "Orders")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal totalPrice;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfOrder;

    @Enumerated
    private OrderState state;

    @OneToMany
    private List<Tire> tires;
    @OneToMany
    private List<Service> services;

    @ManyToOne(optional=false)
    private User user;

    /**
     * order nonparametric constructor
     */
    public Order() {
        this.dateOfOrder = new Date();
        this.tires = new ArrayList<>();
        this.services = new ArrayList<>();
        this.state = OrderState.PENDING;
    }

    /**
     * order parametric constructor
     * @param totalPrice
     * @param dateOfOrder
     * @param state
     */
    public Order(BigDecimal totalPrice, Date dateOfOrder, OrderState state) {
        this.totalPrice = totalPrice;
        this.dateOfOrder = dateOfOrder;
        this.state = state;
        this.tires = new ArrayList<>();
        this.services = new ArrayList<>();
    }

    /**
     * get user
     * @return user
     */
    public User getUser() {
        return user;
    }

    /**
     * set user
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * get ID
     * @return ID
     */
    public Long getId() {
        return id;
    }

    /**
     * get date of order
     * @return date of order
     */
    public Date getDateOfOrder() {
        return dateOfOrder;
    }

    /**
     * get state
     * @return state
     */
    public OrderState getState() {
        return state;
    }

    /**
     * get services
     * @return services
     */
    public List<Service> getServices() {
        return services;
    }

    /**
     * set services
     * @param services
     */
    public void setServices(List<Service> services) {
        this.services = services;
    }

    /**
     * get total price
     * @return total price
     */
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * set total price
     * @param totalPrice
     */
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * set date of order
     * @param dateOfOrder
     */
    public void setDateOfOrder(Date dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    /**
     * set state
     * @param state
     */
    public void setState(OrderState state) {
        this.state = state;
    }

    /**
     * set ID
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * get tires
     * @return tires
     */
    public List<Tire> getTires() {
        return tires;
    }

    /**
     * set tires
     * @param tires
     */
    public void setTires(List<Tire> tires) {
        this.tires = tires;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}