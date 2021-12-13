package mk.finki.ukim.wpaud.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="users_eshop")
public class User {

    @Id
    private String username;

    private String password;

    private String name;

    private String surname;



    public User(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public User() {}
}