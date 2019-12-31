package entity;

import java.util.Objects;

public class User {

    public final static String USER_SEPARATOR = "#";

        private Long id;
        private String login;
        private String password;
        private String email;
        private int age;

        public User() {
            id = 0L;
            login = "login";
            password = "pass";
            email = "mail@example.com";
            age = 0;
        }

    public User(Long id, String login, String password, String email, int age) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.age = age;
    }

    public User(Long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = "mail@example.com";
        this.age = 0;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return id + USER_SEPARATOR + login + USER_SEPARATOR + password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(this.getId(), user.getId()) &&
                Objects.equals(this.getLogin(), user.getLogin()) &&
                Objects.equals(this.getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLogin(), getPassword());
    }

}
