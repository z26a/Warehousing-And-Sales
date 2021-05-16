package ir.co.isc.salesorder.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.co.isc.salesorder.OrderActive;
import ir.co.isc.salesorder.dto.CartDTO;
import ir.co.isc.salesorder.model.OrderItem;
import ir.co.isc.salesorder.model.SalesOrder;
import ir.co.isc.salesorder.repository.SalesOrderRepository;
import ir.co.isc.salesorder.service.CustomerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;




import java.util.*;


import static org.assertj.core.internal.bytebuddy.implementation.FixedValue.value;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.servlet.http.HttpServletResponse;


@WebMvcTest(CustomerController.class)

class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomerService customerService;

    final String baseURI="/api/orders";




    @BeforeEach
    void setUp() {

//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        SalesOrder salesOrder=new SalesOrder(5L,"Tajrish, Tehran, Iran",
                "bike", OrderActive.ACTIVE);

        salesOrder.addItem(new OrderItem(1L,11));
        salesOrder.addItem(new OrderItem(2L,5));
        salesOrder.addItem(new OrderItem(5L,3));

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllOrdersOfCustomerTest() throws Exception {

        String response="[{\"id\":2,\"customerId\":12345,\"customerAddress\":\"Misaagh Alley,Shariati,Tehran,Iran\"" +
                ",\"orderDate\":\"2021-04-11\",\"orderActive\":\"ACTIVE\",\"orderDesc\":null,\"totalPrice\":0.0," +
                "\"accountingCode\":null,\"paymentCode\":null,\"status\":0,\"activityCheck\":0,\"transport\":\"bike\"," +
                "\"orderItemList\":[{\"id\":35,\"productId\":4,\"productCount\":10,\"productName\":null," +
                "\"productAvailability\":null},{\"id\":34,\"productId\":7,\"productCount\":6,\"productName\":null," +
                "\"productAvailability\":null},{\"id\":37,\"productId\":5,\"productCount\":5,\"productName\":null," +
                "\"productAvailability\":null},{\"id\":36,\"productId\":6,\"productCount\":1,\"productName\":null," +
                "\"productAvailability\":null}]},{\"id\":3,\"customerId\":12345,\"customerAddress\":\"Zafar,Tehran," +
                "Iran\",\"orderDate\":\"2021-04-13\",\"orderActive\":\"ACTIVE\",\"orderDesc\":null,\"totalPrice\":0.0," +
                "\"accountingCode\":null,\"paymentCode\":null,\"status\":0,\"activityCheck\":0,\"transport\":\"bike\"," +
                "\"orderItemList\":[{\"id\":40,\"productId\":30,\"productCount\":1,\"productName\":null," +
                "\"productAvailability\":null},{\"id\":38,\"productId\":2,\"productCount\":5,\"productName\":null," +
                "\"productAvailability\":null},{\"id\":39,\"productId\":1,\"productCount\":10,\"productName\":null," +
                "\"productAvailability\":null}]}]";

        when(customerService.getAllOrdersOfCustomer(12345L)).thenReturn(response);

mockMvc.perform(MockMvcRequestBuilders.get(baseURI+"/{id}",12345L))
        .andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$[0].customerId").exists())
        .andExpect(jsonPath("$[0].customerId").value(12345))
        .andExpect(jsonPath("$[0].orderItemList[1].productId").value(7))
        .andExpect(jsonPath("$[1]").exists())
        .andReturn().getResponse().getContentAsString();

    }

    @Test
    void getOrderByIdTest() throws Exception {
        
        String response="{\n" +
                "    \"statusCode\": \"OK\",\n" +
                "    \"description\": null,\n" +
                "    \"results\": null,\n" +
                "    \"salesOrder\": {\n" +
                "        \"id\": 2,\n" +
                "        \"customerId\": 12345,\n" +
                "        \"customerAddress\": \"Misaagh Alley,Shariati,Tehran,Iran\",\n" +
                "        \"orderDate\": \"2021-04-11\",\n" +
                "        \"orderActive\": \"ACTIVE\",\n" +
                "        \"orderDesc\": null,\n" +
                "        \"totalPrice\": 0.0,\n" +
                "        \"accountingCode\": null,\n" +
                "        \"paymentCode\": null,\n" +
                "        \"status\": 0,\n" +
                "        \"activityCheck\": 0,\n" +
                "        \"transport\": \"bike\",\n" +
                "        \"orderItemList\": [\n" +
                "            {\n" +
                "                \"id\": 34,\n" +
                "                \"productId\": 7,\n" +
                "                \"productCount\": 6,\n" +
                "                \"productName\": null,\n" +
                "                \"productAvailability\": null\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 35,\n" +
                "                \"productId\": 4,\n" +
                "                \"productCount\": 10,\n" +
                "                \"productName\": null,\n" +
                "                \"productAvailability\": null\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 36,\n" +
                "                \"productId\": 6,\n" +
                "                \"productCount\": 1,\n" +
                "                \"productName\": null,\n" +
                "                \"productAvailability\": null\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 37,\n" +
                "                \"productId\": 5,\n" +
                "                \"productCount\": 5,\n" +
                "                \"productName\": null,\n" +
                "                \"productAvailability\": null\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}";

        doReturn(response).when(customerService).getOrderById(12345L);

        mockMvc.perform(MockMvcRequestBuilders.get(baseURI+"/get-order/{orderId}",12345L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.salesOrder.customerId").exists())
                .andExpect(jsonPath("$.salesOrder.customerId").value(12345))
                .andExpect(jsonPath("$.salesOrder.orderItemList[2].productId").value(6));


    }

    @Test
    void deleteOrderByIdTest() throws Exception {
doReturn("Order deleted successfully").when(customerService).deleteOrderById(12345L);

        mockMvc.perform(MockMvcRequestBuilders.put(baseURI+"/delete-order/{orderId}",12345L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Order deleted successfully"));
    }

    @Test
    void saveBuyOrderTest() throws Exception {

        List<Object> orderItems=new ArrayList<>();
        List<OrderItem> orderItemList=new ArrayList<>();

        Map<String, Object> payload = new HashMap<>();
        payload.put("customerId", 1212L);
        payload.put("customerAddress", "Moniriyeh,Tehran,Iran");
        payload.put("transport", "bike");

        Map<String,Object> insidePayload=new HashMap<>();

        insidePayload.put("productId",4);
        insidePayload.put("productCount",10);
        orderItems.add(insidePayload);
        orderItemList.add(new OrderItem(4L,10L));

        insidePayload=new HashMap<>();
        insidePayload.put("productId",7);
        insidePayload.put("productCount",6);
        orderItems.add(insidePayload);
        orderItemList.add(new OrderItem(7L,6L));

        insidePayload=new HashMap<>();
        insidePayload.put("productId",6);
        insidePayload.put("productCount",1);
        orderItems.add(insidePayload);
        orderItemList.add(new OrderItem(6L,1L));

        insidePayload=new HashMap<>();
        insidePayload.put("productId",5);
        insidePayload.put("productCount",5);
        orderItems.add(insidePayload);
        orderItemList.add(new OrderItem(5L,5L));

        payload.put("orderItemList",orderItems);

        ObjectMapper mapper = new ObjectMapper();
        String jsonContent = mapper.writeValueAsString(payload);

        CartDTO cartDTO=new CartDTO ("1212","Moniriyeh,Tehran,Iran",orderItemList,"bike");

doReturn("Your order has been successfully registered").when(customerService).saveBuyOrder(any(CartDTO.class));
        System.out.println(jsonContent);

        mockMvc.perform(MockMvcRequestBuilders.post(baseURI+"/buy")
                .content(jsonContent)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Your order has been successfully registered"));
    }

    @Test
    void updateBuyOrderTest() {
    }
}