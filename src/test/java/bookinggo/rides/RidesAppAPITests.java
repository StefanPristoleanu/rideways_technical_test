package bookinggo.rides;

import bookinggo.rides.controllers.RestControllerAPI;
import static org.assertj.core.api.Assertions.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RidesAppAPITests {
    
    @Autowired
    private RestControllerAPI controller;
   
    @Test
    public void contexLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
    

}

