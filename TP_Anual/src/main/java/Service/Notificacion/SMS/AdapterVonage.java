package Service.Notificacion.SMS;

import com.vonage.client.VonageClient;
import com.vonage.client.sms.MessageStatus;
import com.vonage.client.sms.SmsSubmissionResponse;
import com.vonage.client.sms.messages.TextMessage;

public class AdapterVonage {

    VonageClient client = VonageClient.builder().apiKey("cbadfce7").apiSecret("19meFVarHVb4Gohe").build();

    public void Notificar(String usuario, String contenido) {
        TextMessage message = new TextMessage("API SMS",
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
