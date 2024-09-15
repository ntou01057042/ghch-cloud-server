package ntou.cse.ghchcloudserver.prvote;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("pr-votes")
@Getter
@Setter
@ToString
public class PrVote {

    @Id
    private String id;

    private String repoOwner;

    private String repoName;

    private Integer pullNumber;

    private String reviewer;

    private Boolean accept;
}
