package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.UserValidator;
import ru.kpfu.itis.model.forms.LoginForm;
import ru.kpfu.itis.repository.CityRepository;
import ru.kpfu.itis.repository.CountryRepository;
import ru.kpfu.itis.repository.UserRepository;
import ru.kpfu.itis.service.UserService;


@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private UserRepository userRepository;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(new UserValidator(userRepository));
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @PreAuthorize("isAnonymous()")
    public String login(ModelMap map, @RequestParam(required = false, value = "error") String error) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.getClass().equals(AnonymousAuthenticationToken.class)) {
            return "redirect:" + MvcUriComponentsBuilder.fromMappingName("MC#viewAllProduct").build();
        }
        map.put("loginForm", new LoginForm());
        return "login";
    }


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    @PreAuthorize("isAnonymous()")
    public String register(ModelMap map) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.getClass().equals(AnonymousAuthenticationToken.class)) {
            return "redirect:" + MvcUriComponentsBuilder.fromMappingName("MC#viewAllProduct").build();
        }
        map.put("cities", cityRepository.findAll());
        map.put("countries", countryRepository.findAll());
        map.put("user", new User());
        return "signup";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @PreAuthorize("isAnonymous()")
    public String registerHandler(
            RedirectAttributes redirectAttributes,
            @Validated @ModelAttribute("user") User user,
            BindingResult result,
            ModelMap map
    ) {
        if (!result.hasErrors()) {
            userService.registerUser(user);
            redirectAttributes.addAttribute("message", "");
            return "redirect:" + MvcUriComponentsBuilder.fromMappingName("AC#login").build();
        }
        map.put("cities", cityRepository.findAll());
        map.put("countries", countryRepository.findAll());
        return "signup";
    }

    @RequestMapping("/logout")
    @PreAuthorize("isAuthenticated()")
    public String logout() {
        return "redirect:/test/login";
    }
}


