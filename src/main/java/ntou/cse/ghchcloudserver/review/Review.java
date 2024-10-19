package ntou.cse.ghchcloudserver.review;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Review {
    @Id
    private String id;
    private String repoName;
    private int pullNumber;
    private String content;
    private String mergeApproval;
    private LocalDateTime createdAt; // 新增的時間戳欄位
}
