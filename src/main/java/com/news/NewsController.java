package com.news;

import com.footballer.Footballer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.UUID;


@Controller
public class NewsController {

    private final NewsJDBCRepository uRepo;

    public NewsController(NewsJDBCRepository uRepo) {
        this.uRepo = uRepo;
    }

    @PostMapping("/addNews")
    public RedirectView greetingSubmit(@ModelAttribute("footballer") News news, RedirectAttributes attr, ModelMap model) {
        model.addAttribute("tytul", news.getTytul());
        model.addAttribute("tresc", news.getTresc());
        String id = UUID.randomUUID().toString();
        news.setAid(id);
        uRepo.insert(news);
        attr.addFlashAttribute("addedNews","Dodano news!");
        return new RedirectView("news");
    }

    @GetMapping("/news")
    public String index(Model model) {
        List<News> list = uRepo.findAll();
        model.addAttribute("allNews", list);
        return "news.html";
    }

}
