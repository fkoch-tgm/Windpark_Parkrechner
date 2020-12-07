package windpark.parkrechner.mom;


import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import windpark.parkrechner.ParkrechnerApplication;

import javax.jms.*;


public class MOMReceiver {

    private static String user = ActiveMQConnection.DEFAULT_USER;
    private static String password = ActiveMQConnection.DEFAULT_PASSWORD;
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    private static String subject = "WINDENGINE[001]";

    public MOMReceiver() {

        System.out.println("Receiver started.");

        // Create the connection.
        Session session = null;
        Connection connection = null;
        MessageConsumer consumer = null;
        Destination destination = null;

        try {

            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, password, url);
            connection = connectionFactory.createConnection();
            connection.start();

            // Create the session
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue(subject);

            // Create the consumer
            consumer = session.createConsumer(destination);

            // Start receiving
            //ObjectMessage message = (ObjectMessage) consumer.receive();
            //WindengineData data = (WindengineData) message.getObject();
            TextMessage message = (TextMessage) consumer.receive();
            while (message != null) {
                System.out.println("Message received: " + message.getText());
                message.acknowledge();
                String json = message.getText();
                ParkrechnerApplication.data.data.add(json);
                message = (TextMessage) consumer.receive();
                //message = (ObjectMessage) consumer.receive();
                //data = (WindengineData) message;
            }
            connection.stop();

        } catch (Exception e) {

            System.out.println("[MessageConsumer] Caught: " + e);
            e.printStackTrace();

        } finally {

            try {
                consumer.close();
            } catch (Exception e) {
            }
            try {
                session.close();
            } catch (Exception e) {
            }
            try {
                connection.close();
            } catch (Exception e) {
            }

        }
        System.out.println("Receiver finished.");

    } // end main

}
