package ntou.cse.ghchcloudserver;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

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
}
