package ru.job4j.ex;

public class UserStore {

    public static User findUser(User[] users, String login) throws UserNotFoundException {
        User finded = null;
        for (User user: users) {
            if (user.getUserName().equals(login)) {
                finded = user;
                break;
            }
        }
        if (finded == null) {
            throw new UserNotFoundException("Пользователь не найден");
        }
        return finded;
    }

    public static boolean validate(User user) throws UserInvalidException {
        if (!user.isValid() || user.getUserName().length() < 3) {
            throw new UserInvalidException("Пользователь не валидный");
        }
        return true;
    }

    public static void main(String[] args)  {

        User[] users = {
                new User("Petr Arsentev", true)
        };
        try {
            User user = findUser(users, "Petr Arsentev");
            if (validate(user)) {
                System.out.println("This user has an access");
            }
        } catch (UserInvalidException e) {
            System.out.println(e.getMessage());
        } catch (UserNotFoundException d) {
            System.out.println(d.getMessage());
        }
    }
}
