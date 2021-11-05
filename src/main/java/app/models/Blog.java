package app.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
public class Blog {

    private enum templateStyle {BOOK, FOOD,	MARKETING, MUSIC, NEWS, PERSONAL, TRAVEL}
    private enum templateColor {BLACK, BROWN, GREEN, PURPLE, RED, YELLOW}

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @Setter
    @Getter
    private User owner;

    @Setter
    @Getter
    private String title;

    @Setter
    @Getter
    @Transient
    private byte[] templateBackground;

    @Enumerated(EnumType.STRING)
    @Setter
    @Getter
    @Transient
    private templateStyle templateStyle;

    @Enumerated(EnumType.STRING)
    @Setter
    @Getter
    @Transient
    private templateColor templateColor;

    @OneToMany(mappedBy = "blogContent")
    @Setter
    @Getter
    private List<Content> contentList;

    public Blog() {}

    public Blog(long id,
                User owner,
                String title,
                byte[] templateBackground,
                Blog.templateStyle templateStyle,
                Blog.templateColor templateColor,
                List<Content> contentList) {
        this.id = id;
        this.owner = owner;
        this.title = title;
        this.templateBackground = templateBackground;
        this.templateStyle = templateStyle;
        this.templateColor = templateColor;
        this.contentList = contentList;
    }
}
