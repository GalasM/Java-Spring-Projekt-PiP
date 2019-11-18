package hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SkladController {

    @GetMapping("/sklad")
    public String sklad()
    {
        return "sklad";
    }
}
