package Service.Notificacion.Whatsapp;


import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;


public class AdapterBotWhatsapp {

    // Find your Account SID and Auth Token at twilio.com/console
    // and set the environment variables. See http://twil.io/secure
    public static final String ACCOUNT_SID = "";
    public static final String AUTH_TOKEN = "";


    //private String numero = "+12673812069"; Esta hardcodeado, es el segundo numero

    public void Notificar(String numero, String contenido) {

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber("whatsapp:"+numero), //El numero al que enviamos, tiene que tener el codigo de area
                        new com.twilio.type.PhoneNumber("whatsapp:+14155238886"), // Este es el numero de Twilio
                        contenido)
                .create();

        System.out.println(message.getBody());
    }

}





