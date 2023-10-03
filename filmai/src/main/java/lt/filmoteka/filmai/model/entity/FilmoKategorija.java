package lt.filmoteka.filmai.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class FilmoKategorija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String pavadinimas;

    @OneToMany(mappedBy = "kategorija")
    private Set<Filmas> filmai;

    public FilmoKategorija() {
    }

    public FilmoKategorija(long id, String pavadinimas, Set<Filmas> filmai) {
        this.id = id;
        this.pavadinimas = pavadinimas;
        this.filmai = filmai;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPavadinimas() {
        return pavadinimas;
    }

    public void setPavadinimas(String pavadinimas) {
        this.pavadinimas = pavadinimas;
    }

    public Set<Filmas> getFilmai() {
        return filmai;
    }

    public void setFilmai(Set<Filmas> filmai) {
        this.filmai = filmai;
    }
}