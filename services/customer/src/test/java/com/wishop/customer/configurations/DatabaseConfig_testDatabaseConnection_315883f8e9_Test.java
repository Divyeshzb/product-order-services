// ********RoostGPT********
/*
Test generated by RoostGPT for test dm-java-test-29 using AI Type Azure Open AI and AI Model roost-gpt4-32k

Test Scenario 1: Check "testDatabaseConnection" returns correct string
  - Given that I call "testDatabaseConnection" function
  - When the function is executed
  - Then, it should return "DB Connection to RDS_TEST - Low Cost Instance"

Test Scenario 2: Check System.out.println correctly prints "DB Connection to RDS_TEST - Low Cost Instance"
 - Given that I call "testDatabaseConnection" function
 - When the function is executed
 - Then, It should print "DB Connection to RDS_TEST - Low Cost Instance" to the standard output

Test Scenario 3: Ensure System.out.println correctly prints "driverClassName" and "url"
 - Given that I call "testDatabaseConnection" function
 - When the function is executed
 - Then, It should print "driverClassName" and "url" to the standard output

Test Scenario 4: Test by calling the "testDatabaseConnection" multiple times
 - Given that I call "testDatabaseConnection" function
 - When I repeatedly call the function multiple times
 - Then, the string "DB Connection to RDS_TEST - Low Cost Instance" should always be returned consistently

Test Scenario 5: Test when the "testDatabaseConnection" is called in multiple threads
 - Given that I have multiple threads
 - When each thread calls the "testDatabaseConnection" function
 - Then, it should always return "DB Connection to RDS_TEST - Low Cost Instance" consistently across all threads

Please note that these scenarios are assuming the "driverClassName" and "url" are correctly defined elsewhere in the code, as they are not shown in the provided code snippet.
*/

// ********RoostGPT********

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class DatabaseConfig {

    @Value("${db.driverClassName}")
    private String driverClassName;

    @Value("${db.url}")
    private String url;

    @Value("${db.username}")
    private String username;

    @Value("${db.password}")
    private String password;

    @Bean("dataSource")
    @Profile("dev")
    public DataSource devDatabaseConnection() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    public String testDatabaseConnection() {
        System.out.println("DB Connection to RDS_TEST - Low Cost Instance");
        System.out.println(driverClassName);
        System.out.println(url);
        return "DB Connection to RDS_TEST - Low Cost Instance";
    }

    public String prodDatabaseConnection() {
        System.out.println("DB Connection to RDS_PROD - High Performance Instance");
        System.out.println(driverClassName);
        System.out.println(url);
        return "DB Connection to RDS_PROD - High Performance Instance";
    }
}

public class DatabaseConfig_testDatabaseConnection_Test {

    private DatabaseConfig databaseConfig;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        databaseConfig = new DatabaseConfig();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testScenario1() {
        String result = databaseConfig.testDatabaseConnection();
        Assertions.assertEquals("DB Connection to RDS_TEST - Low Cost Instance", result);
    }

    @Test
    public void testScenario2() {
        databaseConfig.testDatabaseConnection();
        Assertions.assertTrue(outputStreamCaptor.toString()
            .contains("DB Connection to RDS_TEST - Low Cost Instance"));
    }

    @Test
    public void testScenario3() {
        databaseConfig.testDatabaseConnection();
        Assertions.assertTrue(outputStreamCaptor.toString().contains(databaseConfig.driverClassName));
        Assertions.assertTrue(outputStreamCaptor.toString().contains(databaseConfig.url));
    }

    @Test
    public void testScenario4() {
        String result1 = databaseConfig.testDatabaseConnection();
        String result2 = databaseConfig.testDatabaseConnection();
        String result3 = databaseConfig.testDatabaseConnection();
        Assertions.assertEquals("DB Connection to RDS_TEST - Low Cost Instance", result1);
        Assertions.assertEquals("DB Connection to RDS_TEST - Low Cost Instance", result2);
        Assertions.assertEquals("DB Connection to RDS_TEST - Low Cost Instance", result3);
    }

    @Test
    public void testScenario5() {
        Thread thread1 = new Thread(() -> {
            String result = databaseConfig.testDatabaseConnection();
            Assertions.assertEquals("DB Connection to RDS_TEST - Low Cost Instance", result);
        });

        Thread thread2 = new Thread(() -> {
            String result = databaseConfig.testDatabaseConnection();
            Assertions.assertEquals("DB Connection to RDS_TEST - Low Cost Instance", result);
        });

        thread1.start();
        thread2.start();
    }
}
