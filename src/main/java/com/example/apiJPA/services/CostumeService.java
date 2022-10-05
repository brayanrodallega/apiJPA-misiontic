package com.example.apiJPA.services;

import com.example.apiJPA.entities.Costume;
import com.example.apiJPA.entities.YearAmount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.apiJPA.repositories.CostumeRepository;

import java.util.ArrayList;
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

    public List<YearAmount> getTopCostumeByYears(){
        List<Object[]> report = costumeRepository.getTopByYear();
        List<YearAmount> res = new ArrayList<>();
        for (int i=0; i<report.size(); i++){
            res.add(new YearAmount((int)report.get(i)[0], (int)(long)report.get(i)[1]));
        }
        return res;
    }
}
