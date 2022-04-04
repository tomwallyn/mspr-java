import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class UnitTestReaderJava {

    File staffFile, listFile, jdoeFile;
    ReaderJava readerJava;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void setUp() {
        try {
            staffFile = folder.newFile("staffFile.txt");
            listFile = folder.newFile("listFile.txt");
            jdoeFile = folder.newFile("jdoe.txt");
        } catch (IOException ioe) {
            System.err.println(
                    "error creating temporary test file in " +
                            this.getClass().getSimpleName());
        }
        try {
            FileWriter fw1 = new FileWriter(staffFile);
            BufferedWriter bw1 = new BufferedWriter(fw1);
            bw1.write("jdoe \n rpani");
            bw1.close();

            FileWriter fw2 = new FileWriter(listFile);
            BufferedWriter bw2 = new BufferedWriter(fw2);
            bw2.write("mousqueton Mousqueton \n gants Gants d’intervention \n brassard Brassard de sécurité \n");
            bw2.close();

            FileWriter fw3 = new FileWriter(jdoeFile);
            BufferedWriter bw3 = new BufferedWriter(fw3);
            bw3.write("John\n Doe \n Surveillance entrepôt \n pmNd1ldFE7WTk \n mousqueton \n brassard ");
            bw3.close();
        } catch (IOException ioe) {
            System.err.println(
                    "error creating temporary test file in " +
                            this.getClass().getSimpleName());
        }


        this.readerJava = new ReaderJava(staffFile, listFile);
    }

    @Test
    public void testGetAListOfUserFromFile() throws IOException {
        //Arrange
        List<String> listOfUsersExpected = Arrays.asList("jdoe","rpani");
        //Act
        ArrayList<String> listOfUsersFromFile = readerJava.getAListOfUsersFromAFile();
        //Assert
        Assert.assertEquals(listOfUsersExpected, listOfUsersFromFile);
    }

    @Test
    public void testCreateItemsList() throws IOException {
        //Arrange
        Map<String, String> item1 = new HashMap<>();
        item1.put("mousqueton","Mousqueton");
        Map<String, String> item2 = new HashMap<>();
        item2.put("gants","Gants d’intervention");
        Map<String, String> item3 = new HashMap<>();
        item3.put("brassard","Brassard de sécurité");
        List<Map> itemsExpected = Arrays.asList(item1,item2,item3);
        //Act
        ArrayList<Map<String, String>> itemsFromFile = readerJava.createItemsList();
        //Assert
        Assert.assertEquals(itemsExpected, itemsFromFile);
    }

    @Test
    public void testGetAllItemsFromUser() throws IOException {
        //Arrange
        ReaderJava readerJavaMock = Mockito.spy(readerJava);
        Mockito.doReturn(jdoeFile).when(readerJavaMock).getUserFile("jdoe");
        List<String> itemsOfUserExpected = Arrays.asList("Mousqueton","Brassard de sécurité");
        List<String> itemsOfUserActual = readerJavaMock.getAllItemsFromUser("jdoe");
        Assert.assertEquals(itemsOfUserExpected,itemsOfUserActual);
    }

    @Test
    public void testGetAllItemsNotUsedByUsers() throws IOException {
        //Arrange
        ReaderJava readerJavaMock = Mockito.spy(readerJava);
        Mockito.doReturn(jdoeFile).when(readerJavaMock).getUserFile("jdoe");
        List<String> itemsOfUserExpected = Collections.singletonList("Gants d’intervention");
        List<String> itemsOfUserActual = readerJavaMock.getAllItemsNotUsedByUsers("jdoe");
        Assert.assertEquals(itemsOfUserExpected,itemsOfUserActual);
    }

}
