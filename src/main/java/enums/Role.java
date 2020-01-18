package enums;

public enum Role {
    USER(1),
    ADMIN(1);

    private int value;

    Role(int value) { this.value = value; }

    public static Role valueToRole(int value) {
        for (Role role : Role.values()) {
            if (role.value == value) {
                return role;
            }
        }
        return USER;
    }

    public int getValue() {
        return value;
    }

}
