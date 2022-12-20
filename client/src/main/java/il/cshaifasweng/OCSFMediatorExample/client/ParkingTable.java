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
        import javafx.stage.Stage;
        import org.greenrobot.eventbus.EventBus;
        import org.greenrobot.eventbus.Subscribe;
        import java.io.IOException;
        import java.time.LocalTime;
        import java.time.format.DateTimeFormatter;

        import il.cshaifasweng.OCSFMediatorExample.entities.Message;
        import il.cshaifasweng.OCSFMediatorExample.entities.ParkingLots;
        import il.cshaifasweng.OCSFMediatorExample.entities.Prices;
        import javafx.animation.Animation;
        import javafx.animation.KeyFrame;
        import javafx.animation.Timeline;
        import javafx.application.Platform;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Node;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.*;
        import javafx.scene.control.cell.PropertyValueFactory;
        import javafx.stage.Stage;
        import javafx.stage.StageStyle;
        import javafx.util.Duration;
        import org.greenrobot.eventbus.EventBus;
        import org.greenrobot.eventbus.Subscribe;

        import java.io.IOException;
        import java.util.List;

public class ParkingTable {

    @FXML
    private TableColumn<ParkingLots, Integer> idpark;

    @FXML
    private TableColumn<ParkingLots, Integer> rowsnum;

    @FXML
    private TableColumn<ParkingLots, Integer> spots;

    @FXML
    private TableView<ParkingLots> tableview;

    @FXML
    private List<ParkingLots> parkingLotsList;
    private int msgId;

    public ParkingTable() {}

    public ParkingTable(List<ParkingLots> parkingLotsList1) {
        parkingLotsList=parkingLotsList1;
    }
    @FXML
    void initData(List<ParkingLots> parkingLotsList1){

        parkingLotsList=parkingLotsList1;
    }


    @FXML
    void setMsgId(int msgId1){
        this.msgId = msgId1;
    }

    @FXML
    void setTable(){
        try {
            Message message = new Message(msgId, "print parking table");
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Subscribe
    public void setTableviewFromServer(showTableEvent event)
    {

        idpark.setCellValueFactory(new PropertyValueFactory<>("id"));
        rowsnum.setCellValueFactory(new PropertyValueFactory<>("num_of_rows"));
        spots.setCellValueFactory(new PropertyValueFactory<>("parking_spots"));

        ObservableList<ParkingLots> parkingLots = FXCollections.observableArrayList();
// Add ParkingLot objects to the list
        tableview.setItems(parkingLots);
        for (ParkingLots p : event.getList()){
            tableview.getItems().add(p);
        }
    }
    @FXML
    public void initialize() {
        EventBus.getDefault().register(this);


    }
}
