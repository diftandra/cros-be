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
@Table(name = "mapping_kantor_wilayah")
/* todo: table name belum fix, akan diganti di kemudian hari */
public class MappingKantorWilayah {
    @Id
    @Size(max = 50)
    @Column(name = "region_key", length = 50)
    private String regionKey;

    @Size(max = 50)
    @Column(name = "region_cd", length = 50)
    private String regionCd;

    @Column(name = "region_shrt_nm", length = Integer.MAX_VALUE)
    private String regionShrtNm;

    @Column(name = "region_lng_nm", length = Integer.MAX_VALUE)
    private String regionLngNm;

    @Column(name = "strt_eff_dt", length = Integer.MAX_VALUE)
    private String strtEffDt;

    @Column(name = "strt_end_dt", length = Integer.MAX_VALUE)
    private String strtEndDt;

    @Column(name = "last_update_dt", length = Integer.MAX_VALUE)
    private String lastUpdateDt;

    @Column(name = "business_load_dt", length = Integer.MAX_VALUE)
    private String businessLoadDt;

    @Column(name = "source_system", length = Integer.MAX_VALUE)
    private String sourceSystem;

}