package co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabdatadebitur;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DataDebiturPemegangSahamDto {
    private String namaPemegangSaham;
    private String pengurusPemilikSahamInd;
    private BigDecimal ownerShare;
}
