package br.com.wsp.vote.model.record;

import br.com.wsp.vote.model.Ruling;
import org.springframework.hateoas.Link;


public record RulingRecord(Long id, String name, Long validated, Link link) {

    public RulingRecord(Ruling ruling, Link link) {
        this(ruling.getId(), ruling.getName(), ruling.getValidated().getTime(), link);
    }
}