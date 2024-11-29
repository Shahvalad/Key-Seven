package com.keyseven.order.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "mail-service", url = "http://localhost:9030")
public interface MailClient {
    @PostMapping("/api/v1/mails/send")
    String sendEmail(@RequestParam String to,
                            @RequestParam String subject,
                            @RequestParam String text);
}
