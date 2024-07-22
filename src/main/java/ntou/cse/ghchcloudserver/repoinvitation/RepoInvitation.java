package ntou.cse.ghchcloudserver.repoinvitation;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("repo-invitations")
@Getter
@Setter
@ToString
public class RepoInvitation {

    @Id
    private String id;

    private String teamId;

    private String teamName;

    private String repoName;

    private String invitationId;
}
