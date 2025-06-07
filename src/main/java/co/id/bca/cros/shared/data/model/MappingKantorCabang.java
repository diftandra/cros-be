package co.id.bca.cros.shared.data.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mapping_kantor_cabang")
/* todo: table name belum fix, akan diganti di kemudian hari */
public class MappingKantorCabang {
    @Id
    @Size(max = 40)
    @Column(name = "branch_key", length = 40)
    private String branchKey;

    @Size(max = 40)
    @Column(name = "branch_cd", length = 40)
    private String branchCd;

    @Size(max = 40)
    @Column(name = "kcu_cd", length = 40)
    private String kcuCd;

    @Column(name = "branch_shrt_nm", length = Integer.MAX_VALUE)
    private String branchShrtNm;

    @Column(name = "branch_name", length = Integer.MAX_VALUE)
    private String branchName;

    @Column(name = "branch_address1", length = Integer.MAX_VALUE)
    private String branchAddress1;

    @Size(max = 40)
    @Column(name = "branch_address2", length = 40)
    private String branchAddress2;

    @Column(name = "jabo_nonjabotabek", length = Integer.MAX_VALUE)
    private String jaboNonjabotabek;

    @Size(max = 40)
    @Column(name = "city_key", length = 40)
    private String cityKey;

    @Size(max = 50)
    @Column(name = "region_key", length = 50)
    private String regionKey;

    @Column(name = "last_update_dt", length = Integer.MAX_VALUE)
    private String lastUpdateDt;

    @Column(name = "business_load_dt", length = Integer.MAX_VALUE)
    private String businessLoadDt;

    @Column(name = "strt_eff_dt", length = Integer.MAX_VALUE)
    private String strtEffDt;

    @Column(name = "strt_end_dt", length = Integer.MAX_VALUE)
    private String strtEndDt;

    @Column(name = "source_system", length = Integer.MAX_VALUE)
    private String sourceSystem;

    @Column(name = "phone", length = Integer.MAX_VALUE)
    private String phone;

    @Column(name = "contact_person", length = Integer.MAX_VALUE)
    private String contactPerson;

    @Column(name = "region_batch", length = Integer.MAX_VALUE)
    private String regionBatch;

    @Column(name = "group_city", length = Integer.MAX_VALUE)
    private String groupCity;

    @Column(name = "branch_closed_dt", length = Integer.MAX_VALUE)
    private String branchClosedDt;

    @Column(name = "ckk_cd", length = Integer.MAX_VALUE)
    private String ckkCd;

    @Column(name = "referral_cd", length = Integer.MAX_VALUE)
    private String referralCd;

    @Column(name = "ruang_prioritas_ind", length = Integer.MAX_VALUE)
    private String ruangPrioritasInd;

    @Column(name = "region_key_dkk", length = Integer.MAX_VALUE)
    private String regionKeyDkk;

    @Column(name = "timezone", length = Integer.MAX_VALUE)
    private String timezone;

    @Column(name = "geography", length = Integer.MAX_VALUE)
    private String geography;

    @Column(name = "central_kfkk", length = Integer.MAX_VALUE)
    private String centralKfkk;

    @Column(name = "flag_big_city", length = Integer.MAX_VALUE)
    private String flagBigCity;

    @Column(name = "cc_jabo_ind", length = Integer.MAX_VALUE)
    private String ccJaboInd;

    @Size(max = 40)
    @Column(name = "cc_region_cd", length = 40)
    private String ccRegionCd;

    @Column(name = "center_id", length = Integer.MAX_VALUE)
    private String centerId;

    @Column(name = "depot_id", length = Integer.MAX_VALUE)
    private String depotId;

    @Column(name = "actv_dt", length = Integer.MAX_VALUE)
    private String actvDt;

    @Column(name = "status", length = Integer.MAX_VALUE)
    private String status;

    @Column(name = "trip_cd", length = Integer.MAX_VALUE)
    private String tripCd;

    @Column(name = "cit_vendor", length = Integer.MAX_VALUE)
    private String citVendor;

    @Column(name = "gl_region", length = Integer.MAX_VALUE)
    private String glRegion;

    @Column(name = "soa_cd", length = Integer.MAX_VALUE)
    private String soaCd;

    @Column(name = "class_id", length = Integer.MAX_VALUE)
    private String classId;

    @Column(name = "sla_profile_id", length = Integer.MAX_VALUE)
    private String slaProfileId;

    @Column(name = "latitude", length = Integer.MAX_VALUE)
    private String latitude;

    @Column(name = "longitude", length = Integer.MAX_VALUE)
    private String longitude;

    @Column(name = "fax", length = Integer.MAX_VALUE)
    private String fax;

    @Column(name = "email", length = Integer.MAX_VALUE)
    private String email;

    @Column(name = "dati2_cd", length = Integer.MAX_VALUE)
    private String dati2Cd;

}