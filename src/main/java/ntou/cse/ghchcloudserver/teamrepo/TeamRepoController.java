package ntou.cse.ghchcloudserver.teamrepo;

import ntou.cse.ghchcloudserver.teammember.TeamMember;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/team-repos")
public class TeamRepoController {

    private final TeamRepoRepository teamRepoRepository;

    public TeamRepoController(TeamRepoRepository teamRepoRepository) {
        this.teamRepoRepository = teamRepoRepository;
    }

    @PostMapping
    public ResponseEntity<Void> createTeamRepo(@RequestBody TeamRepo newTeamRepoRequest, UriComponentsBuilder ucb) {
        TeamRepo savedTeamRepo = teamRepoRepository.save(newTeamRepoRequest);
        URI locationOfNewTeam = ucb
                .path("/team-repos/{id}")
                .buildAndExpand(savedTeamRepo.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewTeam).build();
    }

    @GetMapping("/{teamname}")
    public ResponseEntity<List<TeamRepo>> findAllByTeamName(@PathVariable String teamname) {
        // TODO: return sorted list
        List<TeamRepo> result = teamRepoRepository.findAllByTeamName(teamname);
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeamRepo(@PathVariable String id) {
        if (teamRepoRepository.existsById(id)) {
            teamRepoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
