package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.ParkingLots;
import il.cshaifasweng.OCSFMediatorExample.entities.Prices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.greenrobot.eventbus.Subscribe;

public class PricesTableController {

    @FXML
    private TableColumn<Prices,Integer> fullmem;

    @FXML
    private TableColumn<Prices,Integer> inadvance;

    @FXML
    private TableColumn<Prices,Integer> inplace;

    @FXML
    private TableColumn<Prices,Integer> regmultiple;

    @FXML
    private TableColumn<Prices,Integer> regsignle;

    @FXML
    private TableView<Prices> tableview2;


    @Subscribe
    public void setpTableviewFromServer(showptableEvent event)
    {
        System.out.println("set ptable attempt");
        inadvance.setCellValueFactory(new PropertyValueFactory<>("in advance"));

        inplace.setCellValueFactory(new PropertyValueFactory<>("in place"));

        regsignle.setCellValueFactory(new PropertyValueFactory<>("regular membership - single"));
        regmultiple.setCellValueFactory(new PropertyValueFactory<>("regular membership - multiple"));

        fullmem.setCellValueFactory(new PropertyValueFactory<>("Full membership"));

        ObservableList<Prices> prices = FXCollections.observableArrayList();
// Add ParkingLot objects to the list

        tableview2.setItems(prices);

        for (Prices p : event.getPlist()){
            tableview2.getItems().add(p);
        }



    }


}
