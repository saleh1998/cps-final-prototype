package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.SubscribedClient;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SimpleServer extends AbstractServer {
	private static ArrayList<SubscribedClient> SubscribersList = new ArrayList<>();

	public SimpleServer(int port) {
		super(port);
		
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		Message message = (Message) msg;
		String request = message.getMessage();
		try {
			//we got an empty message, so we will send back an error message with the error details.
			if (request.isBlank()){
				message.setMessage("Error! we got an empty message");
				client.sendToClient(message);
			}
			//we got a request to change submitters IDs with the updated IDs at the end of the string, so we save
			// the IDs at data field in Message entity and send back to all subscribed clients a request to update
			//their IDs text fields. An example of use of observer design pattern.
			//message format: "change submitters IDs: 123456789, 987654321"
			else if(request.startsWith("change submitters IDs:")){
				message.setData(request.substring(23));
				message.setMessage("update submitters IDs");
				sendToAllClients(message);
			}
			//we got a request to add a new client as a subscriber.
			else if (request.equals("add client")){
				SubscribedClient connection = new SubscribedClient(client);
				SubscribersList.add(connection);
				message.setMessage("client added successfully");
				client.sendToClient(message);
			}
			//we got a message from client requesting to echo Hello, so we will send back to client Hello world!
			else if(request.startsWith("echo Hello")){
				message.setMessage("Hello World!");
				client.sendToClient(message);
			}
			else if(request.startsWith("send Submitters IDs")){
				message.setMessage("2090418896, 207392051");
				client.sendToClient(message);

			}
			else if (request.startsWith("send Submitters")){
				message.setMessage("Moaad Abd Ellatif, Saleh Manasra");
				client.sendToClient(message);

			}
			else if (request.equals("what day it is?")) {
				Format f = new SimpleDateFormat("EEEE");
				String str = f.format(new Date());
				message.setMessage(str);
				client.sendToClient(message);
				//add code here to send the date to client

			}
			else if (request.startsWith("add")){
				int len = request.length();
				System.out.println(len);
				String first_num;
				String sec_num ;
				String str =request;
				int c = 0;
				while (len > c){
					if (str.charAt(c) == '+')
						break;
					c++;
				}
				first_num=str.substring(4,c);
				sec_num=str.substring(c+1,len);
				int x =Integer.parseInt(String.valueOf(first_num))+Integer.parseInt(String.valueOf(sec_num));
				message.setMessage(String.valueOf(x));
				client.sendToClient(message);
				System.out.println(x);

				//add code here to sum 2 numbers received in the message and send result back to client
				//(use substring method as shown above)
				//message format: "add n+m"
			}else{
				sendToAllClients(message);
				//add code here to send received message to all clients.
				//The string we received in the message is the message we will send back to all clients subscribed.
				//Example:
					// message received: "Good morning"
					// message sent: "Good morning"
				//see code for changing submitters IDs for help
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void sendToAllClients(Message message) {
		try {
			for (SubscribedClient SubscribedClient : SubscribersList) {
				SubscribedClient.getClient().sendToClient(message);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
