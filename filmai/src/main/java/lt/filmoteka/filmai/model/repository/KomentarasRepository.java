package lt.filmoteka.filmai.model.repository;

import lt.filmoteka.filmai.model.entity.Komentaras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KomentarasRepository extends JpaRepository<Komentaras,Long> {
}
