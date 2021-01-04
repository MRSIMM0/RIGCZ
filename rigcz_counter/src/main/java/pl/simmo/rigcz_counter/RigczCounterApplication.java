package pl.simmo.rigcz_counter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication()
public class RigczCounterApplication {

    public static void main(String[] args) {
        SpringApplication.run(RigczCounterApplication.class, args);
    }

}
