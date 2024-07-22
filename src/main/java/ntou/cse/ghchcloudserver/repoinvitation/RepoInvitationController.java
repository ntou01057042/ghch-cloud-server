package ntou.cse.ghchcloudserver.repoinvitation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/repo-invitations")
public class RepoInvitationController {

    private final RepoInvitationRepository repoInvitationRepository;

    public RepoInvitationController(RepoInvitationRepository repoInvitationRepository) {
        this.repoInvitationRepository = repoInvitationRepository;
    }

    @PostMapping
    public ResponseEntity<Void> createRepoInvitation(@RequestBody RepoInvitation newRepoInvitationRequest, UriComponentsBuilder ucb) {
        RepoInvitation savedRepoInvitation = repoInvitationRepository.save(newRepoInvitationRequest);
        URI locationOfNewRepoInvitation = ucb
                .path("/repo-invitations/{id}")
                .buildAndExpand(savedRepoInvitation.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewRepoInvitation).build();
    }
    @DeleteMapping
    public ResponseEntity<Void> deleteRepoInvitationByInvitationId(@RequestParam String invitationId) {
        if (repoInvitationRepository.existsByInvitationId(invitationId)) {
            repoInvitationRepository.deleteByInvitationId(invitationId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<List<RepoInvitation>> findAllByTeamId(@PathVariable String teamId) {
        // TODO: return sorted list
        List<RepoInvitation> result = repoInvitationRepository.findAllByTeamId(teamId);
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
