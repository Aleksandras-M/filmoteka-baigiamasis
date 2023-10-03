package lt.filmoteka.filmai.model.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Komentaras {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 512)
    private String tekstas;

    private Date pridejimoData;

    @ManyToOne
    @JoinColumn(name = "filmo_id")
    private Filmas filmas;

    @ManyToOne
    @JoinColumn(name = "vartotojo_id")
    private Vartotojas vartotojas;

    public Komentaras() {
    }

    public Komentaras(long id, String tekstas, Date pridejimoData, Filmas filmas, Vartotojas vartotojas) {
        this.id = id;
        this.tekstas = tekstas;
        this.pridejimoData = pridejimoData;
        this.filmas = filmas;
        this.vartotojas = vartotojas;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTekstas() {
        return tekstas;
    }

    public void setTekstas(String tekstas) {
        this.tekstas = tekstas;
    }

    public Date getPridejimoData() {
        return pridejimoData;
    }

    public void setPridejimoData(Date pridejimoData) {
        this.pridejimoData = pridejimoData;
    }

    public Filmas getFilmas() {
        return filmas;
    }

    public void setFilmas(Filmas filmas) {
        this.filmas = filmas;
    }

    public Vartotojas getVartotojas() {
        return vartotojas;
    }

    public void setVartotojas(Vartotojas vartotojas) {
        this.vartotojas = vartotojas;
    }

    @Override
    public String toString() {
        return "Komentaras{" +
                "id=" + id +
                ", tekstas='" + tekstas + '\'' +
                ", pridejimoData=" + pridejimoData +
                '}';
    }
}
