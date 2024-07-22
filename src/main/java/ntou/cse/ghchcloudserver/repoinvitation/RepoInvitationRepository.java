package ntou.cse.ghchcloudserver.repoinvitation;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoInvitationRepository  extends MongoRepository<RepoInvitation, String> {
    List<RepoInvitation> findAllByTeamId(String teamId);
    boolean existsByInvitationId(String invitationId);
    void deleteByInvitationId(String invitationId);
}
