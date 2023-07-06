package br.com.wsp.vote.controller;

import br.com.wsp.vote.model.Vote;
import br.com.wsp.vote.model.record.RulingRecord;
import br.com.wsp.vote.model.record.VoteRecord;
import br.com.wsp.vote.service.VoteService;
import br.com.wsp.vote.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/vote/v1")
@CrossOrigin
@Tag(name = "Vote", description = "Endpoints for Managing Vote")
public class VoteController {

    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}, produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Create Vote",
            description = "Create Vote",
            tags = {"Vote"},
            responses = {
                    @ApiResponse(description = "Sucess", responseCode = "200",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RulingRecord.class))}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<VoteRecord> save(@RequestBody VoteRecord record) {

        Vote save = voteService.save(record);

        Link link = linkTo(methodOn(VoteController.class).save(record)).withSelfRel();

        VoteRecord voteRecord = new VoteRecord(save, link);

        return ResponseEntity.ok(voteRecord);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Find Vote By ID",
            description = "Find Vote By ID",
            tags = {"Vote"},
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
    public ResponseEntity<VoteRecord> findById(@PathVariable Long id) {

        Vote byId = voteService.findById(id);

        Link link = linkTo(methodOn(VoteController.class).findById(id)).withSelfRel();

        VoteRecord record = new VoteRecord(byId, link);

        return ResponseEntity.ok(record);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Find all Vote",
            description = "Return List of all Vote",
            tags = {"Vote"},
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
    public ResponseEntity<List<VoteRecord>> findAll() {

        List<Vote> all = voteService.findAll();

        List<VoteRecord> recordList = new ArrayList<>();

        Link link = linkTo(methodOn(VoteController.class).findAll()).withSelfRel();

        all.forEach(r -> {
            recordList.add(new VoteRecord(r, link));
        });

        return ResponseEntity.ok(recordList);
    }


}