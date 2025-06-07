package co.id.bca.cros.shared.data.repository;

import co.id.bca.cros.shared.data.model.CrosPemegangSaham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CrosPemegangSahamRepository extends JpaRepository<CrosPemegangSaham, UUID> {
}
