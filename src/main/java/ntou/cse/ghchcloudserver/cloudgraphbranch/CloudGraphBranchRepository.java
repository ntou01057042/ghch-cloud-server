package ntou.cse.ghchcloudserver.cloudgraphbranch;

import ntou.cse.ghchcloudserver.invitation.Invitation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CloudGraphBranchRepository extends MongoRepository<CloudGraphBranch, String> {
    void deleteByOwnerAndRepo(String owner, String repo);

    List<CloudGraphBranch> findAllByOwnerAndRepo(String owner, String repo);
}
