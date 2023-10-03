package lt.filmoteka.filmai.model.repository;

import lt.filmoteka.filmai.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByPavadinimas(String roleUser);

}
