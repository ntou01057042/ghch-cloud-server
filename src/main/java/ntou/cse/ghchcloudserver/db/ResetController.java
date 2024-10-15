package ntou.cse.ghchcloudserver.db;

import ntou.cse.ghchcloudserver.cloudgraphbranch.CloudGraphBranchRepository;
import ntou.cse.ghchcloudserver.cloudgraphcommit.CloudGraphCommitRepository;
import ntou.cse.ghchcloudserver.invitation.InvitationRepository;
import ntou.cse.ghchcloudserver.prvote.PrVoteRepository;
import ntou.cse.ghchcloudserver.repoinvitation.RepoInvitationRepository;
import ntou.cse.ghchcloudserver.team.TeamRepository;
import ntou.cse.ghchcloudserver.teammember.TeamMemberRepository;
import ntou.cse.ghchcloudserver.teamrepo.TeamRepoRepository;
import ntou.cse.ghchcloudserver.user.AppUserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reset-db")
public class ResetController {

    private final CloudGraphBranchRepository cloudGraphBranchRepository;
    private final CloudGraphCommitRepository cloudGraphCommitRepository;
    private final InvitationRepository invitationRepository;
    private final PrVoteRepository prVoteRepository;
    private final RepoInvitationRepository repoInvitationRepository;
    private final TeamRepository teamRepository;
    private final TeamMemberRepository teamMemberRepository;
    private final TeamRepoRepository teamRepoRepository;
    private final AppUserRepository appUserRepository;

    public ResetController(CloudGraphBranchRepository cloudGraphBranchRepository, CloudGraphCommitRepository cloudGraphCommitRepository, InvitationRepository invitationRepository, PrVoteRepository prVoteRepository, RepoInvitationRepository repoInvitationRepository, TeamRepository teamRepository, TeamMemberRepository teamMemberRepository, TeamRepoRepository teamRepoRepository, AppUserRepository appUserRepository) {
        this.cloudGraphBranchRepository = cloudGraphBranchRepository;
        this.cloudGraphCommitRepository = cloudGraphCommitRepository;
        this.invitationRepository = invitationRepository;
        this.prVoteRepository = prVoteRepository;
        this.repoInvitationRepository = repoInvitationRepository;
        this.teamRepository = teamRepository;
        this.teamMemberRepository = teamMemberRepository;
        this.teamRepoRepository = teamRepoRepository;
        this.appUserRepository = appUserRepository;
    }

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllDocuments() {
        cloudGraphBranchRepository.deleteAll();
        cloudGraphCommitRepository.deleteAll();
        invitationRepository.deleteAll();
        prVoteRepository.deleteAll();
        repoInvitationRepository.deleteAll();
        teamRepository.deleteAll();
        teamMemberRepository.deleteAll();
        teamRepoRepository.deleteAll();
        appUserRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{documentName}")
    public ResponseEntity<Void> deleteDocumentByDocumentName(@PathVariable String documentName) {
        if (documentName == "cloud-graph-branch") {
            cloudGraphBranchRepository.deleteAll();
        } else if (documentName == "cloud-graph-commit") {
            cloudGraphCommitRepository.deleteAll();
        } else if (documentName == "invitation") {
            invitationRepository.deleteAll();
        } else if (documentName == "pr-vote") {
            prVoteRepository.deleteAll();
        } else if (documentName == "repo-invitation") {
            repoInvitationRepository.deleteAll();
        } else if (documentName == "team") {
            teamRepository.deleteAll();
        } else if (documentName == "team-member") {
            teamMemberRepository.deleteAll();
        } else if (documentName == "team-repo") {
            teamRepoRepository.deleteAll();
        } else if (documentName == "app-user") {
            appUserRepository.deleteAll();
        }
//        switch (documentName) {
//            case "cloud-graph-branch" -> cloudGraphBranchRepository.deleteAll();
//            case "cloud-graph-commit" -> cloudGraphCommitRepository.deleteAll();
//            case "invitation" -> invitationRepository.deleteAll();
//            case "pr-vote" -> prVoteRepository.deleteAll();
//            case "repo-invitation" -> repoInvitationRepository.deleteAll();
//            case "team" -> teamRepository.deleteAll();
//            case "team-member" -> teamMemberRepository.deleteAll();
//            case "team-repo" -> teamRepoRepository.deleteAll();
//            case "app-user" -> appUserRepository.deleteAll();
//        }
        return ResponseEntity.noContent().build();
    }
}
