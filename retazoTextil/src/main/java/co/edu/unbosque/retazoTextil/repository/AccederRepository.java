package co.edu.unbosque.retazoTextil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.unbosque.retazoTextil.model.Acceder;
import co.edu.unbosque.retazoTextil.model.AccederId;

@Repository
public interface AccederRepository extends JpaRepository<Acceder, AccederId> {
}
