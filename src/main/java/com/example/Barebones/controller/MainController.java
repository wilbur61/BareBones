package com.example.Barebones.controller;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.Barebones.model.Role;
import com.example.Barebones.model.User;
import com.example.Barebones.service.UserService;


@Controller
public class MainController {
	
	@Autowired
	private UserService userService;

//    @GetMapping("/")
//    public String root(HttpSession session,Authentication authentication) {
//        //return "index";
//    	System.out.println("IN  MainController->root()");
//    	System.out.println(">>>>>>>USER ="+authentication.getName());
//    	User existing = userService.findByEmail(authentication.getName());
//    	System.out.println("User firstName="+existing.getFirstName());
//    	System.out.println("User lastName="+existing.getLastName());
//    	System.out.println("User Id="+existing.getId());
//    	
//		System.out.println("USER ROLE="+existing.getRoles());
//    	
//
//		java.util.Collection<Role> roles = existing.getRoles();
//		String userRole = roles.toString();
//		System.out.println("COLLECTION USER ROLE="+userRole);
//		
//		if(userRole.equals("[ROLE_SUPER]")) {
//			return "redirect:/admin";
//		}
//		
//		return "redirect:/students";
//    }

	//
	// UPDATE users_roles   user_id to point to role_is that is superuser in role table
	// to enable that id to be superuser!
	// 
	@GetMapping("/")
	public String root(HttpSession session) {

		System.out.println("IN  MainController->root()");
		session.getAttributeNames();
		
		Collection<? extends GrantedAuthority> userRoles;

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			userRoles = ((UserDetails) principal).getAuthorities();

			for (GrantedAuthority userRole : userRoles) {
				if (userRole.getAuthority().equals("ROLE_SUPER")) {
					System.out.println("USER ROLE="+userRole.getAuthority());
					return "redirect:/admin";
				}
			}
		}
		
		System.out.println("USER ROLE Defaults to Regular USER");
		return "redirect:/students";
	}
	
    @GetMapping("/admin")
    public String admin(Model model) {
    	System.out.println("IN  MainController->admin()");
        return "admin";
    }
    
    @GetMapping("/login")
    public String login(Model model) {
    	System.out.println("IN  MainController->login()");
        return "login";
    }

    @GetMapping("/user")
    public String userIndex() {
    	System.out.println("IN  MainController->userIndex()");
        return "user/index";
    }

    @ResponseBody
	@GetMapping("/logoutSuccess")
    public String logoutResponse()
    {
    	System.out.println("IN  MainController->logoutResponse()");
        return "Logged Out!!!!";
    }

}
