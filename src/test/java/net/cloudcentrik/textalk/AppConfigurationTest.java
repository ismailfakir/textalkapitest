package net.cloudcentrik.textalk;

import static org.junit.Assert.assertEquals;

import ch.qos.logback.classic.Logger;
import org.junit.*;

public class AppConfigurationTest {

    private static final Logger log = AppLogger.getLogger(AppConfigurationTest.class.getName());

    @BeforeClass
    public static void setUpBeforeClass() throws Exception{
        //Method annotated with `@BeforeClass` will execute once before any of the test methods in this class.
        log.debug("before class");
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
    public void testConfigurationFile() throws Exception {

        assertEquals("Configuration file is null",AppConfiguration.CONFIG_FILE.equals(null),false);
    }

    @Test
    public void testConfiguration() throws Exception {

        assertEquals("Token is empty",AppConfiguration.getConfiguration(AppConfiguration.TOKEN).equals(""),false);
    }

}
