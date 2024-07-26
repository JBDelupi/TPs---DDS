package Service.Notificacion.Telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class AdapterBotTelegram extends TelegramLongPollingBot {
    private final String botUsername = "Decco_ok_bot"; // Nombre de usuario del bot
    private final String botToken = "7216777994:AAHix0RbtnJv-c0c0zUx5IWUyoxffqKFoj8";     // Token del bot


    public void Notificar(String usuario, String contenido) {
        // Enviar mensaje a través del bot
        SendMessage message = new SendMessage();
        message.setChatId(usuario); // ID del chat (puede ser número de chat)
        message.setText(contenido); // Contenido del mensaje
        System.out.println(contenido);
        try {
            execute(message); // Enviar mensaje
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();
            System.out.println("Chat ID: " + chatId); // Imprime el chatId en la consola
        }
    }


    @Override
    public String getBotUsername() {
        return botUsername; // Nombre de usuario del bot
    }

    @Override
    public String getBotToken() {
        return botToken; // Token del bot
    }
}
