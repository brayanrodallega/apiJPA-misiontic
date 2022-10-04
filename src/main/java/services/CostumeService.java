package services;

import entities.Costume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.CostumeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CostumeService {

    @Autowired
    private CostumeRepository costumeRepository;

    public List<Costume> getAll() {
        return costumeRepository.getAll();
    }

    public Optional<Costume> getCostume(int costumeId) {
        return costumeRepository.getCostume(costumeId);
    }

    public Costume save(Costume costume) {
        if (costume.getId() == null) {
            return costumeRepository.save(costume);
        } else {
            Optional<Costume> e = costumeRepository.getCostume(costume.getId());
            if (e.isEmpty()) {
                return costumeRepository.save(costume);
            } else {
                return costume;
            }
        }
    }

    public Costume update(Costume costume) {
        if (costume.getId() != null) {
            Optional<Costume> e = costumeRepository.getCostume(costume.getId());
            if (!e.isEmpty()) {
                if (costume.getName() != null) {
                    e.get().setName(costume.getName());
                }
                if (costume.getBrand() != null) {
                    e.get().setBrand(costume.getBrand());
                }
                if (costume.getYear() != null) {
                    e.get().setYear(costume.getYear());
                }
                if (costume.getDescription() != null) {
                    e.get().setDescription(costume.getDescription());
                }
                if (costume.getCategory() != null) {
                    e.get().setCategory(costume.getCategory());
                }
                costumeRepository.save(e.get());
                return e.get();
            } else {
                return costume;
            }
        } else {
            return costume;
        }
    }

    public boolean deleteCostume(int costumeId) {
        Boolean aBoolean = getCostume(costumeId).map(costume -> {
            costumeRepository.delete(costume);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
