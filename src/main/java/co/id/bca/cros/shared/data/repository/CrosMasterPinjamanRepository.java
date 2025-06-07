package co.id.bca.cros.shared.data.repository;

import co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabfasilitasdebitur.projection.PopUpPinjamanProjection;
import co.id.bca.cros.shared.data.model.CrosMasterPinjaman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CrosMasterPinjamanRepository extends JpaRepository<CrosMasterPinjaman, UUID> {
    @Query(value = """
            select cmp.id_cros_master_pinjaman,
                   cmp.no_pinjaman,
                   cmp.nama_produk as jenis_fasilitas,
                   cmp.balance_ocur,
                   cmp.bunga_ocur,
                   cmp.denda_ocur,
                   cmp.balance_idr,
                   cmp.bunga_idr,
                   cmp.denda_idr,
                   cmp.mata_uang,
                   cmp.hari_tunggakan,
                   cmp.tgl_tutup_pinjaman
            from cros_master_komitmen cmk
            left join cros_master_pinjaman cmp on cmp.cust_ln_curr_key = cmk.cust_ln_curr_key and cmp.comm_curr_key = cmk.comm_curr_key
            where cmk.id_cros_master_komitmen = :idCrosMasterKomitmen;
            """, nativeQuery = true)
    PopUpPinjamanProjection inquiryDetailPopUpPinjaman(UUID idCrosMasterKomitmen);
}
