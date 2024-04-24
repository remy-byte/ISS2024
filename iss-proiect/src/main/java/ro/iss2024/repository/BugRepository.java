package ro.iss2024.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ro.iss2024.domain.Bug;

public class BugRepository {

    SessionFactory sessionFactory;

    public BugRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean addBug(Bug bug) {
        boolean added = false;
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.persist(bug);
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

    public Bug getBugById(Integer id) {
        Bug bug = null;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                bug = session.get(Bug.class, id);
                transaction.commit();
            } catch (RuntimeException e) {
                e.printStackTrace();
                if (transaction != null)
                    transaction.rollback();
            }
        }
        return bug;
    }

    public Iterable<Bug> getAllBugs() {
        Iterable<Bug> bugs = null;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                bugs = session.createQuery("from Bug as b", Bug.class)
                        .list();
                transaction.commit();
            } catch (RuntimeException e) {
                e.printStackTrace();
                if (transaction != null)
                    transaction.rollback();
            }
        }
        return bugs;
    }

    public void updateBug(Bug bug) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.update(bug);
                tx.commit();
            } catch (RuntimeException e) {
                e.printStackTrace();
                if (tx != null)
                    tx.rollback();
            }
        }
    }
}
