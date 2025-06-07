package co.id.bca.cros.shared.data.repository;

import co.id.bca.cros.module.search.dto.kantordropdownlist.GetKantorDto;
import co.id.bca.cros.shared.data.model.MappingKantorWilayah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MappingKantorWilayahRepository extends JpaRepository<MappingKantorWilayah, String> {
    @Query(value = """
            select region_cd as kantor_code,
                   region_lng_nm as kantor_name
            from mapping_kantor_wilayah
            order by kantor_code;
            """, nativeQuery = true)
    List<GetKantorDto> getKantorWilayahList();
}
