package com.sbelei.botwebhooks.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/viber")
public class ViberWebHookController {

    @GetMapping("/receive")
    public ResponseEntity<String> receive() {
        return ResponseEntity.ok("Hello world");
    }
}
