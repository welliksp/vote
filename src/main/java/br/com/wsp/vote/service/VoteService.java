package br.com.wsp.vote.service;

import br.com.wsp.vote.exception.ResourceBadRequestException;
import br.com.wsp.vote.exception.ResourceNotFoundException;
import br.com.wsp.vote.model.Ruling;
import br.com.wsp.vote.model.Vote;
import br.com.wsp.vote.model.record.VoteRecord;
import br.com.wsp.vote.repository.VoteRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class VoteService {

    private final VoteRepository voteRepository;
    private final RulingService rulingService;

    public VoteService(VoteRepository voteRepository, RulingService rulingService) {
        this.voteRepository = voteRepository;
        this.rulingService = rulingService;
    }

    public Vote findById(Long id) {

        Vote byId = voteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found Vote With ID: " + id));

        return byId;
    }

    public List<Vote> findAll() {

        List<Vote> votes = voteRepository.findAll();

        return votes;
    }

    public Vote save(VoteRecord record) {

        Ruling byId = rulingService.findById(record.rulingId());

        Timestamp date = Timestamp.valueOf(LocalDateTime.now());

        if (date.compareTo(byId.getValidated()) > 0)
            throw new ResourceBadRequestException("Voting Time Is Up");

        Vote vote = Vote.builder()
                .rulingId(byId.getId())
                .rulingName(byId.getName())
                .result(record.result())
                .createAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        Vote save = voteRepository.save(vote);

        return save;
    }
}