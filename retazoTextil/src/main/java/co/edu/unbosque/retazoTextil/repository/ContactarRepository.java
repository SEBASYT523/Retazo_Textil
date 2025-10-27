package co.edu.unbosque.retazoTextil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.unbosque.retazoTextil.model.Contactar;
import co.edu.unbosque.retazoTextil.model.ContactarId;

@Repository
public interface ContactarRepository extends JpaRepository<Contactar, ContactarId> {
}
