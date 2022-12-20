package il.cshaifasweng.OCSFMediatorExample.client;


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

public class PrimaryController {



		@FXML
		private Button changepricesbtn;

		@FXML
		private Button showBtn;

		@FXML
		private Button showpBtn;
	private int msgId;


	@FXML
	void openChangePricesScene(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("changeprices.fxml"));
		Stage stage = new Stage();
		stage.setScene(new Scene(loader.load()));
		stage.show();
	}



//IMPORTANT
	public void showpTable(ActionEvent actionEvent) throws IOException {

		ObservableList<Prices> prices = FXCollections.observableArrayList();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("pricesTable.fxml"));
		Stage stage = new Stage();
		stage.setScene(new Scene(loader.load()));
		PricesTable pricesTable = loader.getController();
		pricesTable.initData(prices);
		pricesTable.setMsgId(msgId++);
		pricesTable.setpTable();
		stage.show();
	}

	//might be helpful in the future, dont delete
	//		Parent tableViewParent = FXMLLoader.load(getClass().getResource("pricesTable.fxml"));
//		Scene tableViewScene = new Scene(tableViewParent);
//		Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
//		window.setScene(tableViewScene);
//		window.show();

	public void showTable(ActionEvent actionEvent) throws IOException {

		System.out.println("first button pressed");
		ObservableList<ParkingLots> parkings = FXCollections.observableArrayList();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("parkingTable.fxml"));
		Stage stage = new Stage();
		stage.setScene(new Scene(loader.load()));
		ParkingTable parkingTable1 = loader.getController();
		parkingTable1.initData(parkings);
		parkingTable1.setMsgId(msgId++);
		parkingTable1.setTable();
		stage.show();
	}


	@Subscribe
	public void getStarterData(NewSubscriberEvent event) {
		try {
			Message message = new Message(msgId, "send Submitters IDs");
			SimpleClient.getClient().sendToServer(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Subscribe
	public void errorEvent(ErrorEvent event){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
		Platform.runLater(() -> {
			Alert alert = new Alert(Alert.AlertType.ERROR,
					String.format("Message:\nId: %d\nData: %s\nTimestamp: %s\n",
							event.getMessage().getId(),
							event.getMessage().getMessage(),
							event.getMessage().getTimeStamp().format(dtf))
			);
			alert.setTitle("Error!");
			alert.setHeaderText("Error:");
			alert.show();
		});
	}


	@FXML
	void initialize() {
		EventBus.getDefault().register(this);

		msgId=0;



// Set the items of the TableView to the ObservableList

		try {
			Message message = new Message(msgId, "add client");
			SimpleClient.getClient().sendToServer(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
