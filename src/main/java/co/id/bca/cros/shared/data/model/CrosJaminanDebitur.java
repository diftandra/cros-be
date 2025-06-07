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
@Table(name = "cros_jaminan_debitur")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CrosJaminanDebitur {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idCrosJaminanDebitur;

    private LocalDate tglData;
    private Integer custLnCurrKey;
    private Integer collCurrKey;
    private String noCollateral;
    private String tipeJaminan;
    private String mataUang;
    private String deskripsiJaminan;
    private BigDecimal nilaiPasarOcur;
    private BigDecimal nilaiPasarIdr;
    private BigDecimal nilaiTaksasiOcur;
    private BigDecimal nilaiTaksasiIdr;
    private BigDecimal nilaiHakTanggunganOcur;
    private BigDecimal nilaiHakTanggunganIdr;
    private BigDecimal nilaiLikuidasiOcur;
    private BigDecimal nilaiLikuidasiIdr;
    private String solidNonSolid;
    private LocalDate tglBap;
    private Boolean isActive;
}
