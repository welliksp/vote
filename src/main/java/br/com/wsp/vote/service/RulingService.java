package br.com.wsp.vote.service;

import br.com.wsp.vote.exception.ResourceNotFoundException;
import br.com.wsp.vote.model.Ruling;
import br.com.wsp.vote.model.record.RulingRecord;
import br.com.wsp.vote.repository.RulingRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

@Service
public class RulingService {

    private final Logger logger = Logger.getLogger(RulingService.class.getName());

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

        logger.info("Saving ruling!" + ruling);
        return repository.save(ruling);
    }

    public List<Ruling> findAll() {

        logger.info("Finding Ruling!");
        List<Ruling> all = repository.findAll();

        return all;
    }

    public Ruling findById(Long id) {

        logger.info("Finding Ruling!");
        Ruling ruling = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found Ruling By Id: " + id));

        return ruling;
    }

    public Ruling update(RulingRecord rulingRecord) {

        logger.info("Finding Ruling!");
        var ruling = repository.findById(rulingRecord.id()).orElseThrow(() -> new ResourceNotFoundException("Not Found Ruling by ID: " + rulingRecord.id()));

        ruling.setName(rulingRecord.name());
        ruling.setValidated(rulingRecord.validated() == 0 ? Timestamp.valueOf(LocalDateTime.now().plusMinutes(1)) : Timestamp.valueOf(LocalDateTime.now().plusMinutes(rulingRecord.validated())));

        logger.info("Updating ruling!" + ruling);
        return repository.save(ruling);

    }

    public void delete(Long id) {

        logger.info("Finding Ruling!");
        var ruling = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found Ruling by ID" + id));

        repository.deleteById(ruling.getId());

    }
}
