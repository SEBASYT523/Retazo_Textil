package co.edu.unbosque.retazoTextil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.retazoTextil.model.Administrador;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {

}
