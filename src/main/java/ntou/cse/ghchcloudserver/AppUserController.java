package ntou.cse.ghchcloudserver;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/app-users")
public class AppUserController {

    private final AppUserRepository appUserRepository;

    public AppUserController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @GetMapping("/{id}")
    private ResponseEntity<AppUser> findById(@PathVariable String id) {
        Optional<AppUser> appUser = appUserRepository.findById(id);
        if (appUser.isPresent()) {
            return ResponseEntity.ok(appUser.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    private ResponseEntity<Void> createAppUser(@RequestBody AppUser newAppUserRequest, UriComponentsBuilder ucb) {
        AppUser appUser = appUserRepository.findByUsername(newAppUserRequest.getUsername());
        if (appUser != null) {
            URI locationOfNewAppUser = ucb
                    .path("/app-users/{id}")
                    .buildAndExpand(appUser.getId())
                    .toUri();
            return ResponseEntity.created(locationOfNewAppUser).build();
        }
        AppUser savedAppUser = appUserRepository.save(newAppUserRequest);
        URI locationOfNewAppUser = ucb
                .path("/app-users/{id}")
                .buildAndExpand(savedAppUser.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewAppUser).build();
    }
}
