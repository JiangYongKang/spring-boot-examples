package spring.boot.docker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vincent
 * @since 1.0
 */
@RestController
@RequestMapping("/messages")
public class MessageController {

    @GetMapping
    public ResponseEntity<?> index() {
        return ResponseEntity.ok(System.currentTimeMillis());
    }

}
