package com.ssg.intern.dev.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {

    @GetMapping("/carousel")
    public String carousel(Model model) {
        List<Item> items = getItems();
        model.addAttribute("items", items);
        return "/feed/carousel";
    }

    private List<Item> getItems() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Image 1", "Description 1", "https://succ.ssgcdn.com/uphoto/202301/20230104135817_1189451428_1_1.jpg"));
        items.add(new Item("Image 2", "Description 2", "https://succ.ssgcdn.com/uphoto/202301/20230104135817_1189451428_2_1.jpg"));
        items.add(new Item("Image 3", "Description 3", "https://succ.ssgcdn.com/uphoto/202301/20230104135817_1189451428_3_1.jpg"));
        return items;
    }

}