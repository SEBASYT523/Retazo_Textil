package co.edu.unbosque.retazoTextil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.unbosque.retazoTextil.model.Guardar;
import co.edu.unbosque.retazoTextil.model.GuardarId;

@Repository
public interface GuardarRepository extends JpaRepository<Guardar, GuardarId> {
}
