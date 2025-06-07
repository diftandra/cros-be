package co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabdatadebitur;

import co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabdatadebitur.projection.DataDebiturProjection;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DataDebiturDto {
    private UUID idCrosMasterDebitur;
    private String noCis;
    private String namaDebitur;
    private String kantorWilayah;
    private String cabang;
    private String kategoriDebitur;
    private String kolektibilitasSaatIni;
    private String sektorEkonomi;
    private String groupUsaha;
    private List<DataDebiturPemegangSahamDto> susunanPengurusPemilikanSaham;

    public DataDebiturDto(DataDebiturProjection projection) throws JsonProcessingException {
        this.idCrosMasterDebitur = projection.getIdCrosMasterDebitur();
        this.noCis = projection.getCin();
        this.namaDebitur = projection.getNamaDebitur();
        this.kantorWilayah = projection.getKantorWilayah();
        this.cabang = projection.getCabang();
        this.kategoriDebitur = projection.getKategoriDebitur();
        this.kolektibilitasSaatIni = projection.getCollectKey();
        this.sektorEkonomi = projection.getSektorEkonomi();
        this.groupUsaha = projection.getNamaGrup();
        this.susunanPengurusPemilikanSaham = fromDataDebiturPemegangSahamProjection(projection.getPemegangSaham());
    }

    public List<DataDebiturPemegangSahamDto> fromDataDebiturPemegangSahamProjection(String projection) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        if (projection == null) {
            return List.of();
        } else {
            return mapper.readValue(projection, new TypeReference<>() {
            });
        }
    }
}
