package lt.filmoteka.filmai.model.repository;

import lt.filmoteka.filmai.model.entity.Vartotojas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VartotojasRepository extends JpaRepository<Vartotojas, Long> {
    Vartotojas findByUsername(String username);

    boolean existsByUsername(String username);
}
