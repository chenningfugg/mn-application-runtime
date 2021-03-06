package com.example;

import com.annotation.YuJaAutowired;
import com.google.gson.Gson;
import com.model.Customer;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Collections;

@Controller("/user")
public class HomeController {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    Gson gson = new Gson();

    @Get("/hello/{name}")
    public HttpResponse<String> hello(@PathVariable("name") String name) {
        logger.info("=================test=====================");
        Customer customer = new Customer();
        customer.setName(name);
        customer.setAge(23);

        logger.info(gson.toJson(customer));
        logger.info(Customer.class.getName());
        if (Customer.class.isAnnotationPresent(YuJaAutowired.class)) {
            logger.info("================has=======================");
        }
        for (Field field : Customer.class.getDeclaredFields()) {
            logger.info(field.getName());
            if (field.isAnnotationPresent(YuJaAutowired.class)) {
                field.setAccessible(true);
                logger.info("has field:{}", field.getName());
            }
        }

        return HttpResponse.ok().body(gson.toJson(Collections.singletonMap("message", "Hello World")));
    }

    @Post("/save/{institutionId}")
    public HttpResponse<String> hello(@PathVariable("institutionId") String institutionId, @Body Customer customer) {
        logger.info("=================test, intitutionId:{}=====================", institutionId);
        logger.info(gson.toJson(customer));
        logger.info(Customer.class.getName());
        if (Customer.class.isAnnotationPresent(YuJaAutowired.class)) {
            logger.info("================has=======================");
        }
        for (Field field : Customer.class.getDeclaredFields()) {
            logger.info(field.getName());
            if (field.isAnnotationPresent(YuJaAutowired.class)) {
                field.setAccessible(true);
                logger.info("has field:{}", field.getName());
            }
        }

        return HttpResponse.ok().body(gson.toJson(Collections.singletonMap("message", "Hello World")));
    }
}
