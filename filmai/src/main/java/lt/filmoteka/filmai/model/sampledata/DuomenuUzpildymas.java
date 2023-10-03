package lt.filmoteka.filmai.model.sampledata;

import lt.filmoteka.filmai.model.entity.*;
import lt.filmoteka.filmai.model.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DuomenuUzpildymas implements ApplicationListener<ContextRefreshedEvent> {

    boolean pirmasPaleidimas = false;
    @Autowired
    FilmoKategorijaRepository filmoKategorijaRepository;

    @Autowired
    FilmasRepository filmasRepository;

    @Autowired
    VartotojasRepository vartotojasRepository;

    @Autowired
    KomentarasRepository komentarasRepository;

    @Autowired
    PrivilegijaRepository privilegijaRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (pirmasPaleidimas) {
            sukurtiKategorijas();
            sukurtiFilmus();
            sukurtiVartotojus();
            sukurtiKomentarus();
        }
    }

    public void sukurtiKategorijas() {
        FilmoKategorija kategorija1 = new FilmoKategorija();
        kategorija1.setPavadinimas("Siaubo");
        FilmoKategorija kategorija2 = new FilmoKategorija();
        kategorija2.setPavadinimas("Veiksmo");
        FilmoKategorija kategorija3 = new FilmoKategorija();
        kategorija3.setPavadinimas("Nuotykių");
        FilmoKategorija kategorija4 = new FilmoKategorija();
        kategorija4.setPavadinimas("Dokumentika");

        filmoKategorijaRepository.save(kategorija1);
        filmoKategorijaRepository.save(kategorija2);
        filmoKategorijaRepository.save(kategorija3);
        filmoKategorijaRepository.save(kategorija4);
    }

    public void sukurtiFilmus() {
        Filmas filmas1 = new Filmas();
        filmas1.setPavadinimas("Filmas 1");
        filmas1.setAprasymas("Aprašymas: Tai emocinis drama apie meilės ir netekties jausmus.");
        filmas1.setIMDBReitingas(8.5);
        FilmoKategorija kategorija1 = new FilmoKategorija();
        kategorija1.setId(1);
        filmas1.setKategorija(kategorija1);

        Filmas filmas2 = new Filmas();
        filmas2.setPavadinimas("Filmas 2");
        filmas2.setAprasymas("Šis veiksmo filmas pasakoja apie neįtikėtinus šnipų nuotykius.");
        filmas2.setIMDBReitingas(7.2);
        FilmoKategorija kategorija2 = new FilmoKategorija();
        kategorija2.setId(2);
        filmas2.setKategorija(kategorija2);

        Filmas filmas3 = new Filmas();
        filmas3.setPavadinimas("Filmas 3");
        filmas3.setAprasymas("Fantastinė istorija apie kosminių laivų kelionę į kitą galaktiką.");
        filmas3.setIMDBReitingas(9.0);
        FilmoKategorija kategorija3 = new FilmoKategorija();
        kategorija3.setId(3);
        filmas3.setKategorija(kategorija3);

        Filmas filmas4 = new Filmas();
        filmas4.setPavadinimas("Filmas 4");
        filmas4.setAprasymas("Komedija apie neprastą draugystę tarp skirtingo amžiaus žmonių.");
        filmas4.setIMDBReitingas(6.8);
        FilmoKategorija kategorija4 = new FilmoKategorija();
        kategorija4.setId(4);
        filmas4.setKategorija(kategorija4);

        Filmas filmas5 = new Filmas();
        filmas5.setPavadinimas("Filmas 5");
        filmas5.setAprasymas("Mistinė trilerio nuotykių istorija su paslaptingomis paslaptimis.");
        filmas5.setIMDBReitingas(8.1);
        FilmoKategorija kategorija5 = new FilmoKategorija();
        kategorija5.setId(1);
        filmas5.setKategorija(kategorija5);

        filmasRepository.save(filmas1);
        filmasRepository.save(filmas2);
        filmasRepository.save(filmas3);
        filmasRepository.save(filmas4);
        filmasRepository.save(filmas5);

    }

    public void sukurtiVartotojus() {
        Privilegija readPrivilegija = sukurtiPrivilegijaJeiguNera("READ_PRIVILEGIJA");
        Privilegija writePrivilegija = sukurtiPrivilegijaJeiguNera("WRITE_PRIVILEGIJA");
        Set<Privilegija> adminoPrivilegijos = Stream.of(readPrivilegija, writePrivilegija).collect(Collectors.toSet());
        Set<Privilegija> vartotojoPrivilegijos = Stream.of(readPrivilegija).collect(Collectors.toSet());
        Role adminoRole = sukurtiRoleJeiguNera("ROLE_ADMIN", adminoPrivilegijos);
        Role vartotojoRole = sukurtiRoleJeiguNera("ROLE_USER", vartotojoPrivilegijos);


        Vartotojas vartotojas1 = new Vartotojas();
        vartotojas1.setUsername("admin");
        vartotojas1.setPassword(passwordEncoder.encode("admin"));
        vartotojas1.setEnabled(true);
        vartotojas1.setExpiredToken(false);
        vartotojas1.setRoles(Stream.of(adminoRole).collect(Collectors.toSet()));

        Vartotojas vartotojas2 = new Vartotojas();
        vartotojas2.setUsername("user");
        vartotojas2.setPassword(passwordEncoder.encode("user"));
        vartotojas2.setEnabled(true);
        vartotojas2.setExpiredToken(false);
        vartotojas2.setRoles(Stream.of(vartotojoRole).collect(Collectors.toSet()));

        vartotojasRepository.save(vartotojas1);
        vartotojasRepository.save(vartotojas2);
    }

    public void sukurtiKomentarus() {
        Komentaras komentaras1 = new Komentaras();
        komentaras1.setTekstas("Labai patiko.");
        komentaras1.setPridejimoData(new Date());
        Filmas receptas1 = new Filmas();
        receptas1.setId(1);
        komentaras1.setFilmas(receptas1);
        Vartotojas vartotojas1 = new Vartotojas();
        vartotojas1.setId(1);
        komentaras1.setVartotojas(vartotojas1);


        komentarasRepository.save(komentaras1);

    }

    public Privilegija sukurtiPrivilegijaJeiguNera(String name) {
        Privilegija privilegija = privilegijaRepository.findByPavadinimas(name);
        if (privilegija == null) {
            privilegija = new Privilegija();
            privilegija.setPavadinimas(name);
            privilegijaRepository.save(privilegija);
        }
        return privilegija;
    }

    public Role sukurtiRoleJeiguNera(String name, Set<Privilegija> privilegijos) {
        Role role = roleRepository.findByPavadinimas(name);
        if (role == null) {
            role = new Role();
            role.setPavadinimas(name);
            role.setPrivilegijos(privilegijos);
            roleRepository.save(role);
        }
        return role;
    }
}
