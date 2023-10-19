package definitions;

import com.example.babyinsightbackend.BabyInsightBackendApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BabyInsightBackendApplication.class)
public class TestSetupDefs {

    @LocalServerPort
    public String port;


}
