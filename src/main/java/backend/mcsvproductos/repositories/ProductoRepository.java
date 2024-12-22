package backend.mcsvproductos.repositories;

import backend.mcsvproductos.models.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}
