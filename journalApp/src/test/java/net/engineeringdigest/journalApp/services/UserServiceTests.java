package net.engineeringdigest.journalApp.services;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.assertj.core.util.Strings;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;
    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testSaveNewUser(User user){
        assertTrue(userService.saveNewUser(user));
    }


//    @ParameterizedTest
//    @ValueSource(strings = {
//            "yasir",
//            "huzaif",
//            "aasim"
//    })
//    public void testFindByUserName(String name){
//        assertNotNull(userRepository.findByUserName(name));
//    }

    //parameterized test
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "3,9,12"
    })
    @Disabled
    public void test(int a, int b, int expected){
        assertEquals(expected, a+b);
    }
}
