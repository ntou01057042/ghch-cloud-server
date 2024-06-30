package ntou.cse.ghchcloudserver.invitation;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("invitations")
@Getter
@Setter
@ToString
public class Invitation {

    @Id
    private String id;

    private String teamId;

    private String teamName;

    private String username;
}
