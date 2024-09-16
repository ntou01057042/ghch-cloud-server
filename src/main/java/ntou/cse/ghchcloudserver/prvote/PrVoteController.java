package ntou.cse.ghchcloudserver.prvote;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pr-votes")
public class PrVoteController {

    private final PrVoteRepository prVoteRepository;

    public PrVoteController(PrVoteRepository prVoteRepository) {
        this.prVoteRepository = prVoteRepository;
    }

    @PostMapping
    public ResponseEntity<Void> createPrVote(@RequestBody PrVote newPrVoteRequest, UriComponentsBuilder ucb) {
        PrVote savedPrVote = prVoteRepository.save(newPrVoteRequest);
        URI locationOfNewPrVote = ucb
                .path("/pr-votes/{id}")
                .buildAndExpand(savedPrVote.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewPrVote).build();
    }

    @GetMapping("/{owner}/{repo}/{pullNumber}")
    public ResponseEntity<List<PrVote>> findAllByOwnerAndRepoAndPullNumber(@PathVariable String owner, @PathVariable String repo, @PathVariable int pullNumber) {
        List<PrVote> result = prVoteRepository.findAllByRepoOwnerAndRepoNameAndPullNumber(owner, repo, pullNumber);
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
