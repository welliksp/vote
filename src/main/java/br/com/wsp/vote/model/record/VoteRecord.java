package br.com.wsp.vote.model.record;

import br.com.wsp.vote.model.Vote;
import org.springframework.hateoas.Link;

public record VoteRecord(Long id, Long rulingId, String rulingName,  String result, Link link) {

    public VoteRecord(Vote vote, Link link) {
        this(vote.getId(), vote.getRulingId(), vote.getRulingName(), vote.getResult(), link);
    }
}