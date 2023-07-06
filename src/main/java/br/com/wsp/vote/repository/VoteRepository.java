package br.com.wsp.vote.repository;

import br.com.wsp.vote.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
