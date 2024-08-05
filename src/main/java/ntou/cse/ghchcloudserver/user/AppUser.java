package ntou.cse.ghchcloudserver.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("app-users")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AppUser {

    @Id
    private String id;

    private String username;

    private String firstName;

    private String lastName;

    public AppUser(String id, AppUser appUserUpdate) {
        this.id = id;
        this.username = appUserUpdate.getUsername();
        this.firstName = appUserUpdate.getFirstName();
        this.lastName = appUserUpdate.getLastName();
    }
}
