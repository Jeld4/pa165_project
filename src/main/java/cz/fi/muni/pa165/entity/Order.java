package cz.fi.muni.pa165.entity;

import cz.fi.muni.pa165.enums.OrderState;

import javax.persistence.*;
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

<<<<<<< HEAD:src/main/java/entity/Order.java
=======
    public List<Tire> getTires() {
        return tires;
    }

    public void setTires(List<Tire> tires) {
        this.tires = tires;
    }
    @OneToMany
>>>>>>> master:src/main/java/cz/fi/muni/pa165/entity/Order.java
    private List<Tire> tires;
    @OneToMany
    private List<Service> services;

    public Long getId() {
        return id;
    }

    public Date getDateOfOrder() {
        return dateOfOrder;
    }

    public OrderState getState() {
        return state;
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

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
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
