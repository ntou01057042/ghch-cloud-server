package ntou.cse.ghchcloudserver.team;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends MongoRepository<Team, String> {
    Team findByIdAndOwner(String id, String owner);
}
