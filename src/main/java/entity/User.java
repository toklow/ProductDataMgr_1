package entity;

import enums.Role;

import java.util.Objects;

import static enums.Separators.FIELD_SEPARATOR;

public class User {

    private Long id;
    private String login;
    private String password;
    private String email;
    private Integer age;
    private Role role;

    public User() {
        id = 0L;
        login = "login";
        password = "pass";
        email = "mail@example.com";
        age = 0;
    }

    public User(Long id, String login, String password, String email, Integer age, Role role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.age = age;
        this.role = role;
    }

    public User(Long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = "mail@example.com";
        this.age = 0;
        this.role = Role.USER;
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

    public Integer getAge() {
        return age;
    }

    public Integer getRoleId() {
        return role.getValue();
    }

    public Role getRole()  {
        return role;
    }

    @Override
    public String toString() {
        return id + FIELD_SEPARATOR.getValue() + login + FIELD_SEPARATOR.getValue() + password + FIELD_SEPARATOR.getValue() + email + FIELD_SEPARATOR.getValue() + age + FIELD_SEPARATOR.getValue() + role;
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
