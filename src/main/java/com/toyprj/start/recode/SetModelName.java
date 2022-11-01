package com.toyprj.start.recode;

import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;

@NoArgsConstructor
public class SetModelName {

    Object principal = SecurityContextHolder.getContext()
            .getAuthentication().getPrincipal();
    UserDetails userDetails = (UserDetails) principal;
    String name = userDetails.getUsername();

    public SetModelName(Model model) {

        model.addAttribute("name", name);
    }

    public String getName(){

        return name;
    }
}
