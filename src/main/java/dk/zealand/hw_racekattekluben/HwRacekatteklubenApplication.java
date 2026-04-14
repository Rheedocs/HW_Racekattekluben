package dk.zealand.hw_racekattekluben;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class HwRacekatteklubenApplication {

    public static void main(String[] args) {SpringApplication.run(HwRacekatteklubenApplication.class, args);}

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();}
}
