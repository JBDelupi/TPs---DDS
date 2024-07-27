package Service.Broker;


import io.javalin.Javalin;
import com.rabbitmq.client.*;
import org.json.JSONObject;

public class TestMain {
    public static void main(String[] args) throws Exception {
        // Configurar RabbitMQAdapter
        RabbitMQAdapter adapter = RabbitMQAdapter.getInstance();

        adapter.init();

        // Crear servidor Javalin
      //  Javalin app = Javalin.create().start(7000);


        // Suscripción y procesamiento de mensajes



        /*
        // Crear colas y configurar consumo




        // Ruta para enviar temperaturas
        app.post("/temperatura/{heladeraId}", ctx -> {
            String heladeraId = ctx.pathParam("heladeraId");
            String temperatura = ctx.formParam("temperatura");

            JSONObject temperatureMessage = new JSONObject();
            temperatureMessage.put("type", "temperatura");
            temperatureMessage.put("heladeraId", heladeraId);
            temperatureMessage.put("value", temperatura);

            adapter.publish("heladera." + heladeraId, temperatureMessage);

            ctx.result("Temperatura enviada a la heladera " + heladeraId);
        });

        // Ruta para enviar autorizaciones
        app.post("/autorizar/{heladeraId}", ctx -> {
            String heladeraId = ctx.pathParam("heladeraId");
            String authorizationMessage = "{\"type\": \"autorizacion\", \"value\": \"autorizado\"}";
            adapter.publish("heladera." + heladeraId, new JSONObject(authorizationMessage));
            ctx.result("Autorización enviada a la heladera " + heladeraId);
        });

        // Ruta de ejemplo en Javalin
        app.get("/", ctx -> ctx.result("Servidor Javalin funcionando!"));
        */
        /*
        JSONObject message = new JSONObject();
        message.put("type", "alerta"); // Esta es la clave esperada por el consumidor
        message.put("value", "Temperatura alta en la heladera"); // Esta clave también debe estar presente en el consumidor
        message.put("heladeraId", "1"); // Asegúrate de incluir esta clave si es necesaria para el consumidor

// Publicar el mensaje con una clave de enrutamiento específica
        String routingKey = "heladera.1";
        adapter.publish(routingKey, message);
        */

    }
}
