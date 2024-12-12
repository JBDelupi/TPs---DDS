package Service.Notificacion.SMS;

import com.vonage.client.VonageClient;
import com.vonage.client.sms.MessageStatus;
import com.vonage.client.sms.SmsSubmissionResponse;
import com.vonage.client.sms.messages.TextMessage;

public class AdapterVonage {

    String apiKey = System.getenv("SMS_ADAPTER_KEY");
    String apiSecret = System.getenv("SMS_ADAPTER_SECRET");

    VonageClient client = VonageClient.builder().apiKey(apiKey).apiSecret(apiSecret).build();

    public void Notificar(String usuario, String contenido) {
        TextMessage message = new TextMessage("Decco colaboraciones",
                usuario,
                contenido
        );

        SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);
        if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
            System.out.println("Message sent successfully.");
        } else {
            System.out.println("Message failed with error: " + response.getMessages().get(0).getErrorText());
        }
    }
}
