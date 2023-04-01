package com.jachil.onlinewhiteboard.DAO;
import com.jachil.onlinewhiteboard.model.Room;
import org.ektorp.CouchDbConnector;
import org.ektorp.support.CouchDbRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class RoomDao extends CouchDbRepositorySupport<Room> {

    public RoomDao(CouchDbConnector db) {
        super(Room.class, db);
    }

}