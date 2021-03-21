package com.example.PayMyBuddy.dao;

import com.example.PayMyBuddy.model.Connection;

import java.util.List;

public interface ConnectionDao {

    public List<Connection> getAllConnections();

    public Connection getConnection(int id);

    public Connection addConnection(Connection connection);

    public Connection updateConnection(Connection connection);

    public Connection deleteConnection(int id);

}
