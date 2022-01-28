package com.sbelei.botapi.telegram;

import com.sbelei.botapi.common.BotHandlerInterface;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBotHandler implements BotHandlerInterface {

    private static final Log LOG = LogFactory.getLog(TelegramBotHandler.class);

    @Autowired
    private TelegramBot telegram;

    @Override
    public void sendMessage(String userId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(message);
        sendMessage.setChatId(userId);
        try {
            telegram.execute(sendMessage);
        } catch (TelegramApiException e) {
            //TODO SB: Implement cirqiut braker
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public String getBotType() {
        return "telegram";
    }

    @Override
    public String getPhoneNumber(Object input) {
        return null;
    }

    @Override
    public String getActionName(Object input) {
        return null;
    }

    @Override
    public void send4SchedulesKeyboard() {

    }

    @Override
    public void sendShareContactKeyboard(String userId) {

    }

    public void setWebhook(String url) {
        try {
            SetWebhook request = new SetWebhook();
            request.setUrl(url);
            LOG.info("Registring callback" + telegram.execute(request));
        } catch (TelegramApiException e) {
            //TODO SB : Implement cirquit braker
            LOG.error("Registring telegram bot failed", e);
        }
    }
}
