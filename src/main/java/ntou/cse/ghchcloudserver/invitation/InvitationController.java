package ntou.cse.ghchcloudserver.invitation;

import ntou.cse.ghchcloudserver.team.Team;
import ntou.cse.ghchcloudserver.teamrepo.TeamRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/invitations")
public class InvitationController {

    private final InvitationRepository invitationRepository;

    public InvitationController(InvitationRepository invitationRepository) {
        this.invitationRepository = invitationRepository;
    }

    @PostMapping
    public ResponseEntity<Void> createInvitation(@RequestBody Invitation newInvitationRequest, UriComponentsBuilder ucb) {
        Invitation savedInvitation = invitationRepository.save(newInvitationRequest);
        URI locationOfNewInvitation = ucb
                .path("/invitations/{id}")
                .buildAndExpand(savedInvitation.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewInvitation).build();
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<Invitation>> findAllByUsername(@PathVariable String username) {
        // TODO: return sorted list
        List<Invitation> result = invitationRepository.findAllByUsername(username);
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvitation(@PathVariable String id) {
        if (invitationRepository.existsById(id)) {
            invitationRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
