package prueba.bancoBogota.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import prueba.bancoBogota.persistence.EmployeePersistence;

@SpringBootApplication
@ComponentScan(basePackages = {"prueba.bancoBogota"})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

    }

}
