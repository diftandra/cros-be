package co.id.bca.cros.shared.data.repository;

import co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabposisifasilitassaatnpl.projection.PosisiFasilitasCol3Projection;
import co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabposisifasilitassaatnpl.projection.PosisiFasilitasCol4Projection;
import co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabposisifasilitassaatnpl.projection.PosisiFasilitasCol5Projection;
import co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabposisifasilitassaatnpl.projection.PosisiFasilitasNplProjection;
import co.id.bca.cros.shared.data.model.CrosNpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CrosNplRepository extends JpaRepository<CrosNpl, UUID> {
    @Query(value = """
            select cn.id_cros_npl,
                   cn.no_rekening_pinjaman as no_rekening_ils,
                   cn.no_komitmen,
                   cn.nama_produk          as jenis_fasilitas,
                   cn.collect_key          as kolektibilitas,
                   mkw.region_lng_nm       as nama_kanwil,
                   mkc.branch_name         as nama_kcu,
                   cn.npl_balance_ocur,
                   cn.npl_bunga_ocur,
                   cn.npl_denda_ocur,
                   cn.npl_balance_idr,
                   cn.npl_bunga_idr,
                   cn.npl_denda_idr,
                   cn.mata_uang,
                   cn.npl_date             as tgl_npl
            from cros_master_debitur cmd
            left join cros_npl cn on cn.cust_ln_curr_key = cmd.cust_ln_curr_key and cn.acct_region_key = cmd.acct_region_key and cn.acct_kcu_key = cmd.acct_kcu_key
            left join mapping_kantor_wilayah mkw on cmd.kode_kanwil = mkw.region_cd
            left join mapping_kantor_cabang mkc on cmd.kode_kcu = mkc.branch_cd
            where cmd.id_cros_master_debitur = :idCrosMasterDebitur
            order by cn.nama_produk, cn.no_rekening_pinjaman, cn.no_komitmen;
            """, nativeQuery = true)
    List<PosisiFasilitasNplProjection> inquiryDetailPosisiFasilitasNpl(UUID idCrosMasterDebitur);

    @Query(value = """
            select cn.id_cros_npl,
                   cn.no_rekening_pinjaman as no_rekening_ils,
                   cn.no_komitmen,
                   cn.nama_produk          as jenis_fasilitas,
                   cn.collect_key          as kolektibilitas,
                   mkw.region_lng_nm       as nama_kanwil,
                   mkc.branch_name         as nama_kcu,
                   cn.col_3_balance_ocur   as col3_balance_ocur,
                   cn.col_3_bunga_ocur     as col3_bunga_ocur,
                   cn.col_3_denda_ocur     as col3_denda_ocur,
                   cn.col_3_balance_idr    as col3_balance_idr,
                   cn.col_3_bunga_idr      as col3_bunga_idr,
                   cn.col_3_denda_idr      as col3_denda_idr,
                   cn.mata_uang,
                   cn.col_3_date           as tgl_kolek3
            from cros_master_debitur cmd
            left join cros_npl cn on cn.cust_ln_curr_key = cmd.cust_ln_curr_key and cn.acct_region_key = cmd.acct_region_key and cn.acct_kcu_key = cmd.acct_kcu_key
            left join mapping_kantor_wilayah mkw on cmd.kode_kanwil = mkw.region_cd
            left join mapping_kantor_cabang mkc on cmd.kode_kcu = mkc.branch_cd
            where cmd.id_cros_master_debitur = :idCrosMasterDebitur
            order by cn.nama_produk, cn.no_rekening_pinjaman, cn.no_komitmen;
            """, nativeQuery = true)
    List<PosisiFasilitasCol3Projection> inquiryDetailPosisiFasilitasCol3(UUID idCrosMasterDebitur);

    @Query(value = """
            select cn.id_cros_npl,
                   cn.no_rekening_pinjaman as no_rekening_ils,
                   cn.no_komitmen,
                   cn.nama_produk          as jenis_fasilitas,
                   cn.collect_key          as kolektibilitas,
                   mkw.region_lng_nm       as nama_kanwil,
                   mkc.branch_name         as nama_kcu,
                   cn.col_4_balance_ocur   as col4_balance_ocur,
                   cn.col_4_bunga_ocur     as col4_bunga_ocur,
                   cn.col_4_denda_ocur     as col4_denda_ocur,
                   cn.col_4_balance_idr    as col4_balance_idr,
                   cn.col_4_bunga_idr      as col4_bunga_idr,
                   cn.col_4_denda_idr      as col4_denda_idr,
                   cn.mata_uang,
                   cn.col_4_date           as tgl_kolek4
            from cros_master_debitur cmd
            left join cros_npl cn on cn.cust_ln_curr_key = cmd.cust_ln_curr_key and cn.acct_region_key = cmd.acct_region_key and cn.acct_kcu_key = cmd.acct_kcu_key
            left join mapping_kantor_wilayah mkw on cmd.kode_kanwil = mkw.region_cd
            left join mapping_kantor_cabang mkc on cmd.kode_kcu = mkc.branch_cd
            where cmd.id_cros_master_debitur = :idCrosMasterDebitur
            order by cn.nama_produk, cn.no_rekening_pinjaman, cn.no_komitmen;
            """, nativeQuery = true)
    List<PosisiFasilitasCol4Projection> inquiryDetailPosisiFasilitasCol4(UUID idCrosMasterDebitur);

    @Query(value = """
            select cn.id_cros_npl,
                   cn.no_rekening_pinjaman as no_rekening_ils,
                   cn.no_komitmen,
                   cn.nama_produk          as jenis_fasilitas,
                   cn.collect_key          as kolektibilitas,
                   mkw.region_lng_nm       as nama_kanwil,
                   mkc.branch_name         as nama_kcu,
                   cn.col_5_balance_ocur   as col5_balance_ocur,
                   cn.col_5_bunga_ocur     as col5_bunga_ocur,
                   cn.col_5_denda_ocur     as col5_denda_ocur,
                   cn.col_5_balance_idr    as col5_balance_idr,
                   cn.col_5_bunga_idr      as col5_bunga_idr,
                   cn.col_5_denda_idr      as col5_denda_idr,
                   cn.mata_uang,
                   cn.col_5_date           as tgl_kolek5
            from cros_master_debitur cmd
            left join cros_npl cn on cn.cust_ln_curr_key = cmd.cust_ln_curr_key and cn.acct_region_key = cmd.acct_region_key and cn.acct_kcu_key = cmd.acct_kcu_key
            left join mapping_kantor_wilayah mkw on cmd.kode_kanwil = mkw.region_cd
            left join mapping_kantor_cabang mkc on cmd.kode_kcu = mkc.branch_cd
            where cmd.id_cros_master_debitur = :idCrosMasterDebitur
            order by cn.nama_produk, cn.no_rekening_pinjaman, cn.no_komitmen;
            """, nativeQuery = true)
    List<PosisiFasilitasCol5Projection> inquiryDetailPosisiFasilitasCol5(UUID idCrosMasterDebitur);
}
