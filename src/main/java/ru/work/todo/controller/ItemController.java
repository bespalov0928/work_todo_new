package ru.work.todo.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.work.todo.model.Category;
import ru.work.todo.model.Item;
import ru.work.todo.model.User;
import ru.work.todo.service.CategorySevice;
import ru.work.todo.service.ItemService;
import ru.work.todo.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ItemController {
    private final ItemService itemService;
    private final CategorySevice categorySevice;
private final UserService userService;

    public ItemController(ItemService itemService, CategorySevice categorySevice, UserService userService) {
        this.itemService = itemService;
        this.categorySevice = categorySevice;
        this.userService = userService;
    }

    @GetMapping("/index")
    public String index(Model model, HttpSession httpSession) {
        User user = getUser(httpSession);
        model.addAttribute("items", itemService.findAll());
        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping("/indexAll")
    public String indexAll(Model model, HttpSession httpSession) {
        User user = getUser(httpSession);
        model.addAttribute("items", itemService.findAll());
        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping("/indexNew")
    public String indexNew(Model model, HttpSession httpSession) {
        User user = getUser(httpSession);
        model.addAttribute("items", itemService.findAllNew());
        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping("/indexDone")
    public String indexDone(Model model, HttpSession httpSession) {
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
        if (user == null) {
            user = new User();
            user.setUsername("Гость");
        }
        return user;
    }

    @GetMapping("/doneItem/{itemId}")
    private String doneItem(@PathVariable("itemId") int id) {
        itemService.updateByIdWhenDone(id);
        return "redirect:/index";
    }

    @GetMapping("/addItem")
    public String addItem(Model model, HttpSession httpSession) {
        model.addAttribute("user", getUser(httpSession));
        model.addAttribute("categories", categorySevice.findAll());
        return "formAddItem";
    }

    @PostMapping("/formAddItem")
    public String formAddItem(@ModelAttribute Item item, @RequestParam("category.id") List<Integer> catIdList, @RequestParam("user.id") Integer userId) {
        User user = userService.findByUserId(userId).get();
        Category category = null;
        for (var catId : catIdList) {
            category = categorySevice.findById(catId);
        }
        item.setUser(user);
        item.setCategory(category);
        itemService.add(item);
        return "redirect:/index";
    }
}
