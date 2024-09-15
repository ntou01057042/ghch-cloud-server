package ntou.cse.ghchcloudserver.prvote;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrVoteRepository extends MongoRepository<PrVote, String> {
    List<PrVote> findAllByRepoOwnerAndRepoNameAndPullNumber(String owner, String repo, int pullNumber);
}
