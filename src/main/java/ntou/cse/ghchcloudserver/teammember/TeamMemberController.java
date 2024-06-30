package ntou.cse.ghchcloudserver.teammember;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/team-members")
public class TeamMemberController {

    private final TeamMemberRepository teamMemberRepository;

    public TeamMemberController(TeamMemberRepository teamMemberRepository) {
        this.teamMemberRepository = teamMemberRepository;
    }

    @PostMapping
    private ResponseEntity<Void> createTeamMember(@RequestBody TeamMember newTeamMemberRequest, UriComponentsBuilder ucb) {
        TeamMember savedTeamMember = teamMemberRepository.save(newTeamMemberRequest);
        URI locationOfNewTeamMember = ucb
                .path("/team-members/{id}")
                .buildAndExpand(savedTeamMember.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewTeamMember).build();
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<TeamMember>> findAll(@PathVariable String username) {
        // TODO: return sorted pages
        List<TeamMember> result = teamMemberRepository.findAllByUsername(username);
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTeamMemberByTeamId(@RequestParam String teamId) {
        if (teamMemberRepository.existsByTeamId(teamId)) {
            teamMemberRepository.deleteByTeamId(teamId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
