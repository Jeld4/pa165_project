package cz.fi.muni.pa165.dto;

import cz.fi.muni.pa165.enums.OrderState;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Jan Jelínek 445416
 */
public class OrderDTO {

    private Long id;

    private BigDecimal totalPrice;

    private Date dateOfOrder;

    private OrderState state;

    private List<TireDTO> tires;

    private List<ServiceDTO> services;

    private UserDTO user;

    private CarDTO car;

    public CarDTO getCar() {
        return car;
    }

    public void setCar(CarDTO car) {
        this.car = car;
    }

    /**
     * Method used to get user of the order
     * @return user of the order
     */
    public UserDTO getUser() {
        return user;
    }

    /**
     * Method to set user of the order
     * @param user of the order
     */
    public void setUser(UserDTO user) {
        this.user = user;
    }

    /**
     * Method used to get id of the order
     * @return id of the order
     */
    public Long getId() {
        return id;
    }

    /**
     * Method which return the date of the order
     * @return date of the order
     */
    public Date getDateOfOrder() {
        return dateOfOrder;
    }

    /**
     * Method used to get state of the order
     * @return state of the order
     */
    public OrderState getState() {
        return state;
    }

    /**
     * Method used to get services of the order
     * @return services of the order
     */
    public List<ServiceDTO> getServices() {
        return services;
    }

    /**
     * Method used to set services of the order
     * @param services of the order
     */
    public void setServices(List<ServiceDTO> services) {
        this.services = services;
    }

    /**
     * Method to get total price of the order
     * @return total price of order
     */
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * Method used to set the total price of the order
     * @param totalPrice of the order
     */
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * Method to set date of the order
     * @param dateOfOrder date when the order is created
     */
    public void setDateOfOrder(Date dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    /**
     * Method used to set state of the order
     * @param state of the order
     */
    public void setState(OrderState state) {
        this.state = state;
    }

    /**
     * Method used to set id of the order
     * @param id of the order
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Method used to get tires included in order
     * @return tires within the order
     */
    public List<TireDTO> getTires() {
        return tires;
    }

    /**
     * Method to set tires of the order
     * @param tires of the order
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
