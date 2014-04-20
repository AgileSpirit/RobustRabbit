package io.jrocket.robustrabbit;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: OCTO-JBU
 * Date: 21/04/2014
 * Time: 00:56
 */
public class Producer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);

    private final static String QUEUE_NAME = "RobustRabbit";

    public static void main(String[] argv) throws java.io.IOException {
        // Declare a new RabbitMQ connection factory
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        // Open a new connection to the Broker
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // Send a message
        String message = "Hello World!";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

        LOGGER.info(" [x] Sent '" + message + "'");

        // Close the connection
        channel.close();
        connection.close();
    }
}
