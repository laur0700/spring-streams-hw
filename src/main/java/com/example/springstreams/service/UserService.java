package com.example.springstreams.service;


import com.example.springstreams.db.UserDb;
import com.example.springstreams.model.User;
import com.thedeanda.lorem.Lorem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;
import java.util.Random;
import java.util.stream.Stream;

@Service
public class UserService {

    public void create(){
        if(!UserDb.users.isEmpty()){
            User lastUser = UserDb.users.get(UserDb.users.size() - 1);
            Integer lastId = lastUser.getId();
            User newUser = new User(lastId + 1, Lorem.getName(), new Random().nextInt(101));

            UserDb.users.add(newUser);

        }
        else {
            User newUser = new User(1, Lorem.getName(), new Random().nextInt(101));
            UserDb.users.add(newUser);
        }
    }

    public ArrayList<User> getAll(){
        return UserDb.users;
    }

    public Stream<User> userStream(){
        ArrayList<User> usersList = new ArrayList<>();
        usersList.add(new User(1, Lorem.getName(), new Random().nextInt(101)));
        usersList.add(new User(2, Lorem.getName(), new Random().nextInt(101)));
        usersList.add(new User(3, Lorem.getName(), new Random().nextInt(101)));

        return usersList.stream();
    }

    public Stream<User> filterStream(Stream<User> stream, int ageFilter){
        return stream.filter(user -> user.getAge() < ageFilter);
    }

    public void doubleAge(Stream<User> stream){
        stream.forEach(user -> {
            System.out.println("Before doubling: " + user);
            user.setAge(user.getAge() * 2);
            System.out.println("After doubling: " + user);
        });
    }

    public User lastElementOfStream(Stream<User> stream){
        return stream.reduce((user, user2) -> user2).orElse(null);
    }

    public User smallestAge(Stream<User> stream){
        return stream.reduce(((user, user2) -> user.getAge() <= user2.getAge() ? user : user2)).orElse(null);
    }

    public User largestAge(Stream<User> stream){
        return stream.reduce(((user, user2) -> user.getAge() <= user2.getAge() ? user2 : user)).orElse(null);
    }

    public Stream<User> removeStreamDuplicates(){
        ArrayList<User> usersList = new ArrayList<>();
        usersList.add(new User(2, "Angela", 19));
        usersList.add(new User(2, "Angela", 19));
        usersList.add(new User(3, Lorem.getName(), new Random().nextInt(101)));

        return usersList.stream().distinct();
    }

    public Stream<User> lastExercise(Stream<User> stream){
        return stream.filter(user -> user.getAge() > 30).map((user) -> {user.setName("BOOMER"/*user.getName().toUpperCase(Locale.ROOT)*/);
            return user;
        }).sorted();
    }

    public void printFilteredStream(){
        System.out.println("--FILTER--");
        this.filterStream(this.userStream(),18).forEach(user -> {
            System.out.println("Filtered user: " + user);
        });
    }

    public void printDoubledStream(){
        System.out.println("--DOUBLING--");
        this.doubleAge(this.userStream());
    }

    public void printLastElementOfStream(){
        System.out.println("--LAST ELEM--");
        System.out.println("Last element: " + this.lastElementOfStream(this.userStream()));
    }

    public void printAges(){
        System.out.println("--SMALLEST AND LARGEST AGES--");
        System.out.println("Smallest age: " + this.smallestAge(this.userStream()));
        System.out.println("Largest age: " + this.largestAge(this.userStream()));
    }

    public void printDistinctStream(){
        System.out.println("--DISTINCT STREAM");
        this.removeStreamDuplicates().forEach(user -> {
            System.out.println(user);
        });
    }

    public void printLastExercise(){
        System.out.println("--LAST EXERCISE--");
        this.lastExercise(this.userStream()).forEach(user -> {
            System.out.println(user);
        });
    }
}
