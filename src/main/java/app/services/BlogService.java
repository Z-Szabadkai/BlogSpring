package app.services;

import app.models.Blog;
import app.models.Content;
import app.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class BlogService {

    private UserService userService;

    @Autowired
    public BlogService(UserService userService) {
        this.userService = userService;
    }

    @PersistenceContext
    private EntityManager em;


    @Transactional
    public List<Blog> findBlogsByUser(String owner) {
        return em.createQuery("SELECT blog FROM Blog blog WHERE blog.owner = :owner",
                Blog.class)
                .setParameter("owner", owner)
                .getResultList();
    }

    @Transactional
    public List<Blog> listAllBlogs() {
        return em.createQuery("SELECT blog FROM Blog blog", Blog.class).getResultList();
    }

    @Transactional
    public boolean createNewContent(User user) {
        if (userService.loginUser(user)) {
            Content content = new Content();
            em.persist(content);
            return true;
        }
        return false;
    }

}
