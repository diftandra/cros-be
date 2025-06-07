package co.id.bca.cros.shared.data.repository;

import co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabfasilitasdebitur.FasilitasDebiturDto;
import co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabfasilitasdebitur.projection.PopUpHapusBukuProjection;
import co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabfasilitasdebitur.projection.PopUpHapusTagihProjection;
import co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabgraph.GraphPerkembanganFasilitasDto;
import co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabgraph.GraphTotalEksposurDto;
import co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabjaminanasetdebitur.PopUpFasilitasCollateralDto;
import co.id.bca.cros.shared.data.model.CrosMasterKomitmen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CrosMasterKomitmenRepository extends JpaRepository<CrosMasterKomitmen, UUID> {
    @Query(value = """
            select cmk.id_cros_master_komitmen,
                   cmk.no_rekening_pinjaman as no_rekening_ils,
                   cmk.no_komitmen          as no_komitmen,
                   cmk.nama_produk          as jenis_fasilitas,
                   cmk.collect_key          as kolektibilitas,
                   mkw.region_lng_nm        as kantor_wilayah,
                   mkc.branch_name          as cabang,
                   cmk.balance_ocur,
                   cmk.bunga_ocur,
                   cmk.denda_ocur,
                   cmk.balance_idr,
                   cmk.bunga_idr,
                   cmk.denda_idr,
                   cmk.mata_uang,
                   cmk.ppap,
                   cmk.ckpn
            from cros_master_debitur cmd
            left join cros_master_komitmen cmk on cmk.cust_ln_curr_key = cmd.cust_ln_curr_key and cmk.acct_region_key = cmd.acct_region_key and cmk.acct_kcu_key = cmd.acct_kcu_key
            left join mapping_kantor_wilayah mkw on cmd.kode_kanwil = mkw.region_cd
            left join mapping_kantor_cabang mkc on cmd.kode_kcu = mkc.branch_cd
            where cmd.id_cros_master_debitur = :idCrosMasterDebitur
            order by cmk.nama_produk, cmk.no_rekening_pinjaman, cmk.no_komitmen;
            """, nativeQuery = true)
    List<FasilitasDebiturDto> inquiryDetailFasilitasDebitur(UUID idCrosMasterDebitur);

    @Query(value = """
            select cmk.id_cros_master_komitmen,
                   cmk.no_rekening_pinjaman as no_rekening_ils,
                   cmk.no_komitmen,
                   cmk.balance_ocur_hb,
                   cmk.bunga_ocur_hb,
                   cmk.denda_ocur_hb,
                   cmk.balance_idr_hb,
                   cmk.bunga_idr_hb,
                   cmk.denda_idr_hb,
                   cmk.mata_uang,
                   cmk.tgl_hb
            from cros_master_komitmen cmk
            where cmk.id_cros_master_komitmen = :idCrosMasterKomitmen;
            """, nativeQuery = true)
    PopUpHapusBukuProjection inquiryDetailPopUpHapusBuku(UUID idCrosMasterKomitmen);

    @Query(value = """
            select cmk.id_cros_master_komitmen,
                   cmk.no_rekening_pinjaman as no_rekening_ils,
                   cmk.no_komitmen,
                   cmk.balance_ocur_ht,
                   cmk.bunga_ocur_ht,
                   cmk.denda_ocur_ht,
                   cmk.balance_idr_ht,
                   cmk.bunga_idr_ht,
                   cmk.denda_idr_ht,
                   cmk.mata_uang,
                   cmk.tgl_ht
            from cros_master_komitmen cmk
            where cmk.id_cros_master_komitmen = :idCrosMasterKomitmen;
            """, nativeQuery = true)
    PopUpHapusTagihProjection inquiryDetailPopUpHapusTagih(UUID idCrosMasterKomitmen);

    @Query(value = """
            select cmk.nama_produk                                       as jenis_fasilitas,
                   sum(cmk.balance_idr)                                  as outstanding,
                   (sum(cmk.balance_idr) * 100.0 / (select sum(cmk.balance_idr)
                                                    from cros_master_debitur cmd
                                                    left join cros_master_komitmen cmk
                                                        on cmk.cust_ln_curr_key = cmd.cust_ln_curr_key
                                                               and cmk.acct_region_key = cmd.acct_region_key
                                                               and cmk.acct_kcu_key = cmd.acct_kcu_key
                                                    where cmd.id_cros_master_debitur = :idCrosMasterDebitur)) as proporsi
            from cros_master_debitur cmd
            left join cros_master_komitmen cmk
                on cmk.cust_ln_curr_key = cmd.cust_ln_curr_key
                       and cmk.acct_region_key = cmd.acct_region_key
                       and cmk.acct_kcu_key = cmd.acct_kcu_key
            where cmd.id_cros_master_debitur = :idCrosMasterDebitur
            group by cmk.nama_produk;
            """, nativeQuery = true)
    List<GraphTotalEksposurDto> inquiryDetailGraphTotalEksposur(UUID idCrosMasterDebitur);

    @Query(value = """
            select cmk.nama_produk                  as jenis_fasilitas,
                   to_char(cmk.tgl_data, 'YYYY-MM') as bulan,
                   sum(cmk.balance_idr)             as outstanding
            from cros_master_debitur cmd
            left join cros_master_komitmen cmk on cmk.cust_ln_curr_key = cmd.cust_ln_curr_key and cmk.acct_region_key = cmd.acct_region_key and cmk.acct_kcu_key = cmd.acct_kcu_key
            where cmd.id_cros_master_debitur = :idCrosMasterDebitur
              and (
                (extract(year from cmk.tgl_data) > :tahunAwal or
                 (extract(year from cmk.tgl_data) = :tahunAwal and extract(month from cmk.tgl_data) >= :bulanAwal))
                    and (extract(year from cmk.tgl_data) < :tahunAkhir or
                         (extract(year from cmk.tgl_data) = :tahunAkhir and extract(month from cmk.tgl_data) <= :bulanAkhir))
                )
            group by jenis_fasilitas, bulan
            order by jenis_fasilitas, bulan;
            """, nativeQuery = true)
    List<GraphPerkembanganFasilitasDto> inquiryDetailGraphPerkembanganFasilitas(UUID idCrosMasterDebitur, int bulanAwal, int tahunAwal, int bulanAkhir, int tahunAkhir);

    @Query(value = """
            select cjd.id_cros_jaminan_debitur,
                   cmd.nama_debitur,
                   cmk.no_rekening_pinjaman as no_rekening_ils,
                   cmk.no_komitmen,
                   cmk.nama_produk          as jenis_fasilitas,
                   cmk.balance_ocur,
                   cmk.balance_idr,
                   cmk.mata_uang
            from cros_master_debitur cmd
            left join cros_jaminan_debitur cjd on cjd.cust_ln_curr_key = cmd.cust_ln_curr_key
            left join cros_master_komitmen cmk on cmk.cust_ln_curr_key = cmd.cust_ln_curr_key and (cmk.kategori_kredit = cmd.kategori_kredit and cmk.acct_kcu_key = cmd.acct_kcu_key)
            where cmd.id_cros_master_debitur = :idCrosMasterDebitur
              and cjd.id_cros_jaminan_debitur = :idCrosJaminanDebitur;
            """, nativeQuery = true)
    List<PopUpFasilitasCollateralDto> inquiryDetailPopUpFasilitasCollateral(UUID idCrosMasterDebitur, UUID idCrosJaminanDebitur);
}
