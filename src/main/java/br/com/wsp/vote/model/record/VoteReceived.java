package br.com.wsp.vote.model.record;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VoteReceived {
    private Long id;
    private String name;
    private String result;
}