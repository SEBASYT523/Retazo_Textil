package co.edu.unbosque.retazoTextil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.retazoTextil.model.Pedido;
import co.edu.unbosque.retazoTextil.model.PedidoId;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, PedidoId> {
}
