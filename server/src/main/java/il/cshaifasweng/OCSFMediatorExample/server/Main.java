package il.cshaifasweng.OCSFMediatorExample.server;
import java.io.IOException;
import java.sql.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.ParkingLots;
import il.cshaifasweng.OCSFMediatorExample.entities.Prices;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.SubscribedClient;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
public class Main extends SimpleServer {
private static SimpleServer server;
private static Session session;
private Message serverMSG;
private static SessionFactory sessionFactory = getSessionFactory();
private static List<ParkingLots> data = new ArrayList<>();
private static List<Prices> data2 = new ArrayList<>();



    public Main(int port) {
        super(port);
    }



    private static SessionFactory getSessionFactory() throws HibernateException {
        Configuration configuration =new Configuration();
        configuration.addAnnotatedClass(ParkingLots.class);
        configuration.addAnnotatedClass(Prices.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        return configuration.buildSessionFactory(serviceRegistry);
    }



    private static void initializeData() throws Exception {

        ParkingLots p1 = new ParkingLots(2);
        ParkingLots p2 = new ParkingLots(5);
        ParkingLots p3 = new ParkingLots(7);

        session.save(p1);
        session.save(p2);
        session.save(p3);


        Prices pr1 = new Prices(20,30,40,50,60);
        session.save(pr1);

        session.flush();

        session.getTransaction().commit();
    }
    /*private static List<ParkingLots> getAllParkinglots() throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ParkingLots> query = builder.createQuery(ParkingLots.class);
        query.from(ParkingLots.class);
        List<ParkingLots> data = session.createQuery(query).getResultList();
        return data;
    }
    private static List<Prices> getPrices() throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Prices> query = builder.createQuery(Prices.class);
        query.from(Prices.class);
        List<Prices> data = session.createQuery(query).getResultList();
        return data;
    }*/

    @Override
    protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
        Message message = (Message) msg;
        String request = message.getMessage();
        String action = message.getAction();
        try {
            //we got an empty message, so we will send back an error message with the error details.
            if (request.isBlank()){
                message.setMessage("Error! we got an empty message");
                client.sendToClient(message);
            }
            else if(request.equals("print parking table")){


                System.out.println("print parking table message");
// Connect to the database and retrieve the data from the parkinglots table
                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/cps-db", "root", "Moaad98@98@")) {
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM parkinglotss");
                    data.clear();
                    while (rs.next()) {
                        // Add the data to the ObservableList
                        data.add(new ParkingLots(rs.getInt("id"), rs.getInt("num_of_rows"), rs.getInt("num_of_parking_spots")));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
// Set the items of the TableView to the ObservableList
                for(ParkingLots p : data)
                {
                    System.out.println("id is "+p.getId()+"num of rows is "+p.getNum_of_rows()+"num of spots is"+p.getParking_spots());

                }
                System.out.println("we here");
                 message.setList(data);
                message.setMessage("plzz");
                client.sendToClient(message);
            }
            //we got a request to add a new client as a subscriber.
//            else if (request.equals("add client")){
//                SubscribedClient connection = new SubscribedClient(client);
//                SubscribersList.add(connection);
//                message.setMessage("client added successfully");
//                client.sendToClient(message);
//            }
            //we got a message from client requesting to echo Hello, so we will send back to client Hello world!
            else if(request.startsWith("print prices table")){

                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/cps-db", "root", "Moaad98@98@")) {
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM prices");
                    data2.clear();

                    while (rs.next()) {
                        // Add the data to the ObservableList
                      data2.add(new Prices(rs.getInt("id"), rs.getInt("in_advance"),
                              rs.getInt("in_place"),rs.getInt("regular_membership_single"),
                              rs.getInt("regular_membership_multiple"),rs.getInt("Full_membership")));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
// Set the items of the TableView to the ObservableList
                for(Prices p : data2)
                {
                    System.out.println("id is "+p.getId()+"in advance "+p.getIn_Advance_price()+"in place"+
                            p.getIn_place_price()+"mem reg single "+p.getSingle_car_reg_mem_price()+"mem multiple reg"+
                            p.getMultiple_cars_reg_mem_price()+"full mem "+p.getFull_mem_price());

                }
                System.out.println("we here prices");
                message.setPlist(data2);
                message.setMessage("prices list is sent");
                client.sendToClient(message);
            }
            else if(request.startsWith("attempt to change data"))
            {

                int arr[]=message.getChange_prices();
                CriteriaBuilder builder = session.getCriteriaBuilder();
                session.beginTransaction();
                CriteriaQuery<Prices> query = builder.createQuery(Prices.class);
                query.from(Prices.class);
                List<Prices> pricesdata = session.createQuery(query).getResultList();
                Prices price2 = pricesdata.get(0);

// update the entity's fields
                if(arr[0]!=-1 && arr[0]!=-0 )
                price2.setFull_mem_price(arr[0]);
                if(arr[1]!=-1 && arr[1]!=-0 )
                price2.setIn_Advance_price(arr[1]);
                if(arr[2]!=-1 && arr[2]!=-0 )
                    price2.setIn_place_price(arr[2]);
                if(arr[3]!=-1 && arr[3]!=-0 )
                price2.setMultiple_cars_reg_mem_price(arr[3]);
                if(arr[4]!=-1 && arr[4]!=-0 )
                price2.setSingle_car_reg_mem_price(arr[4]);

// save the updated entity
                session.save(price2);
                session.flush();
                session.getTransaction().commit();
                session.clear();


            }
            }
         catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception {

        server = new Main(3030);
        server.listen();
        System.out.println("Server says : hi ");
        try {
        session = sessionFactory.openSession();
        session.beginTransaction();

        initializeData();

        } catch (HibernateException e)
        {
            e.printStackTrace();
        }



    }
}
