package il.cshaifasweng.OCSFMediatorExample.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "parkinglotss")
public class ParkingLots implements Serializable {
@Id
@Column(name = "id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
int id;
@Column(name = "num_of_rows")
int num_of_rows;
    @Column(name = "num_of_parking_spots")
    int parking_spots;

    public ParkingLots() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum_of_rows() {
        return num_of_rows;
    }

    public int getParking_spots() {
        return parking_spots;
    }

    public void setParking_spots(int parking_spots) {
        this.parking_spots = parking_spots;
    }

    public ParkingLots(int id, int num_of_rows, int parking_spots) {
        this.id = id;
        this.num_of_rows = num_of_rows;
        this.parking_spots = parking_spots;
    }

    public ParkingLots(int num_of_rows) {
        this.num_of_rows = num_of_rows;
        this.parking_spots = 9*num_of_rows;
    }

    public void setNum_of_rows(int num_of_rows) {
        this.num_of_rows = num_of_rows;
    }
}
