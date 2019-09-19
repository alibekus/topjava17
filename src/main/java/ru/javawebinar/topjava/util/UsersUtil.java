package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.AbstractNamedEntity;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class UsersUtil {

    private UsersUtil() { throw new IllegalStateException("Utility class");}

    public static final List<User> USERS = Arrays.asList(/*
            new User(1, "user1", "user1@email.com", "user1psw", Role.ADMIN),
            new User(2, "user2", "user2@email.com", "user2psw", Role.USER),
            new User(3, "user3", "user3@email.com", "user3psw", Role.ADMIN, Role.USER)*/
    );

    public static List<User> getUsersSortedByName(Collection<User> users) {
        return users.stream().sorted(Comparator.comparing(AbstractNamedEntity::getName)).collect(toList());
    }
}