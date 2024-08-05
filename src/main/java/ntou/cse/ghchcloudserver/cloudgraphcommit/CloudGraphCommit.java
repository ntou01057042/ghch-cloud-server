package ntou.cse.ghchcloudserver.cloudgraphcommit;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("cloud-graph-commits")
@Getter
@Setter
@ToString
public class CloudGraphCommit {

    @Id
    private String id;

    private String owner;

    private String repo;

    private String branchName;

    private String message;

    private String committer;

    private Date commitTime;
}
