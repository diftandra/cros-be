package co.id.bca.cros.shared.data.repository;

import co.id.bca.cros.module.search.dto.defaultfilter.GetKanwilKancabDto;
import co.id.bca.cros.module.search.dto.kantordropdownlist.GetKantorDto;
import co.id.bca.cros.shared.data.model.MappingKantorCabang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MappingKantorCabangRepository extends JpaRepository<MappingKantorCabang, String> {
    @Query(value = """
            select mkw.region_cd as kanwil_code,
                   mkw.region_lng_nm as kanwil_name,
                   mkc.branch_cd as kancab_code,
                   mkc.branch_name as kancab_name
            from mapping_kantor_wilayah mkw
                join mapping_kantor_cabang mkc
                    on mkc.region_key = mkw.region_key
            where mkc.branch_cd = :officeCode
            """, nativeQuery = true)
    GetKanwilKancabDto getKanwilKancab(String officeCode);

    @Query(value = """
            select branch_cd as kantor_code,
                   branch_name as kantor_name
            from mapping_kantor_cabang
            where region_key = (select region_key from mapping_kantor_wilayah where region_cd = :kantorWilayahCode)
            order by kantor_code;
            """, nativeQuery = true)
    List<GetKantorDto> getKantorCabangList(String kantorWilayahCode);
}
