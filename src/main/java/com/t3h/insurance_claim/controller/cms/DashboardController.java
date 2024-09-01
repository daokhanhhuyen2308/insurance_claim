package com.t3h.insurance_claim.controller.cms;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cms")
@RequiredArgsConstructor
public class DashboardController {
    @GetMapping("/dashboard")
    public String dashboard() {
        return "cms/dashboard";
    }

    @GetMapping("/report")
    public String report() {
        return "cms/report";
    }
}
