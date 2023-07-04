package br.com.wsp.vote.model.record;

import br.com.wsp.vote.model.Ruling;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.hateoas.Link;


public record RulingRecord(Long id, String name, Link link){

    public RulingRecord(Ruling ruling, Link link){
        this(ruling.getId(), ruling.getName(), link);
    }
}