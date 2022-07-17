package ru.work.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.work.todo.model.Item;
import ru.work.todo.model.User;
import ru.work.todo.service.ItemService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/index")
    public String index(Model model, HttpSession httpSession) {
        User user = getUser(httpSession);
        model.addAttribute("items", itemService.findAll());
        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping("/indexAll")
    public String indexAll(Model model, HttpSession httpSession){
        User user = getUser(httpSession);
        model.addAttribute("items", itemService.findAll());
        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping("/indexNew")
    public String indexNew(Model model, HttpSession httpSession){
        User user = getUser(httpSession);
        model.addAttribute("items", itemService.findAllNew());
        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping("/indexDone")
    public String indexDone(Model model, HttpSession httpSession){
        User user = getUser(httpSession);
        model.addAttribute("items", itemService.findAllDone());
        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping("/getDescription/{itemId}")
    public String getDescription(Model model, @PathVariable("itemId") int id, HttpSession httpSession) {
        User user = getUser(httpSession);
        model.addAttribute("item", itemService.findBuId(id));
        model.addAttribute("user", user);
        return "description";
    }

    private User getUser(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user==null){
            user=new User();
            user.setUsername("Гость");
        }
        return user;
    }

    @GetMapping("/doneItem/{itemId}")
    private String doneItem(@PathVariable("itemId") int id){
        itemService.updateByIdWhenDone(id);
        return "redirect:/index";
    }

    @PostMapping("/addItem")
    public void addItem() {
        //todo
    }
}
