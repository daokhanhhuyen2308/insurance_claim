package com.t3h.insurance_claim.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeGuestController {
    @GetMapping(value = {"/home-guest", "/"})
    public String homeGuest() {
        return "guest/home-guest";
    }
}
