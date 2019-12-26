package entity;

public class User {
    public class User {
        private Long userId;
        private String login;
        private String password;
        private String email;
        private int age;

        public User() {
            this.userId++;
            login = "login";
            password = "pass";
            email = "mail@example.com";
            age = 0;
        }

        public User(Long userId, String login, String password, String email, int age) {
            this.userId = userId;
            this.login = login;
            this.password = password;
            this.email = email;
            this.age = age;
        }

        public User(Long userId, String login, String password) {
            this.userId = userId;
            this.login = login;
            this.password = password;
            this.email = "mail@example.com";
            this.age = 0;
        }

        public Long getUserId() {
            return userId;
        }
}
