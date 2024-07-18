package ntou.cse.ghchcloudserver.invitation;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvitationRepository extends MongoRepository<Invitation, String> {
    List<Invitation> findAllByUsername(String username);

    List<Invitation> findAllByTeamId(String teamId);
}
