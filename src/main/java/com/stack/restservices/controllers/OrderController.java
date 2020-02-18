package com.stack.restservices.controllers;

import java.util.List;
import java.util.Optional;

import com.stack.restservices.entities.Order;
import com.stack.restservices.entities.User;
import com.stack.restservices.exceptions.UserNotFoundException;
import com.stack.restservices.repositories.OrderRepository;
import com.stack.restservices.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class OrderController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/{id}/orders")
    public List<Order> getAllOrders(@PathVariable Long id) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("User Not Found");
        }

        return userOptional.get().getOrders();
    }

    @PostMapping("/{id}/orders")
    public Order createOrder(@PathVariable Long id, @RequestBody Order order) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("User Not Found");
        }

        User user = userOptional.get();
        order.setUser(user);
        return orderRepository.save(order);

    }
}