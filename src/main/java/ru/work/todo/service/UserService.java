package ru.work.todo.service;

import net.bytebuddy.dynamic.DynamicType;
import org.springframework.stereotype.Service;
import ru.work.todo.model.User;
import ru.work.todo.persistence.UserStore;

import java.util.Optional;

@Service
public class UserService {

    private final UserStore userStore;

    public UserService(UserStore userStore) {
        this.userStore = userStore;
    }

    public Optional<User> findUserByNamePass(String username, String pass){
        return userStore.findUserByNamePass(username, pass);
    }

    public Optional<User> findByUserId(int id){
        return userStore.findByUserId(id);
    }

    public Optional<User> add(User user){
        Optional<User> userNew = Optional.empty();
        Optional<User> userFind = userStore.findUserByNamePass(user.getUsername(), user.getPass());

        if (userFind.isEmpty()){
            userNew = userStore.add(user);
        }
        return userNew;
    }


}
