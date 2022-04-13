package com.example.springstreams.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User implements Comparable<User> {
    private Integer id;
    private String name;
    private int age;


    @Override
    public int compareTo(User o) {
        return this.getName().compareTo(o.getName());
    }
}
