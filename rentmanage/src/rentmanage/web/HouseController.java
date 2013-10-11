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

import java.io.UnsupportedEncodingException;
import java.util.Collection;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import rentmanage.model.Owner;
import rentmanage.model.House;
import rentmanage.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ch.qos.logback.classic.Logger;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
@SessionAttributes("house")
public class HouseController {
	
	final static Logger logger = (Logger) LoggerFactory.getLogger(HouseController.class);
	
    private final ClinicService clinicService;


    @Autowired
    public HouseController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }


    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping(value = "/owners/{ownerId}/houses/new", method = RequestMethod.GET)
    public String initCreationForm(@PathVariable("ownerId") int ownerId, Model model) {
        Owner owner = this.clinicService.findOwnerById(ownerId);
        House house = new House();
        house.setOwner(owner);
        owner.addHouse(house);
        model.addAttribute("house", house);
        return "houses/createOrUpdatehouseForm";
    }

    @RequestMapping(value = "/owners/{ownerId}/houses/new", method = RequestMethod.POST)
    public String processCreationForm(@ModelAttribute("house") House house, BindingResult result, SessionStatus status) {
        new HouseValidator().validate(house, result);
        if (result.hasErrors()) {
            return "houses/createOrUpdatehouseForm";
        } else {
            this.clinicService.savehouse(house);
            status.setComplete();
            return "redirect:/owners/{ownerId}";
        }
    }

    @RequestMapping(value = "/owners/*/houses/{houseId}/edit", method = RequestMethod.GET)
    public String initUpdateForm(@PathVariable("houseId") int houseId, Model model) {
    	House house = this.clinicService.findhouseById(houseId);
        model.addAttribute("house", house);
        return "houses/createOrUpdatehouseForm";
    }

    @RequestMapping(value = "/owners/{ownerId}/houses/{houseId}/edit", method = {RequestMethod.PUT, RequestMethod.POST})
    public String processUpdateForm(@ModelAttribute("house") House house, BindingResult result, SessionStatus status) throws UnsupportedEncodingException {
        // we're not using @Valid annotation here because it is easier to define such validation rule in Java
        new HouseValidator().validate(house, result);
        if (result.hasErrors()) {
            return "houses/createOrUpdatehouseForm";
        } else {
        	logger.info(new String(house.getAddress().getBytes(),"GBK"));
            this.clinicService.savehouse(house);
            status.setComplete();
            return "redirect:/owners/{ownerId}";
        }
    }
    public static String decodeUnicode(final String dataStr) {   
    	        int start = 0;   
    	        int end = 0;   
    	        final StringBuffer buffer = new StringBuffer();   
    	       while (start > -1) {   
    	            end = dataStr.indexOf("\\u", start + 2);   
    	            String charStr = "";   
    	            if (end == -1) {   
    	                charStr = dataStr.substring(start + 2, dataStr.length());   
    	            } else {   
    	                charStr = dataStr.substring(start + 2, end);   
    	           }   
    	            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。   
    	           buffer.append(new Character(letter).toString());   
    	            start = end;   
    	       }   
    	        return buffer.toString();   
    	   }   

    
    @RequestMapping(value = "/houses/find", method = RequestMethod.GET)
    public String processFindHouse(Owner owner, BindingResult result, Model model) {

        Collection<House> results = this.clinicService.findhouseByName("");
        if (results.size() > 1) {
            // multiple owners found
            model.addAttribute("selections", results);
            return "houses/housesList";
        } else {
            // 1 owner found
        	 result.rejectValue("lastName", "notFound", "not found");
             return "houses/housesList";
        }
    }
    
    @RequestMapping(value = "/owners/{ownerId}/houses/{houseId}/delete", method = {RequestMethod.PUT, RequestMethod.POST,RequestMethod.GET})
    public String processUpdateForm(@PathVariable("houseId") int houseId) {
            this.clinicService.deleteById(houseId);
            return "redirect:/owners/{ownerId}";
    }
}
