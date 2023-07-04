package br.com.wsp.vote.repository;

import br.com.wsp.vote.model.Ruling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RulingRepository extends JpaRepository<Ruling, Long> {
}
