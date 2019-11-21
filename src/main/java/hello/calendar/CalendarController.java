package hello.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.UUID;

@Controller
public class CalendarController {

    private final CalendarJDBCRepository Repo;

    @Autowired
    public CalendarController(CalendarJDBCRepository Repo) {
        this.Repo = Repo;
    }

    @GetMapping("/calendar")
    public String calendar() {
        return "calendar";
    }

    @PostMapping("/addEvent")
    public RedirectView greetingSubmit(@ModelAttribute("event") Event event, ModelMap model) {

            model.addAttribute("title", event.getTitle());
            model.addAttribute("start", event.getStart());
            model.addAttribute("end", event.getEnd());
            model.addAttribute("type", event.getType());
            String id = UUID.randomUUID().toString();
            event.setId(id);
            Repo.insert(event);
        return new RedirectView("calendar");
    }

    @PostMapping("/removeEvent")
    public RedirectView removeEvent(@ModelAttribute("event") Event event, ModelMap model) {

        Repo.deleteById(event.getId());
        return new RedirectView("calendar");
    }
}
