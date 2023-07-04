package br.com.wsp.vote.service;

import br.com.wsp.vote.model.Ruling;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VoteService {

    private static final String TOPIC_VOTE = "vote";
    private final KafkaTemplate<Object, Object> template;

    public void addEvent(Ruling ruling) {
        template.send(TOPIC_VOTE, ruling);
    }
}
