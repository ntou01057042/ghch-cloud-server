package ntou.cse.ghchcloudserver;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamRepository teamRepository;

    public TeamController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @PostMapping
    private ResponseEntity<Void> createTeam(@RequestBody Team newTeamRequest, UriComponentsBuilder ucb) {
        Team savedTeam = teamRepository.save(newTeamRequest);
        URI locationOfNewTeam = ucb
                .path("/teams/{id}")
                .buildAndExpand(savedTeam.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewTeam).build();
    }

    @GetMapping("/{id}")
    private ResponseEntity<Team> findById(@PathVariable String id) {
        Optional<Team> team = teamRepository.findById(id);
        return team.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteTeam(@PathVariable String id) {
        if (teamRepository.existsById(id)) {
            teamRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    private ResponseEntity<Void> putTeam(@PathVariable String id, @RequestBody Team teamUpdate) {
        Team team = teamRepository.findByIdAndOwner(id, teamUpdate.getOwner());
        if (team != null) {
            Team updatedTeam = new Team(id, teamUpdate);
            teamRepository.save(updatedTeam);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
