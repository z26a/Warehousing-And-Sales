package ir.co.isc.salesorder;

import ir.co.isc.salesorder.controller.CustomerController;
import ir.co.isc.salesorder.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc

//@RunWith(SpringRunner.class)
// @WebAppConfiguration omitted on purpose
//@ContextConfiguration(classes = SalesorderApplication.class)


@SpringBootTest
public class SmokeTest {

    @Autowired
    private CustomerController controller;

    @Test
    public void contextLoads() throws Exception {
        assertNotNull(controller);
    }
}
