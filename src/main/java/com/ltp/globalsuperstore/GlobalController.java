package com.ltp.globalsuperstore;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class GlobalController {

    GlobalService globalService = new GlobalService();
    @GetMapping("/")
    public String getForm(Model model, @RequestParam (required = false) String id){
        model.addAttribute("item" , globalService.getItemFromId(id));
        return "form";
    }
    @GetMapping("/inventory")
    public String getInventory(Model model) {
        model.addAttribute("items",globalService.getItems());
        return "inventory";
    }
    @PostMapping("/submitItem")
    public String handleSubmit(@Valid Item item , BindingResult result, RedirectAttributes redirectAttributes) {
        if (item.getPrice() < item.getDiscount()){
            result.rejectValue("price","","Price cannot be less than discount");
        }
        if (result.hasErrors()) return "form";
        String status = globalService.handleSubmit(item);
        redirectAttributes.addFlashAttribute("status", status);
        return "redirect:/inventory";
    }




}
