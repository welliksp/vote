package br.com.wsp.vote.controller;

import br.com.wsp.vote.model.Ruling;
import br.com.wsp.vote.model.record.RulingRecord;
import br.com.wsp.vote.service.RulingService;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/vote")
@CrossOrigin
@Tag(name = "Vote", description = "Endpoints for Managing Vote")
public class VoteController {

    private final RulingService rulingService;
    private final VoteService voteService;

    public VoteController(RulingService rulingService, VoteService voteService) {
        this.rulingService = rulingService;
        this.voteService = voteService;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}, produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Add Event Vote",
            description = "Add Event Vote",
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
    public ResponseEntity<RulingRecord> vote(@RequestBody RulingRecord ruling) {

        Ruling byId = rulingService.findById(ruling.id());
        voteService.addEvent(byId);

        Link link = linkTo(methodOn(VoteController.class).vote(ruling)).withSelfRel();

        return ResponseEntity.ok(new RulingRecord(byId, link));
    }
}
