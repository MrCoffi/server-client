package com.example.studentserver.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private String id;
    private String firstname;
    private String lastname;
    private int age;

    @Override
    public String toString() {
        return id + "|" + firstname + "|" + lastname + "|" + age + "\n";
    }
}
