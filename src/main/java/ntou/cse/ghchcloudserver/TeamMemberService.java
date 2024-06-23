package ntou.cse.ghchcloudserver;

import org.springframework.stereotype.Service;

@Service
public class TeamMemberService {

    private final TeamMemberRepository teamMemberRepository;

    public TeamMemberService(TeamMemberRepository teamMemberRepository) {
        this.teamMemberRepository = teamMemberRepository;
    }

    public void save(String username, String teamName) {
        TeamMember teamMember = new TeamMember(username, teamName);
        teamMemberRepository.save(teamMember);
    }
}
