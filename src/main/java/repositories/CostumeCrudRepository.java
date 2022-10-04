package repositories;

import entities.Costume;
import org.springframework.data.repository.CrudRepository;

public interface CostumeCrudRepository extends CrudRepository<Costume, Integer> {
}
