package lt.filmoteka.filmai.controller;

import lt.filmoteka.filmai.model.entity.Filmas;
import lt.filmoteka.filmai.model.entity.FilmoKategorija;
import lt.filmoteka.filmai.model.repository.FilmasRepository;
import lt.filmoteka.filmai.model.repository.FilmoKategorijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@Controller
public class FilmasController {

    @Autowired
    FilmasRepository filmasRepository;

    @Autowired
    FilmoKategorijaRepository kategorijaRepository;

    @GetMapping("/filmas/paieska")
    public String filmuPaieska(Model model, @RequestParam(required = false) String pavadinimas) {
        List<Filmas> visiFilmai;
        if (StringUtils.isEmpty(pavadinimas)) {
            visiFilmai = filmasRepository.findAll();
        } else {
            visiFilmai = filmasRepository.findByPavadinimasLike("%" + pavadinimas + "%");
        }
        model.addAttribute("visiFilmai", visiFilmai);
        return "filmas/paieska.html";
    }

    @GetMapping("/filmas/informacija/{id}")
    public String filmoInformacija(Model model, @PathVariable long id) {
        Filmas filmas = filmasRepository.findById(id);
        model.addAttribute("filmas", filmas);
        return "filmas/informacija.html";
    }

    @PostMapping("/filmas/istrinti/{id}")
    public String istrintiFilma(Model model, @PathVariable long id) {
        filmasRepository.deleteById(id);
        return "filmas/istrintas.html";
    }

    @GetMapping("/filmas/pridejimas")
    public String filmoIdejimas(Model model) {
        model.addAttribute("filmas", new Filmas());
        List<FilmoKategorija> kategorijos = kategorijaRepository.findAll();
        model.addAttribute("kategorijos", kategorijos);
        return "filmas/pridejimas.html";
    }

    @PostMapping("/filmas/pridetas")
    public String filmasIdetas(Model model, @ModelAttribute Filmas filmas) {
        filmasRepository.save(filmas);
        return "filmas/pridetas.html";
    }

    @GetMapping("/filmas/redagavimas/{id}")
    public String filmoRedagavimas(Model model, @PathVariable long id) {
        Filmas filmas = filmasRepository.findById(id);
        model.addAttribute("filmas", filmas);
        List<FilmoKategorija> kategorijos = kategorijaRepository.findAll();
        model.addAttribute("kategorijos", kategorijos);
        return "filmas/redagavimas.html";
    }



}
