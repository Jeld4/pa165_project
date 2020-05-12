package cz.fi.muni.pa165.entity;

import cz.fi.muni.pa165.enums.OrderState;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Class representing cz.fi.muni.pa165.entity Order
 * @author Jan Jelínek
 */
@Entity
@Table(name="ORDER_ITEM")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int totalPrice;

    private Date dateOfOrder;

    @Enumerated
    private OrderState state;

    @OneToMany
    private List<Tire> tires;
    @OneToMany
    private List<Service> services;

    @ManyToOne(optional=false)
    @NotNull
    private User user;

    /**
     * order nonparametric constructor
     */
    public Order() {
        this.tires = new ArrayList<>();
        this.services = new ArrayList<>();
    }

    /**
     * order parametric constructor
     * @param totalPrice
     * @param dateOfOrder
     * @param state
     */
    public Order(int totalPrice, Date dateOfOrder, OrderState state) {
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
    public int getTotalPrice() {
        return totalPrice;
    }

    /**
     * set total price
     * @param totalPrice
     */
    public void setTotalPrice(int totalPrice) {
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