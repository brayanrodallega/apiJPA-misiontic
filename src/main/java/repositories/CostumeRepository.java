package repositories;

import entities.Costume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CostumeRepository {

    @Autowired
    private CostumeCrudRepository costumesCrudRepository;

    public CostumeRepository() {
    }

    public List<Costume> getAll() {
        return (List<Costume>) costumesCrudRepository.findAll();
    }

    public Optional<Costume> getCostume(int id) {
        return costumesCrudRepository.findById(id);
    }

    public Costume save(Costume costume) {
        return costumesCrudRepository.save(costume);
    }

    public void delete(Costume costume) {
        costumesCrudRepository.delete(costume);
    }
}
