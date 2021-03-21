package com.example.PayMyBuddy.dao;

import com.example.PayMyBuddy.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        List<User> user = (List<User>) session.createQuery("from User").list();
        session.close();
        return user;
    }

    @Override
    public User getUser(int id) {
        Session session = sessionFactory.openSession();
        User user = (User) session.get(User.class, id);
        session.close();
        return user;
    }

    @Override
    public User addUser(User user) {
        Session session = sessionFactory.openSession();
        session.save(user);
        session.close();
        return user;
    }

    @Override
    public User updateUser(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
        return user;
    }

    @Override
    public User deleteUser(int id) {
        Session session = sessionFactory.openSession();
        User user = (User) session.get(User.class, id);
        session.delete(user);
        session.close();
        return user;
    }

    public User findByEmail(String email) {
        Session session = sessionFactory.openSession();
        User user = null;
        Query query = session.createQuery("from User where email = :email");
        query.setParameter("email", email);
        List<User> users = query.list();
        if (users.size() > 0) {
            user = users.get(0);
        } else {
        }
        session.close();
        return user;
    }
}
