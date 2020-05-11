package cz.fi.muni.pa165.dto;

import cz.fi.muni.pa165.enums.OrderState;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


/**
 * @author Jan Jelínek 445416
 */
public class OrderCreateDTO {

    private UserDTO user;

    private Date dateOfOrder;

    private OrderState state;

    private List<TireDTO> tires;

    private List<ServiceDTO> services;

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
     * Method which return the date of the order
     * @return date of the order
     */
    public Date getDateOfOrder() {
        return dateOfOrder;
    }

    /**
     * Method to set date of the order
     * @param dateOfOrder
     */
    public void setDateOfOrder(Date dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    /**
     * Method used to get state of the order
     * @return state of the order
     */
    public OrderState getState() {
        return state;
    }

    /**
     * Method used to set state of the order
     * @param state of the order
     */
    public void setState(OrderState state) {
        this.state = state;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        result = prime * result + ((dateOfOrder == null) ? 0 : dateOfOrder.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        result = prime * result + ((tires == null) ? 0 : tires.hashCode());
        result = prime * result + ((services == null) ? 0 : services.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OrderCreateDTO other = (OrderCreateDTO) obj;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        if (dateOfOrder == null) {
            if (other.dateOfOrder != null)
                return false;
        } else if (!dateOfOrder.equals(other.dateOfOrder))
            return false;
        if (state == null) {
            if (other.state != null)
                return false;
        } else if (!state.equals(other.state))
            return false;
        if (tires == null) {
            if (other.tires != null)
                return false;
        } else if (!tires.equals(other.tires))
            return false;
        if (services == null) {
            if (other.services != null)
                return false;
        } else if (!services.equals(other.services))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "OrderCreateDTO{" +
                "user='" + user + '\'' +
                ", date of order ='" + dateOfOrder + '\'' +
                ", state =" + state +
                '}';
    }
}
