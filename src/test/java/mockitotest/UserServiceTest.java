package mockitotest;

import com.example.babyinsightbackend.exception.InformationExistException;
import com.example.babyinsightbackend.models.User;
import com.example.babyinsightbackend.repository.UserRepository;
import com.example.babyinsightbackend.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the UserService class using Mockito for mocking dependencies.
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

@InjectMocks
private UserService userService;

@Mock
private UserRepository userRepository;

    /**
     * Test case for creating a user successfully.
     */
    @Test
    public void testCreateUserSuccessfully() {
        User user = new User(null, "test@example.com", "password123");

        // Mock the behavior of the userRepository
        when(userRepository.existsByEmailAddress(user.getEmailAddress())).thenReturn(false);
        when(userRepository.save(user)).thenReturn(user);

        userService.createUser(user);

        // Verify that userRepository methods were called as expected
        verify(userRepository).existsByEmailAddress(user.getEmailAddress());
        verify(userRepository).save(user);
    }
}
