package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Message implements Serializable {
    int id;
    LocalDateTime timeStamp;
    String message;
    String data;
    String Action;
    List<ParkingLots> list;
    List<Prices> plist;
    int change_prices[];

    public int[] getChange_prices() {
        return change_prices;
    }

    public void setChange_prices(int[] change_prices) {
        this.change_prices = change_prices;
    }

    public Message(int[] change_prices) {
        this.change_prices = change_prices;
    }

    public List<Prices> getPlist() {
        return plist;
    }

    public void setPlist(List<Prices> plist) {
        this.plist = plist;
    }

    public List<ParkingLots> getList() {
        return list;
    }

    public Message(List<ParkingLots> list) {
        this.list = list;
    }

    public void setList(List<ParkingLots> list) {
        this.list = list;


    }

    public String getAction() {
        return Action;
    }

    public void setAction(String action) {
        Action = action;
    }

    public Message(int id, LocalDateTime timeStamp, String message) {
        this.id = id;
        this.timeStamp = timeStamp;
        this.message = message;
    }

    public Message(int id, String message) {
        this.id = id;
        this.timeStamp = LocalDateTime.now();
        this.message = message;
        this.data = null;
        this.change_prices = new int[4];
    }

    public Message(int id, String message,String data) {
        this.id = id;
        this.timeStamp = LocalDateTime.now();
        this.message = message;
        this.data = data;
        this.change_prices = new int[4];
    }

    public Message(int id, LocalDateTime timeStamp, String message, String data, String action) {
        this.id = id;
        this.timeStamp = timeStamp;
        this.message = message;
        this.data = data;
        this.Action = action;
        this.change_prices = new int[4];
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
