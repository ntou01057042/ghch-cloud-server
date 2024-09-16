package ntou.cse.ghchcloudserver.cloudgraphcommit;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cloud-graph-commit")
public class CloudGraphCommitController {

    private final CloudGraphCommitRepository cloudGraphCommitRepository;

    public CloudGraphCommitController(CloudGraphCommitRepository cloudGraphCommitRepository) {
        this.cloudGraphCommitRepository = cloudGraphCommitRepository;
    }

    @PostMapping
    public ResponseEntity<Void> createCloudGraphCommit(@RequestBody List<CloudGraphCommit> newCloudGraphCommitRequests, UriComponentsBuilder ucb) {
        cloudGraphCommitRepository.saveAll(newCloudGraphCommitRequests);
//        CloudGraphCommit savedCloudGraphCommit = cloudGraphCommitRepository.save(newCloudGraphCommitRequest);
//        URI locationOfNewCloudGraphCommit = ucb
//                .path("/cloud-graph-commit/{id}")
//                .buildAndExpand(savedCloudGraphCommit.getId())
//                .toUri();
//        return ResponseEntity.created(locationOfNewCloudGraphCommit).build();
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<CloudGraphCommit>> findAllByOwnerAndRepo(@RequestParam String owner, @RequestParam String repo) {
        // TODO: return sorted list
        List<CloudGraphCommit> result = cloudGraphCommitRepository.findAllByOwnerAndRepo(owner, repo);
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{owner}/{repo}")
    public ResponseEntity<Void> deleteCloudGraphCommit(@PathVariable String owner, @PathVariable String repo) {
        cloudGraphCommitRepository.deleteByOwnerAndRepo(owner, repo);
        return ResponseEntity.noContent().build();
    }
}
