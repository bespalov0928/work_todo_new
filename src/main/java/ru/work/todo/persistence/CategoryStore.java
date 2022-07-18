package ru.work.todo.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.work.todo.model.Category;

import java.util.List;
import java.util.function.Function;

@Repository
public class CategoryStore {
    private final SessionFactory sf;

    public CategoryStore(SessionFactory sf) {
        this.sf = sf;
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }


    public List<Category> findAll() {
        return tx(session -> {
            List<Category> list = session.createQuery("select distinct с from Category с").list();
            return list;
        });
    }

    public Category findById(Integer id) {
        return (Category) this.tx(session -> session.createQuery("from Category where id=:id")
                .setParameter("id", id)
                .uniqueResult());
    }
}
