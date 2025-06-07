package co.id.bca.cros.shared.data.repository;

import co.id.bca.cros.shared.data.model.CrosRelasiJaminanFasilitas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CrosRelasiJaminanFasilitasRepository extends JpaRepository<CrosRelasiJaminanFasilitas, UUID> {
}
