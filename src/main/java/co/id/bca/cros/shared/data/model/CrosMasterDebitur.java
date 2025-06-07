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
@Table(name = "cros_master_debitur")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CrosMasterDebitur {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idCrosMasterDebitur;

    private LocalDate tglData;
    private Integer custLnCurrKey;
    private Integer collectKey;
    private Integer acctRegionKey;
    private Integer acctKcuKey;
    private String cin;
    private String namaDebitur;
    private LocalDate tglMenjadiNasabah;
    private LocalDate tglMenjadiDebitur;
    private String noGrup;
    private String namaGrup;
    private String sektorEkonomi;
    private String kategoriDebitur;
    private String kodeKanwil;
    private String kodeKcu;
    private String kategoriKredit;
    private Integer flagHb;
    private BigDecimal balanceIdr;
    private Integer hariTunggakan;
    private Boolean isActive;
}
