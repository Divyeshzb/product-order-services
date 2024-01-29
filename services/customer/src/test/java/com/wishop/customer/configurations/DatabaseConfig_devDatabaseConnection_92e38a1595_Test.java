// ********RoostGPT********
/*
Test generated by RoostGPT for test dm-java-test-29 using AI Type Azure Open AI and AI Model roost-gpt4-32k

Test Scenario 1: Verify the driver class name
In this scenario, we attempt to validate that the correct driver class name is being set. The DriverManagerDataSource object should be able to accept and store the correct driverClassName without returning any exception.

Test Scenario 2: Validate URL Setting
Here, we check if the database URL is correctly set in the DriverManagerDataSource object. The data source object should contain the exact URL passed without any alteration.

Test Scenario 3: Confirm Username Setting
There should be a test to ensure that the username is accurately set in the DriverManagerDataSource object. The username stored should match exactly with the username passed.

Test Scenario 4: Confirm Password Setting
This scenario will validate whether the password is correctly set in the DriverManagerDataSource object. The password set in the data source should be an exact match with the one provided during the test.

Test Scenario 5: Null Values for Parameters
Evaluate whether the DriverManagerDataSource can handle null values for parameters such as driverClassName, url, username, and password. In a real-world application, these fields may sometimes be null. The application should be able to handle such instances gracefully.

Test Scenario 6: Invalid Driver Class Name
We can test the scenario where an invalid driverClassName is passed to the devDatabaseConnection function. It should either throw an appropriate exception or fail to set the driver.

Test Scenario 7: Wrong Database URL
Supply a wrong/non-existing database URL and observe if the function handles it properly. It can either throw an appropriate error or fail to connect to the database.

Test Scenario 8: Wrong Username or Password
Provide invalid credentials and ensure that the function does not allow the connection. The application should return an appropriate authentication error.
*/

// ********RoostGPT********
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan("com.wishop")
public class DatabaseConfig {

    @Value("${db.driverClassName}")
    private String driverClassName;

    @Value("${db.url}")
    private String url;

    @Value("${db.username}")
    private String username;

    @Value("${db.password}")
    private String password;

    @Bean
    @Profile("dev")
    DataSource devDatabaseConnection() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    @Profile("test")
    String testDatabaseConnection() {
        System.out.println("DB Connection to RDS_TEST - Low Cost Instance");
        System.out.println(driverClassName);
        System.out.println(url);
        return "DB Connection to RDS_TEST - Low Cost Instance";
    }

    @Bean
    @Profile("prod")
    String prodDatabaseConnection() {
        System.out.println("DB Connection to RDS_PROD - High Performance Instance");
        System.out.println(driverClassName);
        System.out.println(url);
        return "DB Connection to RDS_PROD - High Performance Instance";
    }   

} 
