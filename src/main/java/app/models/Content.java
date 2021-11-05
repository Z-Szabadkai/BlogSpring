package app.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Content {

    public enum postStatus {DRAFT, PENDING, PUBLISHED, FUTURE;}

    @Id
    @GeneratedValue
    @Getter
    private long contentId;

    @ManyToOne
    private Blog blogContent;

    @Getter
    private String title;

    @Getter
    private String post;

    @Getter
    private String tags;

    @Enumerated(EnumType.STRING)
    @Getter
    private postStatus status;

    @Getter
    private LocalDateTime published_date;

    @Getter
    private boolean can_comment_under;

    public Content() {}

    public Content(long contentId,
                   Blog blogContent,
                   String title,
                   String post,
                   String tags,
                   postStatus status,
                   LocalDateTime published_date,
                   boolean can_comment_under) {
        this.contentId = contentId;
        this.blogContent = blogContent;
        this.title = title;
        this.post = post;
        this.tags = tags;
        this.status = status;
        this.published_date = published_date;
        this.can_comment_under = can_comment_under;
    }
}

