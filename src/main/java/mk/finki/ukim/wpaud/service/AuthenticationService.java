package mk.finki.ukim.wpaud.service;


import mk.finki.ukim.wpaud.model.User;

public interface AuthenticationService {

    User login (String username, String password);
    User register (String name, String surname, String username, String password,String repeatPassword);


}
