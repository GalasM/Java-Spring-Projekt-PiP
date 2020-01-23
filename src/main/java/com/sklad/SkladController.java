package com.sklad;

import com.footballer.Footballer;
import com.footballer.FootballerJDBCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Controller
public class SkladController {
    private ArrayList<String> sizes = new ArrayList<>();   //[3]-BR [2]-N [1]-P [0]-O
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
        sizes = new ArrayList<>(Arrays.asList(currentTeam.getFormation().split("-")));
        sizes.add(3,"1");
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
                             @RequestParam(name="idTeam")String idTeam,
                             RedirectAttributes attr) {
        String url = "sklad?id="+idTeam;
        List<Footballer> list;
        Footballer f = uRepo.findById(id);
        int size = 11;
        int index = 3;
        switch (f.getPozycja()){
            case "O": {
                list = uRepo.findO(idTeam);
                size = list.size();
                index = 0;
                break;
            }
            case "P": {
                list = uRepo.findP(idTeam);
                size = list.size();
                index = 1;
                break;
            }
            case "N": {
                list = uRepo.findN(idTeam);
                size = list.size();
                index = 2;
                break;
            }
            case "BR": {
                list = uRepo.findBR(idTeam);
                size = list.size();
                index = 3;
                break;
            }
        }
        if(size<Integer.parseInt(sizes.get(index)))
        if(!sRepo.exist(id,idTeam)){
            String idTF = UUID.randomUUID().toString();
            sRepo.insertFootballer(idTF,idTeam,id,"S");
        }
        else{
            sRepo.updateFootballer(idTeam,id,"S");
        }
        else {
            attr.addFlashAttribute("info","Nie można dodać więcej zawodników na tą pozycję przy tej formacji");
            return new RedirectView(url, true);
        }
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