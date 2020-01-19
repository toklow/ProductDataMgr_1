package enums;

public enum Role {
    USER(1),
    ADMIN(2);

    private int value;

    Role(int value) { this.value = value; }

    public static Role valueToEnum(int value) {
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
