package net.engineeringdigest.journalApp.services;

import net.engineeringdigest.journalApp.scheduler.UserScheduler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserSchedulersTest {

    @Autowired
    private UserScheduler userScheduler;

    @Test
    public void fetchUsersAndSendSaMail(){
        userScheduler.fetchUsersAndSendSAMail();
    }
}
