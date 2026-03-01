import java.util.Map;

public class AuthenticationService {

    public User authenticate(String username, String password, Map<String,User> users) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

}