package resources;


import entities.Message;
import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;

public interface MessageResource
        extends PanacheEntityResource<Message, Long> {
}
