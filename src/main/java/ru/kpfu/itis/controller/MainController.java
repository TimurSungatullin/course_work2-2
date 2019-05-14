package ru.kpfu.itis.controller;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.model.Order;
import ru.kpfu.itis.model.Product;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.repository.*;
import ru.kpfu.itis.utils.WeatherAPI;

import java.io.*;


@Controller
public class MainController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/")
    public String redirect() {
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName("MC#viewAllProduct").build();
    }

    @RequestMapping(value = "/products")
    public String viewAllProduct(ModelMap map) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.getClass().equals(AnonymousAuthenticationToken.class)) {
            map.put("user", userRepository.findByEmail(auth.getName()));
        }
        String temp = WeatherAPI.getTemp();
        if (temp != null) {
            map.put("temp", temp);
        }
        map.put("categories", categoryRepository.findAll());
        map.put("products", productRepository.findAll());
        return "home";
    }

    @RequestMapping(value = "/category/{category}")
    public String viewProductByCategory(ModelMap map, @PathVariable(name = "category") String category) throws IOException, ParseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.getClass().equals(AnonymousAuthenticationToken.class)) {
            map.put("user", userRepository.findByEmail(auth.getName()));
        }
        String temp = WeatherAPI.getTemp();
        if (temp != null) {
            map.put("temp", temp);
        }
        map.put("categories", categoryRepository.findAll());
        map.put("products", productRepository.findProductsByCategory(categoryRepository.findByTitle(category)));
        return "home";
    }

    @RequestMapping(value = "/products/{id}")
    public String productDetail(@PathVariable Long id, ModelMap map) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.getClass().equals(AnonymousAuthenticationToken.class)) {
            map.put("user", userRepository.findByEmail(auth.getName()));
        }
        Product product = productRepository.findById(id).get();
        map.put("categories", categoryRepository.findAll());
        map.put("product", product);
        return "product";
    }

    @RequestMapping(value = "/products/{id}/order", method = RequestMethod.GET)
    public String orderProductDetail(@PathVariable Long id, ModelMap map, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.getClass().equals(AnonymousAuthenticationToken.class)) {
            map.put("user", userRepository.findByEmail(auth.getName()));
            User user = userRepository.findByEmail(auth.getName());
            Product product = productRepository.findById(id).get();
            Order newOrder = new Order();
            newOrder.setProduct(product);
            newOrder.setUser(user);
            orderRepository.save(newOrder);
            redirectAttributes.addAttribute("message", "");
            return "redirect:" + MvcUriComponentsBuilder.fromMappingName("MC#viewAllProduct").build();
        }
        redirectAttributes.addAttribute("warning", "");
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName("AC#login").build();
    }
}
