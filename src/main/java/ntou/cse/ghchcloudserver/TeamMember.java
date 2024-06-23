package ntou.cse.ghchcloudserver;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("team-members")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TeamMember {

    @Id
    private String id;

    private String username;

    private String teamId;

    private String teamName;

    public TeamMember(String username, String teamId, String teamName) {
        this.username = username;
        this.teamId = teamId;
        this.teamName = teamName;
    }
}
