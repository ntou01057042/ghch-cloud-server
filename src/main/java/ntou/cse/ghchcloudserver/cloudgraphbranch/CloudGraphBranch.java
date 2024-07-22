package ntou.cse.ghchcloudserver.cloudgraphbranch;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("cloud-graph-branches")
@Getter
@Setter
@ToString
public class CloudGraphBranch {

    @Id
    private String id;

    private String owner;

    private String repo;

    private String name;

    private Date endTime;

    private Date startTime;

    private String committer;
}
