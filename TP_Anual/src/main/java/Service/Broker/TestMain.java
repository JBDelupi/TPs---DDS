package Service.Broker;


import Service.Server.Server;
import io.javalin.Javalin;
import com.rabbitmq.client.*;
import org.json.JSONObject;

public class TestMain {
    public static void main(String[] args) throws Exception {
        // Configurar RabbitMQAdapter
        RabbitMQAdapter adapter = RabbitMQAdapter.getInstance();

        adapter.init();

        Server.init();
    }
}
