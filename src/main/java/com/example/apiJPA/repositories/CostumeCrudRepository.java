package com.example.apiJPA.repositories;

import com.example.apiJPA.entities.Costume;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CostumeCrudRepository extends CrudRepository<Costume, Integer> {

    @Query("SELECT c.year, COUNT(c.year) from Costume AS c group by c.year order by COUNT(c.year) DESC")
    public List<Object[]> countTotalCostumeByYear();
}
