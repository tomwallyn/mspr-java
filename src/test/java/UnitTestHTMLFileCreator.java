import com.mspr.utils.HTMLFileCreator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class UnitTestHTMLFileCreator {

    HTMLFileCreator htmlFileCreator = new HTMLFileCreator();
    @InjectMocks
    HTMLFileCreator htmlFileCreatorMock = new HTMLFileCreator();

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(htmlFileCreatorMock,
                "index",
                new File("out/html/index2.html"));
    }

    @Test
    public void testCreateIndexPage() throws IOException {
        ArrayList<String> users = new ArrayList<String>(Collections.singleton("jdoe"));
        htmlFileCreatorMock.createIndexPage(users);
        File file = new File("out/html/index2.html");
        Assert.assertTrue(file.exists());
    }

    @Test
    public void testCreateUserPage() throws IOException {
        htmlFileCreator.createUserPage("jdoe", null ,null);
        File file = new File("out/html/jdoe.html");
        Assert.assertTrue(file.exists());
        file.delete();
    }
}
