package resources;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;
import entities.MyEntity;

public interface MyEntityResource extends PanacheEntityResource<MyEntity, Long> {
}