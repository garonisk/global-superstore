package com.ltp.globalsuperstore;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GlobalController {
    @GetMapping("/")
    public String getForm(Model model, @RequestParam (required = false) String id){
        int index = getIndexFromId(id);
        model.addAttribute("item" ,index == Constants.NOT_FOUND ? new Item() : items.get(index));
        model.addAttribute("categories", Constants.CATEGORIES);
        return "form";
    }

    private List<Item> items = new ArrayList<Item>();

    @GetMapping("/inventory")
    public String getInventory(Model model) {
        model.addAttribute("items",items);
        return "inventory";
    }


    @PostMapping("/submitItem")
    public String handleSubmit(Item item) {
        items.add(item);
        return "redirect:/inventory";
    }

    public int getIndexFromId(String id){
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equals(id)) return i;
        }
        return Constants.NOT_FOUND;
    }
}
