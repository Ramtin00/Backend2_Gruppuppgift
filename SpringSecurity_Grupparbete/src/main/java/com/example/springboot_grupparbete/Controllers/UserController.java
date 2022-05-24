package com.example.springboot_grupparbete.Controllers;


import com.example.springboot_grupparbete.Models.Kund;
import com.example.springboot_grupparbete.Models.User;
import com.example.springboot_grupparbete.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(path ="/sign_up")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User("", ""));
        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);

        return "register_success";
    }
}

    /*
    @Autowired
    private KundRepository kundRepository;
    @Autowired
    private UserRepository userRepository;

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/sign_up")
    public String newUser(@RequestParam String username, @RequestParam String password, @RequestParam long kund) {

        Användare user = new Användare();
        Kund k = kundRepository.findById(kund).get();
        user.setUsername(username);
        user.setPassword(password);
        user.setKund(k);

        if (k == null) {
            user.setKund(k);
        }

        userRepository.save(user);
        log.info("User " + user + " was added to the database");
        return "User " + user + " is saved";
    }

}*/
