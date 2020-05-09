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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order() {
        this.tires = new ArrayList<>();
        this.services = new ArrayList<>();
    }

    public Order(int totalPrice, Date dateOfOrder, OrderState state) {
        this.totalPrice = totalPrice;
        this.dateOfOrder = dateOfOrder;
        this.state = state;
        this.tires = new ArrayList<>();
        this.services = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public Date getDateOfOrder() {
        return dateOfOrder;
    }

    public OrderState getState() {
        return state;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setDateOfOrder(Date dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Tire> getTires() {
        return tires;
    }

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