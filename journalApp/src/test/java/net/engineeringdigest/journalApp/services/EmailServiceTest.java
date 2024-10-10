package net.engineeringdigest.journalApp.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private  EmailService emailService;


    @Test
    void testSendMail(){
        emailService.sendEmail("yasiralam981@gmail.com",
                "Testing Java Mail Sender",
                "Hii Ap kasai hoo?"
        );
    }
}
