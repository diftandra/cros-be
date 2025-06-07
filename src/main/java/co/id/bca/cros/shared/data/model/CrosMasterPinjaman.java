package co.id.bca.cros.shared.data.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "cros_master_pinjaman")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CrosMasterPinjaman {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idCrosMasterPinjaman;

    private LocalDate tglData;
    private Integer custLnCurrKey;
    private Integer acctLnCurrKey;
    private Integer commCurrKey;
    private Integer lnCurrKey;
    private Integer productSubTypeKey;
    private Integer facLnSegKey;
    private Integer collectKey;
    private Integer acctRegionKey;
    private Integer acctKcuKey;
    private String kodeKanwil;
    private String kodeKcu;
    private String noRekeningPinjaman;
    private String noPinjaman;
    private LocalDate tglBukaPinjaman;
    private LocalDate tglTutupPinjaman;
    private String kategoriKredit;
    private String tipeProduk;
    private String kodeProduk;
    private String namaProduk;
    private String mataUang;
    private BigDecimal balanceOcur;
    private BigDecimal balanceIdr;
    private BigDecimal bungaOcur;
    private BigDecimal bungaIdr;
    private BigDecimal dendaOcur;
    private BigDecimal dendaIdr;
    private BigDecimal balanceOcurHb;
    private BigDecimal balanceIdrHb;
    private BigDecimal bungaOcurHb;
    private BigDecimal bungaIdrHb;
    private BigDecimal dendaOcurHb;
    private BigDecimal dendaIdrHb;
    private LocalDate tglHb;
    private BigDecimal balanceOcurHt;
    private BigDecimal balanceIdrHt;
    private BigDecimal bungaOcurHt;
    private BigDecimal bungaIdrHt;
    private BigDecimal dendaOcurHt;
    private BigDecimal dendaIdrHt;
    private LocalDate tglHt;
    private Integer hariTunggakan;
    private Integer isRestrukturisasi;
    private String kodeRestrukturisasi;
    private Integer flagHb;
}
