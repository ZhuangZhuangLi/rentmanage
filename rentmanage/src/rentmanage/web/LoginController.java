/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package rentmanage.web;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import rentmanage.model.Admin;
import rentmanage.model.Owner;
import rentmanage.service.ClinicService;
import rentmanage.util.LoginInterceptor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import ch.qos.logback.classic.Logger;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 */
@Controller
public class LoginController {
	final static Logger logger = (Logger) LoggerFactory.getLogger(LoginController.class);
	
    private final ClinicService clinicService;


    @Autowired
    public LoginController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest req,Model model,HttpServletResponse resp,ModelAndView mov) throws ServletException, IOException {
    	String loginName=(String)req.getParameter("loginName");
    	String password=(String)req.getParameter("password");
    	Admin admin=clinicService.findAdminLogin(loginName, password);
    	req.removeAttribute("error");
    	if(admin!=null){
    		req.getSession().setAttribute("loginUser", admin.getLogin_name());
    		req.getSession().setAttribute("loginType", "1");
    		logger.info(admin.toString());
    		 Collection<Owner> results = this.clinicService.findOwnerByLastName("");
    		 model.addAttribute("selections", results);
             return "owners/ownersList";
    	}
    	Owner owner =clinicService.findOwnerLogin(loginName, password);
    	if(owner!=null){
    		logger.info(owner.toString());
    		req.getSession().setAttribute("loginUser", owner.getLoginname());
    		req.getSession().setAttribute("loginType", "2");
    		req.getSession().setAttribute("loginId", String.valueOf(owner.getId()));
    		req.getRequestDispatcher("/owner/"+owner.getId()+"/login").forward(req, resp);
    		return "owners/ownerDetails";
    	}
    	req.setAttribute("error", "no such user");
    	return "/login/login";
    }
    
    @RequestMapping("/owner/{ownerId}/login")
    public ModelAndView showOwner(@PathVariable("ownerId") int ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(this.clinicService.findOwnerById(ownerId));
        return mav;
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest req,Model model,HttpServletResponse resp,ModelAndView mov) throws ServletException, IOException {
    	req.getSession().removeAttribute("loginUser");
    	req.getSession().removeAttribute("loginType");
       	req.getSession().removeAttribute("loginId");
    	return "/login/login";
    }
    

}
