package com.example.PayMyBuddy.service;

import com.example.PayMyBuddy.dao.ConnectionDao;
import com.example.PayMyBuddy.dto.ConnectionDto;
import com.example.PayMyBuddy.model.Connection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConnectionService {

    private static final Logger logger = LogManager.getLogger(ConnectionService.class);

    @Autowired
    ConnectionDao connectionDao;

    @Transactional
    public List<Connection> getAllConnections() {
        return connectionDao.getAllConnections();
    }

    @Transactional
    public Connection getConnection(int id) {
        return connectionDao.getConnection(id);
    }

    @Transactional
    public Connection addConnection(Connection connection) {
        return connectionDao.addConnection(connection);
    }

    @Transactional
    public Connection updateConnection(Connection connection) {
       return connectionDao.updateConnection(connection);
    }

    @Transactional
    public Connection deleteConnection(int id) {
       return connectionDao.deleteConnection(id);
    }

    public List<ConnectionDto> getConnectionList(List<Connection> allConnection, int id) {
        List<ConnectionDto> connectionDtoList = new ArrayList<>();
        for (int i = 0; i < allConnection.size(); i++) {
            if (allConnection.get(i).getUser().getId() == id) {
                Connection connection;
                connection = allConnection.get(i);
                ConnectionDto connectionDto = new ConnectionDto();
                connectionDto.setName(connection.getFriend().getUserName());
                connectionDto.setEmail(connection.getFriend().getEmail());
                connectionDto.setFriendId(connection.getFriend().getId());
                connectionDto.setUserId(connection.getUser().getId());
                connectionDto.setFriendName(connection.getFriend().getUserName());
                connectionDtoList.add(connectionDto);
            }
        }
        logger.info("Connections list "+ connectionDtoList);
        return connectionDtoList;
    }

}
