package ntou.cse.ghchcloudserver;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("app-users")
@Getter
@Setter
@ToString
public class AppUser {

    @Id
    private String id;

    private String username;
}
