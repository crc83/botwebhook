package com.sbelei.botapi.viber;

import com.sbelei.botapi.viber.request.incomingmessage.IncomingEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Deprecated
public class ViberBotResource {

    private Queue<IncomingEvent> queue = new LinkedList<IncomingEvent>();

    private static final Logger LOG = LoggerFactory.getLogger(ViberBotResource.class);
    private static Map<String, String> knownUsers = new HashMap<>();
    private static Set<String> usersWeWaitForPhoneNumber = new HashSet<>();

    @Autowired
    private ViberHttpClient viberHttpClient;

    public ResponseEntity<String> proxy(@RequestBody Optional<IncomingEvent> incomingMessageOptional) {
        if (incomingMessageOptional.isEmpty()) {
            LOG.info("No incoming content");
            return ResponseEntity.ok("No incoming content");
        }

        IncomingEvent incomingMessage = incomingMessageOptional.get();
        queue.add(incomingMessage);
        return ResponseEntity.ok("saved");
    }

    public ResponseEntity<IncomingEvent> getEvent() {
        if (queue.isEmpty()) {
            return ResponseEntity.of(Optional.empty());
        } else {
            return ResponseEntity.of(Optional.of(queue.poll()));
        }
    }

    public ResponseEntity<String> sentMessage(@RequestBody String body) throws IOException {
        return ResponseEntity.ok(viberHttpClient.post("https://chatapi.viber.com/pa/send_message", body));
    }

    private boolean isPhoneNumberValid(String possiblePhoneNumber) {
        Pattern pattern = Pattern.compile("^\\d{10}$");
        Matcher matcher = pattern.matcher(possiblePhoneNumber);
        return matcher.matches();
    }

    public ResponseEntity<String> receive() {
        //lets parse request to see if we can get
        return ResponseEntity.ok("ok");
    }
}
