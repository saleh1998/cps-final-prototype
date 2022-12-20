package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.ParkingLots;
import il.cshaifasweng.OCSFMediatorExample.entities.Prices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import java.io.IOException;
import java.util.List;

public class PricesTable {

    @FXML
    private TableColumn<Prices, Integer> Fullmem;

    @FXML
    private TableColumn<Prices, Integer> inadvance;

    @FXML
    private TableColumn<Prices, Integer> inplace;

    @FXML
    private TableColumn<Prices, Integer> idprices;
    @FXML
    private TableColumn<Prices, Integer> regmultiple;

    @FXML
    private TableColumn<Prices, Integer> regsingle;

    @FXML
    private TableView<Prices> tableview2;

    @FXML
    private List<Prices> plist;
    private int msgId;

    public PricesTable() {}

    public PricesTable(List<Prices> pricesList1) {
        plist=pricesList1;
    }

    @FXML
    void initData(List<Prices> pricesList1){
        plist = pricesList1;
    }

    @FXML
    void setMsgId(int msgId1){
        this.msgId = msgId1;
    }

    @FXML
    void setpTable(){
        try {
			Message message = new Message(msgId, "print prices table");
			SimpleClient.getClient().sendToServer(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Subscribe
    public void setpTableviewFromServer(showptableEvent event)
    {

        idprices.setCellValueFactory(new PropertyValueFactory<>("id"));
        inadvance.setCellValueFactory(new PropertyValueFactory<>("in_Advance_price"));
        inplace.setCellValueFactory(new PropertyValueFactory<>("in_place_price"));
        regsingle.setCellValueFactory(new PropertyValueFactory<>("single_car_reg_mem_price"));
        regmultiple.setCellValueFactory(new PropertyValueFactory<>("multiple_cars_reg_mem_price"));
        Fullmem.setCellValueFactory(new PropertyValueFactory<>("full_mem_price"));

        ObservableList<Prices> prices = FXCollections.observableArrayList();
// Add ParkingLot objects to the list

        tableview2.setItems(prices);

        for (Prices p : event.getPlist()){
            tableview2.getItems().add(p);
        }
    }

    public void initialize() {
        EventBus.getDefault().register(this);
    }
}
