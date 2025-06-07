package co.id.bca.cros.shared.data.repository;

import co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabdatadebitur.projection.DataDebiturProjection;
import co.id.bca.cros.shared.data.model.CrosMasterDebitur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.UUID;

@Repository
public interface CrosMasterDebiturRepository extends JpaRepository<CrosMasterDebitur, UUID> {
    @Query(value = """
            select cmd.id_cros_master_debitur,
                   cmd.cin,
                   cmd.nama_debitur,
                   mkw.region_lng_nm as kantor_wilayah,
                   mkc.branch_name as cabang,
                   cmd.kategori_debitur,
                   cmd.collect_key,
                   cmd.sektor_ekonomi,
                   cmd.nama_grup,
                   (SELECT array_to_json(array_agg(row_to_json(pemegangSaham))) AS pemegang_saham
                    FROM (SELECT cps.nama_pemegang_saham,
                                 cps.pengurus_pemilik_saham_ind,
                                 cps.owner_share
                          FROM cros_pemegang_saham cps
                          WHERE cps.cust_ln_curr_key = cmd.cust_ln_curr_key) AS pemegangSaham)
            from cros_master_debitur cmd
            left join mapping_kantor_wilayah mkw on cmd.kode_kanwil = mkw.region_cd
            left join mapping_kantor_cabang mkc on cmd.kode_kcu = mkc.branch_cd
            where cmd.id_cros_master_debitur = :idCrosMasterDebitur;
            """, nativeQuery = true)
    DataDebiturProjection inquiryDetailDataDebitur(UUID idCrosMasterDebitur);

    @Query("select max(cmd.balanceIdr) from CrosMasterDebitur cmd")
    BigDecimal getMaxBalanceIdr();
}
