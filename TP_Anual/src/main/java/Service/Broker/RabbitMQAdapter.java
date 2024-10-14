package Service.Broker;

import Service.Broker.Controllers.Publicacion;
import com.rabbitmq.client.*;
import org.json.JSONObject;

import java.io.IOException;

public class RabbitMQAdapter implements BrokerAdapter {

    private static RabbitMQAdapter instance;
    private final Channel channel;
    private final String exchangeName;

    private RabbitMQAdapter() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqps://ucxjlgia:XUEu7C4ny55dCIJPExpKETg7k-F30Yd5@prawn.rmq.cloudamqp.com/ucxjlgia"); // Usa setUri para CloudAMQP
        Connection connection = factory.newConnection();
        this.channel = connection.createChannel();
        this.exchangeName = "heladera_exchange";
        channel.exchangeDeclare(exchangeName, "direct");
    }

    public static synchronized RabbitMQAdapter getInstance() throws Exception {
        if (instance == null) {
            instance = new RabbitMQAdapter();
        }
        return instance;
    }

    @Override
    public void publish(String routingKey, JSONObject message) {
        try {
            channel.basicPublish(exchangeName, routingKey, null, message.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void consume(String queueName, DeliverCallback deliverCallback) {
        try {
            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Channel getChannel() {
        return channel;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public void init() throws IOException {
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            JSONObject jsonMessage = new JSONObject(message);
            String type = jsonMessage.getString("type");

            Publicacion publicacion = (Publicacion) FactoryPublicacion.Publicacion(type);

            publicacion.handleMessage(jsonMessage);

        };
        for (int i = 1; i <= 60; i++) {
            String heladeraQueue = "heladera." + i;
            instance.getChannel().queueDeclare(heladeraQueue, false, false, false, null);
            instance.getChannel().queueBind(heladeraQueue, instance.getExchangeName(), heladeraQueue);
            instance.consume(heladeraQueue, deliverCallback);
        }
    }

}
