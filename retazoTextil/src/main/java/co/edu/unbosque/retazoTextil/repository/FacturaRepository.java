package co.edu.unbosque.retazoTextil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.unbosque.retazoTextil.model.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer> {
}
