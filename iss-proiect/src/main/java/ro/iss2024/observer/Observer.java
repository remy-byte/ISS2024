package ro.iss2024.observer;


import ro.iss2024.event.Event;

import java.sql.SQLException;

public interface Observer<E extends Event> {
    void update(E e) throws SQLException;
}