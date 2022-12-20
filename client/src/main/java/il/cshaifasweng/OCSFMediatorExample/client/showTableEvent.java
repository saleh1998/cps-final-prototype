package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.ParkingLots;

import java.util.List;

public class showTableEvent {
   private List<ParkingLots> list;

    public List<ParkingLots> getList() {
        return list;
    }

    public void setList(List<ParkingLots> list) {
        this.list = list;
    }

    public showTableEvent(List<ParkingLots> list) {
        this.list = list;
    }


    public void printlist()
    {
        for(ParkingLots p : list)
        {
            System.out.println("id is "+p.getId()+"num of rows is "+p.getNum_of_rows()+"num of spots is"+p.getParking_spots());
        }


    }
}
