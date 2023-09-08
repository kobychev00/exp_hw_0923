import expection.WrongLoginException;
import expection.WrongPasswordException;

import java.util.regex.Pattern;

public class Main {

    public static final String REQUIREMENTS = "Логин/пароль должен содержать только латинские буквы, цифры и знак подчеркивания";

    public static void main(String[] args) {
        /* System.out.println(consistsOnlyLatinCharacters("пппппп"));
        System.out.println(consistsOnlyLatinCharacters("zzzzzz"));
        */
        String login = "логин";
        String password = "password";
        String confirmPassword = "password";

        try {
            checkLoginAndPassword(login, password, confirmPassword);
        } catch (WrongLoginException e) {
            System.out.println(e.getMessage());;
        } catch (WrongPasswordException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Проверка логина и пароля выполнена");
        }
    }

    public static void checkLoginAndPassword(String login,
                                             String password,
                                             String confirmPassword) throws WrongLoginException, WrongPasswordException {
        checkLogin(login);
        checkPassword(password, confirmPassword);

    }

    public static void checkLogin(String login) throws WrongLoginException {


        Pattern p = Pattern.compile("^[A-Za-z0-9_-]{1,20}$");
        if (!p.matcher(login).matches()) {
            throw new WrongLoginException(String.format("Логин %s не подходит под требования %S ", login, REQUIREMENTS));
        }
    }

    public static void checkPassword(String password, String confirmPassword) throws WrongPasswordException {


        Pattern p = Pattern.compile("^[A-Za-z0-9_-]{1,20}$");
        if (!p.matcher(password).matches()) {
            throw new WrongPasswordException(String.format("Пароль не подходит под требования, %s ", REQUIREMENTS));
        }

        if (!password.equals(confirmPassword)) {
            throw new WrongPasswordException("Пароли не совпадают");
        }
    }



/* метод с проверкой на содержание только латинских сиволов и символа
* нижнего подчеркивания через таблицу ASCII
*
* 36:50:00 - вебинар, нашел ошибку. Инвертировал метод. В методе, приведенном в примере на
* получал ошибочный результат
*
* метод не будет выдавать ответ, соответствующий условию при вводе строки с разрешенными
* и недопустимыми символами. Пример: "helloФФФФ"
*
* сделал инверсию
*

    private boolean consistsOnlyLatinCharacters(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!((c >= '\u0041' && c <= '\u005A') || (c >= '\u0061' && c <= '\u007A') || c == '\u005F')) {
                return false;
            }
        }
        return true;
    }
 */
}
