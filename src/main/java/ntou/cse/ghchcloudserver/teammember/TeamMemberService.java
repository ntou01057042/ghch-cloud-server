package ntou.cse.ghchcloudserver.teammember;

import org.springframework.stereotype.Service;

@Service
public class TeamMemberService {

    private final TeamMemberRepository teamMemberRepository;

    public TeamMemberService(TeamMemberRepository teamMemberRepository) {
        this.teamMemberRepository = teamMemberRepository;
    }

    public void save(String username, String teamId, String teamName) {
        TeamMember teamMember = new TeamMember(username, teamId, teamName);
        teamMemberRepository.save(teamMember);
    }
}
