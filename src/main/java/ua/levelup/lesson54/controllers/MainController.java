package ua.levelup.lesson54.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.levelup.lesson54.dao.ItemsDao;
import ua.levelup.lesson54.domain.Item;
import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping("/")
public class MainController {
    private final ItemsDao itemsDao;
    @Autowired
    public MainController(ItemsDao itemsDao){
        this.itemsDao = itemsDao;
    }
    @GetMapping()
    public String index(Model model){
        List<Item> items = itemsDao.showAllItems();
        model.addAttribute("items", itemsDao.showAllItems());
        return "/items";
    }
    @GetMapping("/new")
    public String newItem(@ModelAttribute("items") Item item){
        return "/new";
    }
    @PostMapping()
    public  String insertOne(@ModelAttribute("items") @Valid Item item, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "/new";
        itemsDao.insertOneItem(item);
        return "redirect:/";
    }
    @GetMapping("/multi_new")
    public String newMultiItem(@ModelAttribute("items") Item item){
        return ("/multi_new");
    }

    @PostMapping("/multi_new")
    public String insertMulti(@ModelAttribute("items") @Valid Item item){
        List<Item> items = new ArrayList<>();
        items.add(new Item(item.getName(), item.getDescription(), item.getPrice()));
        itemsDao.insertMultiItems(items);
        return "redirect:/";
    }



}
