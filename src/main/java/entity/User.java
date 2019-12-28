package entity;

public class User {

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
}
