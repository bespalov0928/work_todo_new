package ru.work.todo.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.work.todo.model.Item;

import javax.xml.crypto.Data;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.function.Function;

@Repository
public class ItemStore {
    private final SessionFactory sf;

    public ItemStore(SessionFactory sf) {
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

    public <T> T create(T item) {
        return this.tx(session -> {
            session.save(item);
            return item;
        });
    }

    public boolean edit(int id, Item item) {

        return this.tx(
                session -> {
                    item.setId(id);
                    session.update(item);
                    return true;
                });
    }

    public Item findItemId(int id) {
        return this.tx(session -> {
            Item rsl = (Item) session.createQuery("from ru.work.todo.model.Item where id=:id"
            ).setParameter("id", id).uniqueResult();
            return rsl;
        });
    }

    public List<Item> findAll() {
        return this.tx(session -> {
            List<Item> list = session.createQuery("select distinct i from Item i left join fetch i.category c").list();
            return list;
        });
    }

    public List<Item> findAllNew() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return this.tx(session -> {
            List<Item> list = (List<Item>) session.createQuery("select distinct i from Item i left join fetch i.category c " +
                    "where i.timeCreat >= CURRENT_DATE").list();
            return list;
        });
    }

    public List<Item> findAllDone() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return this.tx(session -> {
            List<Item> list = (List<Item>) session.createQuery("select distinct i from Item i left join fetch i.category c " +
                    "where i.done = true ").list();
            return list;
        });
    }

    public boolean updateByIdWhenDone(final Integer id) {
        return this.tx(session -> session.createQuery("update Item set done=:nDone where id=:id")
                .setParameter("nDone", true)
                .setParameter("id", id)
                .executeUpdate() > 0);
    }


}
