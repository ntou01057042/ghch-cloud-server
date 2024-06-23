package ntou.cse.ghchcloudserver.teammember;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamMemberRepository extends MongoRepository<TeamMember, String> {
    List<TeamMember> findAllByUsername(String username);
}
