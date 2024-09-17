package ntou.cse.ghchcloudserver.cloudgraphbranch;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cloud-graph-branch")
public class CloudGraphBranchController {

    private final CloudGraphBranchRepository cloudGraphBranchRepository;

    public CloudGraphBranchController(CloudGraphBranchRepository cloudGraphBranchRepository) {
        this.cloudGraphBranchRepository = cloudGraphBranchRepository;
    }

    @PostMapping
    public ResponseEntity<Void> createCloudGraphBranch(@RequestBody CloudGraphBranch newCloudGraphBranchRequest, UriComponentsBuilder ucb) {
        CloudGraphBranch savedCloudGraphBranch = cloudGraphBranchRepository.save(newCloudGraphBranchRequest);
        URI locationOfNewCloudGraphBranch = ucb
                .path("/cloud-graph-branches/{id}")
                .buildAndExpand(savedCloudGraphBranch.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewCloudGraphBranch).build();
    }

    @GetMapping
    public ResponseEntity<List<CloudGraphBranch>> findAllByOwnerAndRepo(@RequestParam String owner, @RequestParam String repo) {
        // TODO: return sorted list
        List<CloudGraphBranch> result = cloudGraphBranchRepository.findAllByOwnerAndRepo(owner, repo);
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/all/{owner}/{repo}")
    public ResponseEntity<Void> deleteAllCloudGraphBranches(@PathVariable String owner, @PathVariable String repo) {
        cloudGraphBranchRepository.deleteByOwnerAndRepo(owner, repo);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{owner}/{repo}")
    public ResponseEntity<Void> deleteCloudGraphBranch(@PathVariable String owner, @PathVariable String repo, @RequestParam String branch) {
        cloudGraphBranchRepository.deleteByOwnerAndRepoAndName(owner, repo, branch);
        return ResponseEntity.noContent().build();
    }
}
