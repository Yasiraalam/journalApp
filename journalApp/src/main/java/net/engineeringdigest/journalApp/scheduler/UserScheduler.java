package net.engineeringdigest.journalApp.scheduler;

import net.engineeringdigest.journalApp.cache.AppCache;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.enums.Sentiments;
import net.engineeringdigest.journalApp.repository.UserRepositoryImpl;
import net.engineeringdigest.journalApp.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepository;


    @Autowired
    private AppCache appCache;

//    @Scheduled(cron = "0 9 * * SUN")
    public void fetchUsersAndSendSAMail() {
        List<User> users = userRepository.getUserForSA();
        for (User user : users) {
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<Sentiments> sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiments()).collect(Collectors.toList());
            Map<Sentiments, Integer> sentimentCount = new HashMap<>();
            for (Sentiments sentiment : sentiments) {
                if (sentiment != null) {
                    sentimentCount.put(sentiment, sentimentCount.getOrDefault(sentiment, 0) + 1);
                }
            }
            Sentiments mostFrequentSentiment = null;
            int maxCount = 0;
            for (Map.Entry<Sentiments, Integer> entry : sentimentCount.entrySet()) {
                if (entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }
            if (mostFrequentSentiment != null) {
                emailService.sendEmail(user.getEmail(), "Sentiment for last 7 days", mostFrequentSentiment.toString());
            }
        }
    }

//    @Scheduled(cron = "0 0/10 * ? * *")
//    public void cleanAppCache() {
//        appCache.init();
//    }
}
