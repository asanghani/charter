package com.delta.charter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/charterAdmin")
public class CharterAdminController {
	
	 @RequestMapping(value={"adminTest"})
	    public String welcome(){
	        return "welcome";
	    }

}
