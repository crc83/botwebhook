package com.sbelei.botapi.common;

@RestController
@RequestMapping("/${webhook_base}")
public class WebhookEndpointsController {

    private static final Logger LOG = LoggerFactory.getLogger(WebhookEndpointsController.class);

    private Queue<IncomingEvent> queue = new LinkedList<IncomingEvent>();

    @RequestMapping("/viber")
    public ResponseEntity<String> proxy(@RequestBody Optional<IncomingEvent> incomingMessageOptional) {
        if (incomingMessageOptional.isEmpty()) {
            LOG.info("No incoming content");
            return ResponseEntity.ok("No incoming content");
        }

        IncomingEvent incomingMessage = incomingMessageOptional.get();
        queue.add(incomingMessage);
        return ResponseEntity.ok("saved");
    }
}
