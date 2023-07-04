package br.com.wsp.vote.controller;

import br.com.wsp.vote.exception.ResourceNotFoundException;
import br.com.wsp.vote.model.Parameter;
import br.com.wsp.vote.repository.ParameterRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/parameter/v1")
public class ParameterController {

    private final ParameterRepository repository;

    public ParameterController(ParameterRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Parameter> save(@RequestBody @Validated Parameter parameter) {

        Parameter save = repository.save(parameter);

        return ResponseEntity.ok(save);

    }

    @GetMapping()
    public ResponseEntity<Parameter> find(@RequestParam Long key) {

        Parameter parameter = repository.findById(key).orElseThrow(() -> new ResourceNotFoundException("Not found Parameter By ID " + key));

        return ResponseEntity.ok(parameter);
    }
}
