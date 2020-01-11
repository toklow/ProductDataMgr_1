package entity.parser;

import entity.User;

import static enums.Separators.FIELD_SEPARATOR;

public class UserParser {

    public static User stringToUser(String userStr) {
        String [] userInformation = userStr.split(FIELD_SEPARATOR.getValue());

        Long id = Long.parseLong(userInformation[0]);
        String userName = userInformation[1];
        String password = userInformation[2];

        return new User (id, userName, password);
    }
}
