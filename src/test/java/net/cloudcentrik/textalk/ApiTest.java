package net.cloudcentrik.textalk;

import ch.qos.logback.classic.Logger;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;
import org.junit.*;

import static org.junit.Assert.assertEquals;

public class ApiTest {
    private static final Logger log = AppLogger.getLogger(AppConfigurationTest.class.getName());

    @BeforeClass
    public static void setUpBeforeClass() throws Exception{
        //Method annotated with `@BeforeClass` will execute once before any of the test methods in this class.
        log.debug("before class");
        TextTalkUtils.loadConfigurations();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        //Method annotated with `@AfterClass` will execute once after all of the test methods are executed in this class.
        log.debug("after class");
    }

    @Before
    public void setUp() throws Exception {
        //Method annotated with `@Before` will execute before each test method in this class is executed.
        log.debug("before any method call");
    }

    @After
    public void tearDown() throws Exception {
        //Method annotated with `@After` will execute after each test method in this class is executed.
        log.debug("After any method call");
    }

    @Test
    public void testApiCall() throws Exception {

        String response=TextalkApiClient.getSchema(TexTalkEntity.ARTICLE_GROUP);
        assertEquals("Api call get schema error",response.equals(null),false);
    }
}
