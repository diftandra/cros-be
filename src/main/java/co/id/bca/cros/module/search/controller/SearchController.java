package co.id.bca.cros.module.search.controller;

import co.id.bca.cros.module.auth.service.AuthService;
import co.id.bca.cros.module.search.dto.defaultfilter.DefaultFilterDto;
import co.id.bca.cros.module.search.dto.searchdatadebitur.SearchDataDebiturFilterDto;
import co.id.bca.cros.module.search.dto.searchdatadebitur.SearchDataDebiturOutputSchema;
import co.id.bca.cros.module.search.dto.kantordropdownlist.KantorCabangDropdownMapDto;
import co.id.bca.cros.module.search.dto.kantordropdownlist.KantorWilayahDropdownMapDto;
import co.id.bca.cros.module.search.service.SearchService;
import co.id.bca.cros.shared.dto.ApiBody;
import co.id.bca.cros.shared.dto.PaginationSchema;
import co.id.bca.cros.shared.enumeration.CreditDebiturCategory;
import co.id.bca.cros.shared.enumeration.DebiturStatus;
import co.id.bca.cros.shared.helper.apibody.ApiBodyHelper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/search")
@Validated
public class SearchController {
    private final ApiBodyHelper apiBodyHelper;
    private final SearchService service;
    private final AuthService authService;

    @GetMapping("default-filter")
    public ResponseEntity<ApiBody<DefaultFilterDto>> searchDefaultFilter(HttpServletRequest httpServletRequest) {
        val user = authService.getUserDetail(httpServletRequest);
        return ResponseEntity.ok(apiBodyHelper.create(service.searchDefaultFilter(user)));
    }

    @GetMapping("dropdown/kantor-wilayah")
    public ResponseEntity<ApiBody<KantorWilayahDropdownMapDto>> getKantorWilayahDropdownMap(HttpServletRequest httpServletRequest) {
        val user = authService.getUserDetail(httpServletRequest);
        return ResponseEntity.ok(apiBodyHelper.create(service.getKantorWilayahDropdownMap(user)));
    }

    @GetMapping("dropdown/kantor-wilayah/{kanwil_code}/kantor-cabang")
    public ResponseEntity<ApiBody<KantorCabangDropdownMapDto>> getKantorCabangDropdownMap(
            HttpServletRequest httpServletRequest,
            @PathVariable(value = "kanwil_code") String kantorWilayahCode
    ) {
        val user = authService.getUserDetail(httpServletRequest);
        return ResponseEntity.ok(apiBodyHelper.create(service.getKantorCabangDropdownMap(user, kantorWilayahCode)));
    }

    @GetMapping("data-debitur")
    public ResponseEntity<ApiBody<PaginationSchema<SearchDataDebiturOutputSchema>>> searchDataDebitur(
            HttpServletRequest httpServletRequest,
            @RequestParam(required = false, value = "kantor-wilayah")
            String kantorWilayah,
            @RequestParam(required = false, value = "kantor-cabang")
            String kantorCabang,
            @RequestParam(required = false, value = "kolektibilitas")
            @Parameter(schema = @Schema(allowableValues = {"1", "2", "3", "4", "5", "C"}))
            String kolektibilitas,
            @RequestParam(required = false, value = "kategori-debitur")
            CreditDebiturCategory kategoriDebitur,
            @RequestParam(required = false, value = "kategori-kredit")
            CreditDebiturCategory kategoriKredit,
            @RequestParam(required = false, value = "penanganan")
            String penanganan,
            @RequestParam(required = false, value = "status-debitur")
            DebiturStatus statusDebitur,
            @RequestParam(required = false, value = "outstanding-min")
            BigDecimal totalOutstandingMin,
            @RequestParam(required = false, value = "outstanding-max")
            BigDecimal totalOutstandingMax,
            @RequestParam(required = false, value = "search-key")
            @Parameter(schema = @Schema(allowableValues = {"no_cis", "nama_debitur", "no_rekening_ils", "alamat_jaminan"}))
            String searchKey,
            @RequestParam(required = false, value = "search-value") @Size(max = 100)
            String searchValue,
            @RequestParam(value = "page-no", defaultValue = "1")
            Integer pageNo,
            @RequestParam(value = "rows-per-page", defaultValue = "10")
            Integer rowsPerPage
    ) {
        val user = authService.getUserDetail(httpServletRequest);
        return ResponseEntity.ok(apiBodyHelper.create(service.searchDataDebitur(
                user,
                SearchDataDebiturFilterDto.builder()
                        .kantorWilayah(kantorWilayah)
                        .kantorCabang(kantorCabang)
                        .kolektibilitas(kolektibilitas)
                        .kategoriDebitur(Objects.toString(kategoriDebitur, null))
                        .kategoriKredit(Objects.toString(kategoriKredit, null))
                        .statusDebitur(Optional.ofNullable(statusDebitur).map(DebiturStatus::toFlagNumber).orElse(null))
                        .totalOutstandingMin(totalOutstandingMin)
                        .totalOutstandingMax(totalOutstandingMax)
                        .searchKey(searchKey)
                        .searchValue(searchValue != null ? "%" + searchValue + "%" : null)
                        .pageNo(pageNo)
                        .rowsPerPage(rowsPerPage)
                        .build()
        )));
    }
}
