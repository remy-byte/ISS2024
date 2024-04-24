package ro.iss2024.service;

import ro.iss2024.domain.Bug;
import ro.iss2024.domain.EntitateCompanie;
import ro.iss2024.repository.BugRepository;
import ro.iss2024.repository.UserRepository;

public class Service {

    UserRepository userRepository;
    BugRepository bugRepository;

    public Service(UserRepository userRepository, BugRepository bugRepository) {
        this.userRepository = userRepository;
        this.bugRepository = bugRepository;
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
    }

    public void updateBug(Bug bug) {
        this.bugRepository.updateBug(bug);
    }
}
