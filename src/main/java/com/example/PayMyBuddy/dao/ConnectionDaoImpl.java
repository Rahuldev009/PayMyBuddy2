package com.example.PayMyBuddy.dao;

import com.example.PayMyBuddy.model.Connection;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ConnectionDaoImpl implements ConnectionDao {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Connection> getAllConnections() {
        Session session = sessionFactory.openSession();
        List<Connection> connection = session.createQuery("from Connection").list();
        session.close();
        return connection;
    }

    @Override
    public Connection getConnection(int id) {
        Session session = sessionFactory.openSession();
        Connection connection = session.get(Connection.class, id);
        session.close();
        return connection;
    }

    @Override
    public Connection addConnection(Connection connection) {
        Session session = sessionFactory.openSession();
        session.save(connection);
        session.close();
        return connection;
    }

    @Override
    public Connection updateConnection(Connection connection) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(connection);
        session.getTransaction().commit();
        session.close();
        return connection;
    }

    @Override
    public Connection deleteConnection(int id) {
        Session session = sessionFactory.openSession();
        Connection connection = (Connection) session.get(Connection.class, id);
        session.delete(connection);
        session.close();
        return connection;
    }
}
