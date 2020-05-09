package cz.fi.muni.pa165.dto;

import cz.fi.muni.pa165.enums.OrderState;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Jan Jelínek 445416
 */
public class OrderDTO {

    private Long id;

    private int totalPrice;

    private Date dateOfOrder;

    private OrderState state;

    private List<TireDTO> tires;

    private List<ServiceDTO> services;

    private UserDTO user;

    /**
     * get user
     * @return user
     */
    public UserDTO getUser() {
        return user;
    }

    /**
     * set user
     * @param user
     */
    public void setUser(UserDTO user) {
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
    public List<ServiceDTO> getServices() {
        return services;
    }

    /**
     * set services
     * @param services
     */
    public void setServices(List<ServiceDTO> services) {
        this.services = services;
    }

    /**
     * get total price
     * @return
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
    public List<TireDTO> getTires() {
        return tires;
    }

    /**
     * set tires
     * @param tires
     */
    public void setTires(List<TireDTO> tires) {
        this.tires = tires;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDTO order = (OrderDTO) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
