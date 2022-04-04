import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException {
        ReaderJava readerJava = new ReaderJava();
        HTMLFileCreator htmlFileCreator = new HTMLFileCreator();
        ArrayList<String> listOfUsers = readerJava.getAListOfUsersFromAFile();
        htmlFileCreator.createIndexPage(listOfUsers);
        for (String user : listOfUsers) {
            if (Objects.equals(user, "cberthier")) {

                htmlFileCreator.createUserPage(user, readerJava.getAllItemsFromUser(user), readerJava.getAllItemsNotUsedByUsers(user));
            } else {
                htmlFileCreator.createUserPage(user, null, null);
            }
        }
        System.out.println(readerJava.getHtpasswd("cberthier"));
    }


}
