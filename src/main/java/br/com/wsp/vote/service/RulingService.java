package br.com.wsp.vote.service;

import br.com.wsp.vote.exception.ResourceNotFoundException;
import br.com.wsp.vote.model.Ruling;
import br.com.wsp.vote.model.record.RulingRecord;
import br.com.wsp.vote.repository.RulingRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RulingService {

    private final RulingRepository repository;

    public RulingService(RulingRepository repository) {
        this.repository = repository;
    }

    public Ruling save(RulingRecord record) {

        Ruling ruling = Ruling.builder()
                .name(record.name())
                .createAt(Timestamp.valueOf(LocalDateTime.now()))
                .createdBy("welliksp")
                .validated(record.validated() == 0 ? Timestamp.valueOf(LocalDateTime.now().plusMinutes(1)) : Timestamp.valueOf(LocalDateTime.now().plusMinutes(record.validated())))
                .build();


        return repository.save(ruling);
    }

    public List<Ruling> findAll() {

        List<Ruling> all = repository.findAll();

        return all;
    }

    public Ruling findById(Long id) {

        Ruling ruling = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found Ruling By Id: " + id));

        return ruling;
    }
}
