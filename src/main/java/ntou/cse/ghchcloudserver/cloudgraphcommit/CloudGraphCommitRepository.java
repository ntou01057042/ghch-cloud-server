package ntou.cse.ghchcloudserver.cloudgraphcommit;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CloudGraphCommitRepository extends MongoRepository<CloudGraphCommit, String> {
    List<CloudGraphCommit> findAllByOwnerAndRepo(String owner, String repo);

    void deleteByOwnerAndRepo(String owner, String repo);
}
