package lt.filmoteka.filmai.model.repository;

import lt.filmoteka.filmai.model.entity.Privilegija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegijaRepository extends JpaRepository<Privilegija,Long> {
    Privilegija findByPavadinimas(String name);
}
