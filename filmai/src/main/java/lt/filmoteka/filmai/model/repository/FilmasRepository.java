package lt.filmoteka.filmai.model.repository;

import lt.filmoteka.filmai.model.entity.Filmas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmasRepository extends JpaRepository<Filmas, Long> {
    List<Filmas> findByPavadinimasLike(String s);
    Filmas findById(long id);
}
