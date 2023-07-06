package br.com.wsp.vote.controller;

import br.com.wsp.vote.model.Ruling;
import br.com.wsp.vote.model.record.RulingRecord;
import br.com.wsp.vote.service.RulingService;
import br.com.wsp.vote.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/ruling/v1")
@CrossOrigin
@Tag(name = "Ruling", description = "Endpoints for Managing Ruling")
public class RulingController {

    private final RulingService rulingService;

    public RulingController(RulingService rulingService) {
        this.rulingService = rulingService;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Find all Ruling",
            description = "Return List of all Ruling",
            tags = {"Ruling"},
            responses = {
                    @ApiResponse(description = "Sucess", responseCode = "200",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RulingRecord.class))}),
                    @ApiResponse(description = "No Content", responseCode = "201", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<List<RulingRecord>> findAll() {

        List<Ruling> all = rulingService.findAll();

        List<RulingRecord> recordList = new ArrayList<>();

        Link link = linkTo(methodOn(RulingController.class).findAll()).withSelfRel();

        all.forEach(r -> {
            recordList.add(new RulingRecord(r, link));
        });

        return ResponseEntity.ok(recordList);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Find By ID Ruling",
            description = "Find Ruling By ID",
            tags = {"Ruling"},
            responses = {
                    @ApiResponse(description = "Sucess", responseCode = "200",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RulingRecord.class))}),
                    @ApiResponse(description = "No Content", responseCode = "201", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<RulingRecord> findById(@PathVariable Long id) {

        Ruling byId = rulingService.findById(id);

        Link link = linkTo(methodOn(RulingController.class).findById(id)).withSelfRel();

        RulingRecord record = new RulingRecord(byId, link);


        return ResponseEntity.ok(record);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}, produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Create Ruling",
            description = "Create Ruling",
            tags = {"Ruling"},
            responses = {
                    @ApiResponse(description = "Sucess", responseCode = "200",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RulingRecord.class))}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<RulingRecord> save(@RequestBody @Validated RulingRecord rulingRecord) {

        Ruling saved = rulingService.save(rulingRecord);

        Link link = linkTo(methodOn(RulingController.class).findAll()).withSelfRel();

        RulingRecord record = new RulingRecord(saved, link);

        return ResponseEntity.ok(record);
    }

    @PutMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Update Ruling",
            description = "Update Ruling",
            tags = {"Ruling"},
            responses = {
                    @ApiResponse(description = "Sucess", responseCode = "200", content = @Content),
                    @ApiResponse(description = "No Content", responseCode = "201", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<RulingRecord> update(@RequestBody @Valid RulingRecord rulingRecord) {

        var update = rulingService.update(rulingRecord);
        Link link = linkTo(methodOn(RulingController.class).update(rulingRecord)).withSelfRel();
        RulingRecord record = new RulingRecord(update, link);

        return ResponseEntity.ok(record);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete Ruling",
            description = "Delete Ruling",
            tags = {"Ruling"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "201", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<?> delete(@PathVariable Long id) {

        rulingService.delete(id);

        return ResponseEntity.noContent().build();
    }


}