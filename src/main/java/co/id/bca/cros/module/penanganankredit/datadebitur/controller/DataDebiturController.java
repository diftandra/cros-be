package co.id.bca.cros.module.penanganankredit.datadebitur.controller;

import co.id.bca.cros.module.auth.service.AuthService;
import co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabdatadebitur.DataDebiturDto;
import co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabfasilitasdebitur.*;
import co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabgraph.GraphPerkembanganFasilitasResultDto;
import co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabgraph.GraphTotalEksposurResultDto;
import co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabjaminanasetdebitur.JaminanAsetDebiturResultDto;
import co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabjaminanasetdebitur.PopUpFasilitasCollateralDto;
import co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabposisifasilitassaatnpl.PosisiFasilitasCol3ResultDto;
import co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabposisifasilitassaatnpl.PosisiFasilitasCol4ResultDto;
import co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabposisifasilitassaatnpl.PosisiFasilitasCol5ResultDto;
import co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabposisifasilitassaatnpl.PosisiFasilitasNplResultDto;
import co.id.bca.cros.module.penanganankredit.datadebitur.service.DataDebiturService;
import co.id.bca.cros.shared.dto.ApiBody;
import co.id.bca.cros.shared.helper.apibody.ApiBodyHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/penanganan-kredit/data-debitur")
public class DataDebiturController {
    private final ApiBodyHelper apiBodyHelper;
    private final DataDebiturService service;
    private final AuthService authService;

    @GetMapping("{id_cros_master_debitur}")
    public ResponseEntity<ApiBody<DataDebiturDto>> inquiryDetailDataDebitur(
            HttpServletRequest httpServletRequest,
            @PathVariable(value = "id_cros_master_debitur") UUID idCrosMasterDebitur
    ) throws JsonProcessingException {
        val user = authService.getUserDetail(httpServletRequest);
        authService.checkMasterDebiturAuthorization(user, idCrosMasterDebitur);
        return ResponseEntity.ok(apiBodyHelper.create(service.inquiryDetailDataDebitur(idCrosMasterDebitur)));
    }

    @GetMapping("fasilitas-debitur/{id_cros_master_debitur}")
    public ResponseEntity<ApiBody<FasilitasDebiturResultDto>> inquiryDetailFasilitasDebitur(
            HttpServletRequest httpServletRequest,
            @PathVariable(value = "id_cros_master_debitur") UUID idCrosMasterDebitur
    ) {
        val user = authService.getUserDetail(httpServletRequest);
        authService.checkMasterDebiturAuthorization(user, idCrosMasterDebitur);
        return ResponseEntity.ok(apiBodyHelper.create(service.inquiryDetailFasilitasDebitur(idCrosMasterDebitur)));
    }

    @GetMapping("pop-up-pinjaman/{id_cros_master_komitmen}")
    public ResponseEntity<ApiBody<PopUpPinjamanDto>> detailPopUpPinjaman(
            HttpServletRequest httpServletRequest,
            @PathVariable(value = "id_cros_master_komitmen") UUID idCrosMasterKomitmen
    ) {
        val user = authService.getUserDetail(httpServletRequest);
        authService.checkMasterKomitmenAuthorization(user, idCrosMasterKomitmen);
        return ResponseEntity.ok(apiBodyHelper.create(service.inquiryDetailPopUpPinjaman(idCrosMasterKomitmen)));
    }

    @GetMapping("pop-up-hapus-buku/{id_cros_master_komitmen}")
    public ResponseEntity<ApiBody<PopUpHapusBukuDto>> detailPopUpHapusBuku(
            HttpServletRequest httpServletRequest,
            @PathVariable(value = "id_cros_master_komitmen") UUID idCrosMasterKomitmen
    ) {
        val user = authService.getUserDetail(httpServletRequest);
        authService.checkMasterKomitmenAuthorization(user, idCrosMasterKomitmen);
        return ResponseEntity.ok(apiBodyHelper.create(service.inquiryDetailPopUpHapusBuku(idCrosMasterKomitmen)));
    }

    @GetMapping("pop-up-hapus-tagih/{id_cros_master_komitmen}")
    public ResponseEntity<ApiBody<PopUpHapusTagihDto>> detailPopUpHapusTagih(
            HttpServletRequest httpServletRequest,
            @PathVariable(value = "id_cros_master_komitmen") UUID idCrosMasterKomitmen
    ) {
        val user = authService.getUserDetail(httpServletRequest);
        authService.checkMasterKomitmenAuthorization(user, idCrosMasterKomitmen);
        return ResponseEntity.ok(apiBodyHelper.create(service.inquiryDetailPopUpHapusTagih(idCrosMasterKomitmen)));
    }

    @GetMapping("pop-up-detail-jaminan/{id_cros_master_komitmen}")
    public ResponseEntity<ApiBody<PopUpDetailJaminanDto>> detailPopUpDetailJaminan(
            HttpServletRequest httpServletRequest,
            @PathVariable(value = "id_cros_master_komitmen") UUID idCrosMasterKomitmen
    ) {
        val user = authService.getUserDetail(httpServletRequest);
        authService.checkMasterKomitmenAuthorization(user, idCrosMasterKomitmen);
        return ResponseEntity.ok(apiBodyHelper.create(service.inquiryDetailPopUpDetailJaminan(idCrosMasterKomitmen)));
    }

    @GetMapping("posisi-fasilitas-npl/{id_cros_master_debitur}")
    public ResponseEntity<ApiBody<PosisiFasilitasNplResultDto>> detailPosisiFasilitasNpl(
            HttpServletRequest httpServletRequest,
            @PathVariable(value = "id_cros_master_debitur") UUID idCrosMasterDebitur
    ) {
        val user = authService.getUserDetail(httpServletRequest);
        authService.checkMasterDebiturAuthorization(user, idCrosMasterDebitur);
        return ResponseEntity.ok(apiBodyHelper.create(service.inquiryDetailPosisiFasilitasNpl(idCrosMasterDebitur)));
    }

    @GetMapping("posisi-fasilitas-col-3/{id_cros_master_debitur}")
    public ResponseEntity<ApiBody<PosisiFasilitasCol3ResultDto>> detailPosisiFasilitasCol3(
            HttpServletRequest httpServletRequest,
            @PathVariable(value = "id_cros_master_debitur") UUID idCrosMasterDebitur
    ) {
        val user = authService.getUserDetail(httpServletRequest);
        authService.checkMasterDebiturAuthorization(user, idCrosMasterDebitur);
        return ResponseEntity.ok(apiBodyHelper.create(service.inquiryDetailPosisiFasilitasCol3(idCrosMasterDebitur)));
    }

    @GetMapping("posisi-fasilitas-col-4/{id_cros_master_debitur}")
    public ResponseEntity<ApiBody<PosisiFasilitasCol4ResultDto>> detailPosisiFasilitasCol4(
            HttpServletRequest httpServletRequest,
            @PathVariable(value = "id_cros_master_debitur") UUID idCrosMasterDebitur
    ) {
        val user = authService.getUserDetail(httpServletRequest);
        authService.checkMasterDebiturAuthorization(user, idCrosMasterDebitur);
        return ResponseEntity.ok(apiBodyHelper.create(service.inquiryDetailPosisiFasilitasCol4(idCrosMasterDebitur)));
    }

    @GetMapping("posisi-fasilitas-col-5/{id_cros_master_debitur}")
    public ResponseEntity<ApiBody<PosisiFasilitasCol5ResultDto>> detailPosisiFasilitasCol5(
            HttpServletRequest httpServletRequest,
            @PathVariable(value = "id_cros_master_debitur") UUID idCrosMasterDebitur
    ) {
        val user = authService.getUserDetail(httpServletRequest);
        authService.checkMasterDebiturAuthorization(user, idCrosMasterDebitur);
        return ResponseEntity.ok(apiBodyHelper.create(service.inquiryDetailPosisiFasilitasCol5(idCrosMasterDebitur)));
    }

    @GetMapping("graph-total-eksposur/{id_cros_master_debitur}")
    public ResponseEntity<ApiBody<GraphTotalEksposurResultDto>> detailGraphTotalEksposur(
            HttpServletRequest httpServletRequest,
            @PathVariable(value = "id_cros_master_debitur") UUID idCrosMasterDebitur
    ) {
        val user = authService.getUserDetail(httpServletRequest);
        authService.checkMasterDebiturAuthorization(user, idCrosMasterDebitur);
        return ResponseEntity.ok(apiBodyHelper.create(service.inquiryDetailGraphTotalEksposur(idCrosMasterDebitur)));
    }

    @GetMapping("graph-perkembangan-fasilitas/{id_cros_master_debitur}")
    public ResponseEntity<ApiBody<GraphPerkembanganFasilitasResultDto>> detailGraphPerkembanganFasilitas(
            HttpServletRequest httpServletRequest,
            @PathVariable(value = "id_cros_master_debitur") UUID idCrosMasterDebitur,
            @RequestParam(value = "bulan_awal") int bulanAwal,
            @RequestParam(value = "tahun_awal") int tahunAwal,
            @RequestParam(value = "bulan_akhir") int bulanAkhir,
            @RequestParam(value = "tahun_akhir") int tahunAkhir
    ) {
        val user = authService.getUserDetail(httpServletRequest);
        authService.checkMasterDebiturAuthorization(user, idCrosMasterDebitur);
        return ResponseEntity.ok(apiBodyHelper.create(service.inquiryDetailGraphPerkembanganFasilitas(idCrosMasterDebitur, bulanAwal, tahunAwal, bulanAkhir, tahunAkhir)));
    }

    @GetMapping("jaminan-aset-debitur-solid/{id_cros_master_debitur}/{solid_non_solid}")
    public ResponseEntity<ApiBody<JaminanAsetDebiturResultDto>> detailJaminanAsetDebiturSolid(
            HttpServletRequest httpServletRequest,
            @PathVariable(value = "id_cros_master_debitur") UUID idCrosMasterDebitur,
            @PathVariable(value = "solid_non_solid") String solidNonSolid
    ) {
        val user = authService.getUserDetail(httpServletRequest);
        authService.checkMasterDebiturAuthorization(user, idCrosMasterDebitur);
        return ResponseEntity.ok(apiBodyHelper.create(service.inquiryDetailJaminanAsetDebiturSolid(idCrosMasterDebitur, solidNonSolid)));
    }

    @GetMapping("pop-up-fasilitas-collateral/{id_cros_master_debitur}/{id_cros_jaminan_debitur}")
    public ResponseEntity<ApiBody<List<PopUpFasilitasCollateralDto>>> detailPopUpFasilitasCollateral(
            HttpServletRequest httpServletRequest,
            @PathVariable(value = "id_cros_master_debitur") UUID idCrosMasterDebitur,
            @PathVariable(value = "id_cros_jaminan_debitur") UUID idCrosJaminanDebitur
    ) {
        val user = authService.getUserDetail(httpServletRequest);
        authService.checkMasterDebiturAuthorization(user, idCrosMasterDebitur);
        return ResponseEntity.ok(apiBodyHelper.create(service.inquiryDetailPopUpFasilitasCollateral(idCrosMasterDebitur, idCrosJaminanDebitur)));
    }
}
