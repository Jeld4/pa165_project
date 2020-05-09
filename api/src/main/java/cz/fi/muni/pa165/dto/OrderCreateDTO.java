package cz.fi.muni.pa165.dto;

import cz.fi.muni.pa165.enums.OrderState;

import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 * @author Jan Jelínek 445416
 */
public class OrderCreateDTO {

    private UserDTO user;

    private Date dateOfOrder;

    private OrderState state;

    private List<TireDTO> tires;

    private List<ServiceDTO> services;

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Date getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(Date dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public List<TireDTO> getTires() {
        return tires;
    }

    public void setTires(List<TireDTO> tires) {
        this.tires = tires;
    }

    public List<ServiceDTO> getServices() {
        return services;
    }

    public void setServices(List<ServiceDTO> services) {
        this.services = services;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderCreateDTO that = (OrderCreateDTO) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(dateOfOrder, that.dateOfOrder) &&
                state == that.state &&
                Objects.equals(tires, that.tires) &&
                Objects.equals(services, that.services);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, dateOfOrder, state, tires, services);
    }

    @Override
    public String toString() {
        return "OrderCreateDTO{" +
                "user=" + user +
                ", dateOfOrder=" + dateOfOrder +
                ", state=" + state +
                ", tires=" + tires +
                ", services=" + services +
                '}';
    }
}
