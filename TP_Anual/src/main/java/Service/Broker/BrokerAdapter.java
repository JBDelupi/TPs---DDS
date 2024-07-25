package Service.Broker;

import com.rabbitmq.client.DeliverCallback;
import org.json.JSONObject;

public interface BrokerAdapter {
    void publish(String routingKey, JSONObject message);
    void consume(String queueName, DeliverCallback deliverCallback);
}
