package com.sbelei.botwebhooks.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/viber")
public class ViberWebHookController {

    //Viber requires that we handle any request here and always return 200
    @RequestMapping("/receive")
    public ResponseEntity<String> receive() {
        return ResponseEntity.ok("Hello world");
    }
}
