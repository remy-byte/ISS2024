package ro.iss2024.service;

import ro.iss2024.domain.Bug;
import ro.iss2024.domain.EntitateCompanie;
import ro.iss2024.event.EventBug;
import ro.iss2024.observer.Observable;
import ro.iss2024.observer.Observer;
import ro.iss2024.repository.BugRepository;
import ro.iss2024.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class Service implements Observable<EventBug> {

    UserRepository userRepository;
    BugRepository bugRepository;


    List<Observer<EventBug>> observers;
    public Service(UserRepository userRepository, BugRepository bugRepository) {
        this.userRepository = userRepository;
        this.bugRepository = bugRepository;
        observers = new ArrayList<>();
    }

public EntitateCompanie login(String username, String password) {
        EntitateCompanie user =  userRepository.getUserByUsernameandPassword(username);
        if (user == null || !password.equals(user.getPassword()))
            return null;
        return user;

    }

    public boolean register(EntitateCompanie user) {
        return userRepository.addUser(user);
    }

    public Iterable<EntitateCompanie> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public void addUser(EntitateCompanie user) {
        this.userRepository.addUser(user);
    }

    public void deleteUser(EntitateCompanie user) {
        this.userRepository.deleteUser(user);
    }

    public Iterable<Bug> getAllBugs() {
        return this.bugRepository.getAllBugs();
    }

    public void addBug(Bug bug) {
        this.bugRepository.addBug(bug);
        notifyObservers(new EventBug(null, "get"));
    }

    public void updateBug(Bug bug) {
        this.bugRepository.updateBug(bug);
        notifyObservers(new EventBug(null,  "get"));
    }

    @Override
    public void addObserver(Observer<EventBug> e) {

        observers.add(e);

    }

    @Override
    public void removeObserver(Observer<EventBug> e) {
        observers.remove(e);
    }

    @Override
    public void notifyObservers(EventBug t) {

        observers.forEach(o -> {
            try {
                o.update(t);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }
}
