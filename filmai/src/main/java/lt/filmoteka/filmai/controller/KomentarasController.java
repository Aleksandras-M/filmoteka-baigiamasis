package lt.filmoteka.filmai.controller;

import lt.filmoteka.filmai.model.entity.Filmas;
import lt.filmoteka.filmai.model.entity.Komentaras;
import lt.filmoteka.filmai.model.entity.Vartotojas;
import lt.filmoteka.filmai.model.repository.KomentarasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class KomentarasController {

    @Autowired
    KomentarasRepository komentarasRepository;

    @PostMapping("/komentaras/prideti/{id}")
    public String pridetiKomentara(Model model, @PathVariable long id, @RequestParam String pridedamasKomentaras) {
        Komentaras komentaras = new Komentaras();
        komentaras.setTekstas(pridedamasKomentaras);
        komentaras.setPridejimoData(new Date());
        Filmas filmas = new Filmas();
        filmas.setId(id);
        komentaras.setFilmas(filmas);
        Vartotojas vartotojas = new Vartotojas();
        vartotojas.setId(id);
        komentaras.setVartotojas(vartotojas);

        komentarasRepository.save(komentaras);
        return "komentaras/pridetas.html";
    }
}
