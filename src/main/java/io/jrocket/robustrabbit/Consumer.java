package io.jrocket.robustrabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: OCTO-JBU
 * Date: 21/04/2014
 * Time: 00:57
 */
public class Consumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

    private final static String QUEUE_NAME = "RobustRabbit";

    public static void main(String[] argv) throws java.io.IOException, java.lang.InterruptedException {
        // Declare a new RabbitMQ connection factory
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        // Open a new connection to the Broker
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        LOGGER.info(" [*] Waiting for messages. To exit press CTRL+C");

        // Declare a new QueueingConsumer and assign it to the channel
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(QUEUE_NAME, true, consumer);

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            LOGGER.info(" [x] Received '" + message + "'");
        }
    }
}
