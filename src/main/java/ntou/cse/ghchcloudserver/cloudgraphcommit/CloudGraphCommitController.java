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
    public ResponseEntity<Void> createCloudGraphCommit(@RequestBody CloudGraphCommit newCloudGraphCommitRequest, UriComponentsBuilder ucb) {
        CloudGraphCommit savedCloudGraphCommit = cloudGraphCommitRepository.save(newCloudGraphCommitRequest);
        URI locationOfNewCloudGraphCommit = ucb
                .path("/cloud-graph-commit/{id}")
                .buildAndExpand(savedCloudGraphCommit.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewCloudGraphCommit).build();
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

    @DeleteMapping("/all/{owner}/{repo}")
    public ResponseEntity<Void> deleteAllCloudGraphCommits(@PathVariable String owner, @PathVariable String repo) {
        cloudGraphCommitRepository.deleteByOwnerAndRepo(owner, repo);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{owner}/{repo}")
    public ResponseEntity<Void> deleteCloudGraphCommit(@PathVariable String owner, @PathVariable String repo, @RequestParam String branch) {
        cloudGraphCommitRepository.deleteByOwnerAndRepoAndBranchName(owner, repo, branch);
        return ResponseEntity.noContent().build();
    }
}
