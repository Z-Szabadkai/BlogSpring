package app.models;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table
public class User implements UserDetails {

    @Id
    @GeneratedValue
    @Getter
    @Setter
    private long userId;

    @Setter
    private String userName;

    @Transient
    @Setter
    private String userPassword;

    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private UserPrivilege privilege;

    @Getter
    @Setter
    private String userEmail;

    @Getter
    @Setter
    @Transient
    private byte[] userAvatar;

    @Getter
    @Setter
    private LocalDateTime registration_date;

    @OneToMany(mappedBy = "owner")
    @Getter
    private List<Blog> blogList;

    @Transient
    private boolean isEnabled;

    public User() {
        this.isEnabled = true;
    }

    public User(long userId,
                String userName,
                String userPassword,
                UserPrivilege privilege,
                String userEmail,
                byte[] userAvatar,
                LocalDateTime registration_date,
                List<Blog> blogList) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.privilege = privilege;
        this.userEmail = userEmail;
        this.userAvatar = userAvatar;
        this.registration_date = registration_date;
        this.blogList = blogList;
        this.isEnabled = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return privilege.getAuthor();
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isEnabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isEnabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isEnabled;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
