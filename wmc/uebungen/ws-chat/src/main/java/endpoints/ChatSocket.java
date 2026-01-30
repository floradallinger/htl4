package endpoints;

import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Message;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import service.MessageService;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executor;

@ServerEndpoint("/chat")
@ApplicationScoped
public class ChatSocket {
    private static final Set<Session> sessions = new CopyOnWriteArraySet<>();
    @Inject
    ObjectMapper mapper;
    @Inject
    MessageService messageService;
    @Inject
    Executor executor; // Insert that on top of the class, where all the other injections are.

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }



    @OnMessage
    public void onMessage(String json, Session session) throws Exception {
        executor.execute(() -> {
            try {
                Message msg = mapper.readValue(json, Message.class);
                Message persisted = messageService.create(msg);

                broadcast(mapper.writeValueAsString(persisted));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void broadcast(String message) {
        sessions.forEach(s -> s.getAsyncRemote().sendText(message));
    }
}
