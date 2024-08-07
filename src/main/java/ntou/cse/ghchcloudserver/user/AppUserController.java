package ntou.cse.ghchcloudserver.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/app-users")
public class AppUserController {

    private final AppUserRepository appUserRepository;

    public AppUserController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUser> findById(@PathVariable String id) {
        Optional<AppUser> appUser = appUserRepository.findById(id);
        return appUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<AppUser> findByUsername(@RequestParam String username) {
        AppUser appUser = appUserRepository.findByUsername(username);
        if (appUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(appUser);
    }

    @PostMapping
    public ResponseEntity<Void> createAppUser(@RequestBody AppUser newAppUserRequest, UriComponentsBuilder ucb) {
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

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAppUser(@PathVariable String id, @RequestBody AppUser appUserUpdate) {
        Optional<AppUser> appUser = appUserRepository.findById(id);
        if (appUser.isPresent()) {
            AppUser updatedAppUser = new AppUser(id, appUserUpdate);
            appUserRepository.save(updatedAppUser);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
