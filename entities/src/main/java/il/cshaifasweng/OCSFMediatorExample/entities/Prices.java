package il.cshaifasweng.OCSFMediatorExample.entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "prices")
public class Prices implements Serializable {
@Id
@Column(name = "id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
 @Column(name = "in_advance")
private int in_Advance_price;
@Column(name = "in_place")
private int in_place_price;
@Column(name = "regular_membership_single")
private int single_car_reg_mem_price;
@Column(name = "regular_membership_multiple")
private int multiple_cars_reg_mem_price;
@Column(name = "Full_membership")
private int full_mem_price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Prices(int id, int in_Advance_price, int in_place_price, int single_car_reg_mem_price, int multiple_cars_reg_mem_price, int full_mem_price) {
        this.id = id;
        this.in_Advance_price = in_Advance_price;
        this.in_place_price = in_place_price;
        this.single_car_reg_mem_price = single_car_reg_mem_price;
        this.multiple_cars_reg_mem_price = multiple_cars_reg_mem_price;
        this.full_mem_price = full_mem_price;
    }

    public Prices(int in_Advance_price, int in_place_price, int single_car_reg_mem_price, int multiple_cars_reg_mem_price, int full_mem_price) {
        this.in_Advance_price = in_Advance_price;
        this.in_place_price = in_place_price;
        this.single_car_reg_mem_price = single_car_reg_mem_price;
        this.multiple_cars_reg_mem_price = multiple_cars_reg_mem_price;
        this.full_mem_price = full_mem_price;
    }

    public Prices() {
        this.in_Advance_price = 0;
        this.in_place_price = 0;
        this.single_car_reg_mem_price = 0;
        this.multiple_cars_reg_mem_price = 0;
        this.full_mem_price = 0;
    }

    public int getIn_Advance_price() {
        return in_Advance_price;
    }

    public void setIn_Advance_price(int in_Advance_price) {
        this.in_Advance_price = in_Advance_price;
    }

    public int getIn_place_price() {
        return in_place_price;
    }

    public void setIn_place_price(int in_place_price) {
        this.in_place_price = in_place_price;
    }

    public int getSingle_car_reg_mem_price() {
        return single_car_reg_mem_price;
    }

    public void setSingle_car_reg_mem_price(int single_car_reg_mem_price) {
        this.single_car_reg_mem_price = single_car_reg_mem_price;
    }

    public int getMultiple_cars_reg_mem_price() {
        return multiple_cars_reg_mem_price;
    }

    public void setMultiple_cars_reg_mem_price(int multiple_cars_reg_mem_price) {
        this.multiple_cars_reg_mem_price = multiple_cars_reg_mem_price;
    }

    public int getFull_mem_price() {
        return full_mem_price;
    }

    public void setFull_mem_price(int full_mem_price) {
        this.full_mem_price = full_mem_price;
    }
}
