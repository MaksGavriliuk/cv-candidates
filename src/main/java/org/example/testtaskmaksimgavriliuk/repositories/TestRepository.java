package org.example.testtaskmaksimgavriliuk.repositories;

import org.example.testtaskmaksimgavriliuk.entities.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
}