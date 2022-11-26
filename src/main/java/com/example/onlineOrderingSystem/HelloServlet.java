package com.example.onlineOrderingSystem;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import com.example.onlineOrderingSystem.entity.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        Customer customer = new Customer();

        customer.setEmail("myemail.gmail.com");
        customer.setFirstName("Jiachen");
        customer.setLastName("Ma");
        customer.setPassword("111111");
        customer.setEnabled(true);
        response.getWriter().print(mapper.writeValueAsString(customer));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // read customer information from request
        JSONObject jsonRequest = new JSONObject(IOUtils.toString(request.getReader()));
        String email = jsonRequest.getString("email");
        String firstName = jsonRequest.getString("first_name");
        String lastName = jsonRequest.getString("last_name");
        int age = jsonRequest.getInt("age");

        // print customer information
        System.out.println(jsonRequest);

        response.setContentType("application/json");
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("status", "OK");
        response.getWriter().print(jsonResponse);
    }

    public void destroy() {
    }
}