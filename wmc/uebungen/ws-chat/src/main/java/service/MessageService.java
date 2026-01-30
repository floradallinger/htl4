package service;

import entities.Message;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class MessageService {

    @Transactional
    public Message create(Message message) {
        Message m = new Message();
        m.setUserId(message.getUserId());
        m.setText(message.getText());
        m.setTimestamp(message.getTimestamp());
        m.persistAndFlush();
        return m;
    }
}
