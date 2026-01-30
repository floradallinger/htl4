package resources;

import entities.Message;
import io.smallrye.mutiny.Multi;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestStreamElementType;
import services.EventBusService;
import services.MessageService;

import java.util.Map;
import java.util.UUID;

@Path("/connections")
public class ConnectionResource {
    @Inject
    EventBusService bus;
    @Inject
    MessageService messageService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String register() {
        return UUID.randomUUID().toString();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void sendMessage(Message message) {
        messageService.create(message);
        bus.publish(Map.of("userId", message.getUserId(), "message", message.getText(), "timestamp", message.getTimestamp()));
    }

    // SSE Events...
    @GET @Path("/events") @Produces(MediaType.SERVER_SENT_EVENTS) @RestStreamElementType(MediaType.APPLICATION_JSON)
    public Multi<Object> events() {
        return bus.eventStream();
    }
}