package demo.iyushu.service.b;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("demo.iyushu")
public class AppB {

    public static void main(String[] args){
        SpringApplication.run(AppB.class, args);
    }
}
