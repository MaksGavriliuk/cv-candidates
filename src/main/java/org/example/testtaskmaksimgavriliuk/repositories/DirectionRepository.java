package org.example.testtaskmaksimgavriliuk.repositories;

import org.example.testtaskmaksimgavriliuk.entities.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DirectionRepository extends JpaRepository<Direction, Long> {
}