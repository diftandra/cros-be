package co.id.bca.cros.shared.data.repository;

import co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabjaminanasetdebitur.JaminanAsetDebiturDto;
import co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabfasilitasdebitur.PopUpDetailJaminanDto;
import co.id.bca.cros.shared.data.model.CrosJaminanDebitur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CrosJaminanDebiturRepository extends JpaRepository<CrosJaminanDebitur, UUID> {
    @Query(value = """
            select cjd.id_cros_jaminan_debitur,
                   cjd.no_collateral,
                   cjd.tipe_jaminan      as jenis_jaminan,
                   cjd.deskripsi_jaminan as detail_jaminan,
                   cjd.nilai_pasar_ocur,
                   cjd.nilai_hak_tanggungan_ocur,
                   cjd.nilai_pasar_idr,
                   cjd.nilai_hak_tanggungan_idr,
                   cjd.mata_uang
            from cros_master_komitmen cmk
                     left join cros_relasi_jaminan_fasilitas crjf on crjf.comm_curr_key = cmk.comm_curr_key
                     left join cros_jaminan_debitur cjd on cjd.coll_curr_key = crjf.coll_curr_key and cjd.cust_ln_curr_key = cmk.cust_ln_curr_key
            where cmk.id_cros_master_komitmen = :idCrosMasterKomitmen;
            """, nativeQuery = true)
    PopUpDetailJaminanDto inquiryDetailPopUpDetailJaminan(UUID idCrosMasterKomitmen);

    @Query(value = """
            select cjd.id_cros_jaminan_debitur,
                   cjd.no_collateral,
                   cjd.tipe_jaminan      as jenis_agunan,
                   cjd.deskripsi_jaminan as detail_jaminan,
                   cjd.nilai_pasar_ocur,
                   cjd.nilai_taksasi_ocur,
                   cjd.nilai_hak_tanggungan_ocur,
                   cjd.nilai_likuidasi_ocur,
                   cjd.nilai_pasar_idr,
                   cjd.nilai_taksasi_idr,
                   cjd.nilai_hak_tanggungan_idr,
                   cjd.nilai_likuidasi_idr,
                   cjd.mata_uang,
                   cjd.solid_non_solid   as jaminan_solid
            from cros_master_debitur cmd
            left join cros_jaminan_debitur cjd on cjd.cust_ln_curr_key = cmd.cust_ln_curr_key
            where cmd.id_cros_master_debitur = :idCrosMasterDebitur
              and cjd.solid_non_solid = :solidNonSolid
            order by cjd.tipe_jaminan, cjd.no_collateral;
            """, nativeQuery = true)
    List<JaminanAsetDebiturDto> inquiryDetailJaminanAsetDebiturSolid(UUID idCrosMasterDebitur, String solidNonSolid);
}
