package ntou.cse.ghchcloudserver.team;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("teams")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Team {

    @Id
    private String id;

    private String teamName;

    private String owner;

    public Team(String id, Team teamUpdate) {
        this.id = id;
        this.teamName = teamUpdate.teamName;
        this.owner = teamUpdate.owner;
    }
}
