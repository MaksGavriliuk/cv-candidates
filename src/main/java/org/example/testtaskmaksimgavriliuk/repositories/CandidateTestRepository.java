package org.example.testtaskmaksimgavriliuk.repositories;

import org.example.testtaskmaksimgavriliuk.entities.CandidateTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CandidateTestRepository extends JpaRepository<CandidateTest, Long> {
}