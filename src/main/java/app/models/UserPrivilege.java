package app.models;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public enum UserPrivilege {

    LURKER (Authority.READ),
    USER (Authority.READ, Authority.CREATE, Authority.WRITE, Authority.MODIFY_OWN, Authority.DELETE_OWN),
    MODERATOR (Authority.READ, Authority.CREATE, Authority.WRITE, Authority.MODIFY, Authority.DELETE_OWN),
    ADMIN (Authority.READ, Authority.CREATE, Authority.WRITE, Authority.MODIFY, Authority.DELETE);

    private final Authority[] AUTHOR;

    UserPrivilege(Authority... author) {
        AUTHOR = author;
    }

    public List<SimpleGrantedAuthority> getAuthor() {
        List<SimpleGrantedAuthority> author = new ArrayList<>();

        for (Authority a : AUTHOR) {
            author.add(new SimpleGrantedAuthority(a.toString()));
        }
        return author;
    }
}
