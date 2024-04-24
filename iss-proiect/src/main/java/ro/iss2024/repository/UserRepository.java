package ro.iss2024.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ro.iss2024.domain.EntitateCompanie;

public class UserRepository {

    SessionFactory sessionFactory;

    public UserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean addUser(EntitateCompanie user) {
        boolean added = false;
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.persist(user);
                tx.commit();
                added = true;
            } catch (RuntimeException e) {
                e.printStackTrace();
                if (tx != null)
                    tx.rollback();
            }
        }
        return added;
    }

    public EntitateCompanie getUserByUsernameandPassword(String username) {
        EntitateCompanie user = null;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                user = session.get(EntitateCompanie.class, username);
                transaction.commit();
            } catch (RuntimeException e) {
                e.printStackTrace();
                if (transaction != null)
                    transaction.rollback();
            }
        }
        return user;
    }

    public Iterable<EntitateCompanie> getAllUsers() {
        Iterable<EntitateCompanie> users = null;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                users = session.createQuery("from EntitateCompanie as u", EntitateCompanie.class)
                        .list();
                transaction.commit();
            } catch (RuntimeException e) {
                e.printStackTrace();
                if (transaction != null)
                    transaction.rollback();
            }
        }
        return users;
    }

    public void deleteUser(EntitateCompanie user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.delete(user);
                tx.commit();
            } catch (RuntimeException e) {
                e.printStackTrace();
                if (tx != null)
                    tx.rollback();
            }
        }
    }
}
