package ntou.cse.ghchcloudserver.teamrepo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepoRepository extends MongoRepository<TeamRepo, String> {
    List<TeamRepo> findAllByTeamId(String teamId);
}
