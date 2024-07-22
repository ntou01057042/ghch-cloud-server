package ntou.cse.ghchcloudserver.teamrepo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

//@CrossOrigin(origins = "*")
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
        URI locationOfNewTeamRepo = ucb
                .path("/team-repos/{id}")
                .buildAndExpand(savedTeamRepo.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewTeamRepo).build();
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<List<TeamRepo>> findAllByTeamName(@PathVariable String teamId) {
        // TODO: return sorted list
        List<TeamRepo> result = teamRepoRepository.findAllByTeamId(teamId);
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
