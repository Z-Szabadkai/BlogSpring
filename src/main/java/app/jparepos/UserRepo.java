package app.jparepos;

import app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface UserRepo extends JpaRepository<User, String> {
    //AppUser findAppUserByUsername(String username);
    List<User> findAllByAccountNonExpiredAndAuthoritiesBetweenAndRegTimeAfter
    (boolean accountNonExpired, Collection<? extends GrantedAuthority> authorities, Collection<? extends GrantedAuthority> authorities2, LocalDateTime regTime);
}
