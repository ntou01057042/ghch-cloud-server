package ntou.cse.ghchcloudserver.review;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {
    Optional<Review> findByRepoNameAndPullNumber(String repoName, int pullNumber);
//    List<Review> findAllByRepoNameAndPullNumber(String repoName, int pullNumber);
}
