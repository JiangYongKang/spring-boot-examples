package spring.boot.restful;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: vincent
 * Date: 2020-11-10 18:19
 * Comment:
 */

@RestController
@SpringBootApplication
public class SpringBootRestful {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRestful.class, args);
    }

    @GetMapping
    public ResponseEntity<?> hello() {
        return ResponseEntity.ok("Hello World");
    }

}
