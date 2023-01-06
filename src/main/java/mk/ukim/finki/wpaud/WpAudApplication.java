package mk.ukim.finki.wpaud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@ServletComponentScan //pri start ne samo klasicni stvari tuku da skenira i za servlet
@SpringBootApplication
//anotacia deka e satrtnata klasa koja shto ke ja pokrene aplikacijata i od nejze ke se gradi drvoto na celata aplikacija
@EnableScheduling
public class WpAudApplication {

    public static void main(String[] args) {
        SpringApplication.run(WpAudApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder(10);
    }

}

