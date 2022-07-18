package ru.work.todo.persistence;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.work.todo.model.User;

import java.util.Optional;

@Repository
public class UserStore implements Store {
    private final SessionFactory sessionFactory;

    public UserStore(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Optional<User> findUserByNamePass(String username, String pass) {
        return tx(session -> session.createQuery("from User where username=:nName and pass=:nPass")
                .setParameter("nName", username)
                .setParameter("nPass", pass)
                .uniqueResultOptional(), sessionFactory);
    }

    public Optional<User> findByUserId(Integer id) {
        return tx(session -> session.createQuery("from User where id=:nId")
                .setParameter("nId", id)
                .uniqueResultOptional(), sessionFactory);
    }

    public Optional<User> add(User user) {
        try {
            return tx(session -> {
                session.save(user);
                return Optional.of(user);
            }, sessionFactory);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
