package ntou.cse.ghchcloudserver;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("teams")
@Getter
@Setter
@ToString
public class Team {

    @Id
    private String id;

    private String teamName;

    private String owner;

    private String repoName;
}
