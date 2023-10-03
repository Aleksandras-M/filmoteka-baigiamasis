package lt.filmoteka.filmai.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Filmas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String pavadinimas;
    @Column(length = 1024)
    private String aprasymas;
    private double IMDBReitingas;

    @ManyToOne
    @JoinColumn(name = "kategorijos_id")
    private FilmoKategorija kategorija;

    @OneToMany(mappedBy = "filmas")
    private Set<Komentaras> komentarai;

    public Filmas() {
    }

    public Filmas(long id, String pavadinimas, String aprasymas, double IMDBReitingas, FilmoKategorija kategorija, Set<Komentaras> komentarai) {
        this.id = id;
        this.pavadinimas = pavadinimas;
        this.aprasymas = aprasymas;
        this.IMDBReitingas = IMDBReitingas;
        this.kategorija = kategorija;
        this.komentarai = komentarai;
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

    public String getAprasymas() {
        return aprasymas;
    }

    public void setAprasymas(String aprasymas) {
        this.aprasymas = aprasymas;
    }

    public double getIMDBReitingas() {
        return IMDBReitingas;
    }

    public void setIMDBReitingas(double IMDBReitingas) {
        this.IMDBReitingas = IMDBReitingas;
    }

    public FilmoKategorija getKategorija() {
        return kategorija;
    }

    public void setKategorija(FilmoKategorija kategorija) {
        this.kategorija = kategorija;
    }

    public Set<Komentaras> getKomentarai() {
        return komentarai;
    }

    public void setKomentarai(Set<Komentaras> komentarai) {
        this.komentarai = komentarai;
    }

    @Override
    public String toString() {
        return "Filmas{" +
                "id=" + id +
                ", pavadinimas='" + pavadinimas + '\'' +
                ", aprasymas='" + aprasymas + '\'' +
                ", IMDB Reitingas=" + IMDBReitingas +
                ", kategorija=" + kategorija +
                ", komentarai=" + komentarai +
                '}';
    }
}