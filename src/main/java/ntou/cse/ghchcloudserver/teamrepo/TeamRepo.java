package ntou.cse.ghchcloudserver.teamrepo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("team-repos")
@Getter
@Setter
@ToString
public class TeamRepo {

    @Id
    private String id;

    private String teamId;

    private String teamName;

    private String repoName;
}
