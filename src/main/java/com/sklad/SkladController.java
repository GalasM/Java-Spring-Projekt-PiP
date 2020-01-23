package com.sklad;

import com.footballer.Footballer;
import com.footballer.FootballerJDBCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class SkladController {
    private final FootballerJDBCRepository uRepo;
    private final SkladJDBCRepository sRepo;
    @Autowired
    public SkladController(FootballerJDBCRepository uRepo,SkladJDBCRepository sRepo) {
        this.uRepo = uRepo;
        this.sRepo = sRepo;
    }

    @GetMapping("/sklad")
    public String allFootballersN(Model model, @RequestParam(name="id", defaultValue = "1") String id) {
        List<Footballer> listN = uRepo.findN(id);
        List<Footballer> listP = uRepo.findP(id);
        List<Footballer> listO = uRepo.findO(id);
        List<Footballer> listBR = uRepo.findBR(id);
        List<Footballer> listR = uRepo.findR(id);
        List<Footballer> listAllOutTeam = uRepo.findAllWithoutOne(id);

        List<Footballer> listAll = new ArrayList<>();
        listAll.addAll(listN);
        listAll.addAll(listP);
        listAll.addAll(listO);
        listAll.addAll(listBR);
        List<Sklad> allTeams = sRepo.findAllTeams();
        Sklad currentTeam = sRepo.findTeamById(id);
        model.addAttribute("allFootballersN", listN);
        model.addAttribute("allFootballersP", listP);
        model.addAttribute("allFootballersO", listO);
        model.addAttribute("allFootballersBR", listBR);
        model.addAttribute("allFootballersR", listR);
        model.addAttribute("allFootballersOutTeam", listAllOutTeam);
        model.addAttribute("allFootballersInTeam", listAll);
        model.addAttribute("idTeam",id);
        model.addAttribute("allTeams",allTeams);
        model.addAttribute("currentTeam",currentTeam);

        return "sklad";
    }

    @GetMapping("/addT")
    public RedirectView addT(@RequestParam(name="id")String id,
                             @RequestParam(name="idTeam")String idTeam) {
        if(!sRepo.exist(id,idTeam)){
            String idTF = UUID.randomUUID().toString();
            sRepo.insertFootballer(idTF,idTeam,id,"S");
        }
        else{
            sRepo.updateFootballer(idTeam,id,"S");
        }
        String url = "sklad?id="+idTeam;
        return new RedirectView(url,true);
    }

    @GetMapping("/addR")
    public RedirectView addR(@RequestParam(name="id")String id,
                             @RequestParam(name="idTeam")String idTeam) {
        if(sRepo.exist(id,idTeam))
            sRepo.updateFootballer(idTeam,id,"R");
        else{
            String idTF = UUID.randomUUID().toString();
            sRepo.insertFootballer(idTF,idTeam,id,"R");
        }

        String url = "sklad?id="+idTeam;
        return new RedirectView(url,true);
    }

    @GetMapping("/remove")
    public RedirectView remove(@RequestParam(name="id")String id,
                               @RequestParam(name="idTeam")String idTeam) {
        sRepo.removeFootballer(idTeam,id);

        String url = "sklad?id="+idTeam;
        return new RedirectView(url,true);
    }

    @PostMapping("/addTeam")
    public RedirectView addTeam(@ModelAttribute("team") Sklad team, Model model) {
        String id = UUID.randomUUID().toString();
        model.addAttribute("id", id);
        team.setId(id);
        sRepo.insert(team);
        String url = "sklad?id="+id;
        return new RedirectView(url,true);
    }
}