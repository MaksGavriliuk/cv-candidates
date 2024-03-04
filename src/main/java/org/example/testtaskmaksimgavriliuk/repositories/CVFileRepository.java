package org.example.testtaskmaksimgavriliuk.repositories;

import org.example.testtaskmaksimgavriliuk.entities.CVFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CVFileRepository extends JpaRepository<CVFile, Long> {
    Optional<CVFile> findByName(String name);
}