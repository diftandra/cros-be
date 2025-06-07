package co.id.bca.cros.module.search.dto.searchdatadebitur;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SearchDataDebiturFilterDto {
    private String kantorWilayah;
    private String kantorCabang;
    private String kolektibilitas;
    private String kategoriDebitur;
    private String kategoriKredit;
//    private String penanganan; menunggu kejelasan tabel trx
    private Integer statusDebitur;
    private BigDecimal totalOutstandingMin;
    private BigDecimal totalOutstandingMax;
    private String searchKey;
    private String searchValue;
    private int pageNo;
    private int rowsPerPage;
}
