package Service.Notificacion.Telegram;

import Service.Notificacion.Notificacion;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class test {
    public  static void main(String[] args) throws TelegramApiException {
        AdapterBotTelegram bot = new AdapterBotTelegram();

        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);

        try {
            // Registrar el bot
            botsApi.registerBot(new AdapterBotTelegram());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
