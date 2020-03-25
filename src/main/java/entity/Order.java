package entity;

import enums.OrderState;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Class representing entity Order
 * @author Jan Jelínek
 */
@Entity
@Table(name="\"Order\"")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int totalPrice;

    private Date dateOfOrder;

    @Enumerated
    private OrderState state;

    public List<Tire> getTires() {
        return tires;
    }

    public void setTires(List<Tire> tires) {
        this.tires = tires;
    }

    @OneToMany(mappedBy="id", cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST})
    private List<Tire> tires;

    @OneToMany(mappedBy="id", cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST})
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

    // TODO equals and hash method
}
