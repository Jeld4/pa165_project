package entity;

import enums.OrderState;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Class representing entity Order
 * @author Jan Jelínek
 */
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int totalPrice;

    private Date dateOfOrder;

    @Enumerated
    private OrderState state;

    private List<Tire> tires;

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
