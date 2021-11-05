package app.services;

import app.jparepos.UserRepo;
import app.models.Blog;
import app.models.User;
import org.junit.jupiter.api.MethodOrderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private PasswordEncoder encoder;

    @Autowired
    public UserService(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void registerUser(User user) {
        user.setUserPassword(encoder.encode(user.getPassword()));
        em.persist(user);
    }

    public boolean loginUser(User user) {
        List<User> userList = listAllUsers();
        for (User u : userList) {
            if (user.getPassword().equals(u.getPassword()) && (user.getUsername().equals(u.getUsername()))) {
                return true;
            }
        }
        return false;

    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return em.createQuery(
                "SELECT user FROM User user WHERE user.userName = :name",
                User.class)
                .setParameter("name", username)
                .getSingleResult();
    }

    @Transactional
    public List<User> listAllUsers() {

        return em.createQuery("SELECT user FROM User user", User.class)
                .getResultList();
    }

    @Transactional
    public void changePassword(User user) {
        User userFromDB = (User) loadUserByUsername(user.getUsername());
        userFromDB.setUserPassword(encoder.encode(getNewPassword()));
    }

    public String getNewPassword() {
        String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvwxyz";
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            int character = (int) (abc.length() * Math.random());
            builder.append(abc.charAt(character));
        }
        return builder.toString();
    }

}
