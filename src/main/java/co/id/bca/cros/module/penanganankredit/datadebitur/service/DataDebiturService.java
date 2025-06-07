package co.id.bca.cros.module.penanganankredit.datadebitur.service;

import co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabdatadebitur.DataDebiturDto;
import co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabfasilitasdebitur.*;
import co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabgraph.GraphPerkembanganFasilitasResultDto;
import co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabgraph.GraphTotalEksposurResultDto;
import co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabjaminanasetdebitur.JaminanAsetDebiturDto;
import co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabjaminanasetdebitur.JaminanAsetDebiturResultDto;
import co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabjaminanasetdebitur.PopUpFasilitasCollateralDto;
import co.id.bca.cros.module.penanganankredit.datadebitur.dto.tabposisifasilitassaatnpl.*;
import co.id.bca.cros.shared.data.repository.*;
import co.id.bca.cros.shared.enumeration.Errors;
import co.id.bca.cros.shared.exception.CustomErrorException;
import co.id.bca.cros.shared.util.CommonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DataDebiturService {
    private final CrosMasterDebiturRepository crosMasterDebiturRepository;
    private final CrosMasterKomitmenRepository crosMasterKomitmenRepository;
    private final CrosMasterPinjamanRepository crosMasterPinjamanRepository;
    private final CrosJaminanDebiturRepository crosJaminanDebiturRepository;
    private final CrosNplRepository crosNplRepository;
    private final CommonUtil commonUtil;

    BigDecimal outstandingTotal = new BigDecimal(0);
    BigDecimal bungaTotal = new BigDecimal(0);
    BigDecimal dendaTotal = new BigDecimal(0);
    BigDecimal outstandingEquivalentIdr = new BigDecimal(0);
    BigDecimal bungaEquivalentIdr = new BigDecimal(0);
    BigDecimal dendaEquivalentIdr = new BigDecimal(0);

    public DataDebiturDto inquiryDetailDataDebitur(UUID idCrosMasterDebitur) throws JsonProcessingException {
        val dataDebiturProjection = crosMasterDebiturRepository.inquiryDetailDataDebitur(idCrosMasterDebitur);

        return new DataDebiturDto(dataDebiturProjection);
    }

    public FasilitasDebiturResultDto inquiryDetailFasilitasDebitur(UUID id) {
        val fasilitasDebitur = crosMasterKomitmenRepository.inquiryDetailFasilitasDebitur(id);

        FasilitasDebiturResultDto resultDto = new FasilitasDebiturResultDto();
        resultDto.setFasilitasDebitur(fasilitasDebitur);
        setTotalAndEquivalentIdrFasilitasDebitur(resultDto);

        return resultDto;
    }

    private void setTotalAndEquivalentIdrFasilitasDebitur(FasilitasDebiturResultDto resultDto) {
        resetTotalAndEquivalentIdr();

        for (FasilitasDebiturDto data : resultDto.getFasilitasDebitur()) {
            outstandingTotal = outstandingTotal.add(data.getBalanceOcur());
            bungaTotal = bungaTotal.add(commonUtil.bigDecimalNullChecker(data.getBungaOcur()));
            dendaTotal = dendaTotal.add(commonUtil.bigDecimalNullChecker(data.getDendaOcur()));
            outstandingEquivalentIdr = outstandingEquivalentIdr.add(commonUtil.bigDecimalNullChecker(data.getBalanceIdr()));
            bungaEquivalentIdr = bungaEquivalentIdr.add(commonUtil.bigDecimalNullChecker(data.getBungaIdr()));
            dendaEquivalentIdr = dendaEquivalentIdr.add(commonUtil.bigDecimalNullChecker(data.getDendaIdr()));
        }

        resultDto.setOutstandingTotal(outstandingTotal);
        resultDto.setBungaTotal(bungaTotal);
        resultDto.setDendaTotal(dendaTotal);
        resultDto.setOutstandingEquivalentIdr(outstandingEquivalentIdr);
        resultDto.setBungaEquivalentIdr(bungaEquivalentIdr);
        resultDto.setDendaEquivalentIdr(dendaEquivalentIdr);
        resultDto.setRecordTotal(resultDto.getFasilitasDebitur().size());
    }

    private void resetTotalAndEquivalentIdr() {
        outstandingTotal = new BigDecimal(0);
        bungaTotal = new BigDecimal(0);
        dendaTotal = new BigDecimal(0);
        outstandingEquivalentIdr = new BigDecimal(0);
        bungaEquivalentIdr = new BigDecimal(0);
        dendaEquivalentIdr = new BigDecimal(0);
    }

    public PopUpPinjamanDto inquiryDetailPopUpPinjaman(UUID idCrosMasterKomitmen) {
        val popUpPinjaman = crosMasterPinjamanRepository.inquiryDetailPopUpPinjaman(idCrosMasterKomitmen);

        if (popUpPinjaman == null)
            throw new CustomErrorException(Errors.NO_DATA_FOUND, Errors.NO_DATA_FOUND.toString());

        return new PopUpPinjamanDto(popUpPinjaman);
    }

    public PopUpHapusBukuDto inquiryDetailPopUpHapusBuku(UUID idCrosMasterKomitmen) {
        val popUpHapusBuku = crosMasterKomitmenRepository.inquiryDetailPopUpHapusBuku(idCrosMasterKomitmen);

        if (popUpHapusBuku == null)
            throw new CustomErrorException(Errors.NO_DATA_FOUND, Errors.NO_DATA_FOUND.toString());

        return new PopUpHapusBukuDto(popUpHapusBuku);
    }

    public PopUpHapusTagihDto inquiryDetailPopUpHapusTagih(UUID idCrosMasterKomitmen) {
        val popUpHapusTagih = crosMasterKomitmenRepository.inquiryDetailPopUpHapusTagih(idCrosMasterKomitmen);

        if (popUpHapusTagih == null)
            throw new CustomErrorException(Errors.NO_DATA_FOUND, Errors.NO_DATA_FOUND.toString());

        return new PopUpHapusTagihDto(popUpHapusTagih);
    }

    public PopUpDetailJaminanDto inquiryDetailPopUpDetailJaminan(UUID idCrosMasterKomitmen) {
        return crosJaminanDebiturRepository.inquiryDetailPopUpDetailJaminan(idCrosMasterKomitmen);
    }

    public PosisiFasilitasNplResultDto inquiryDetailPosisiFasilitasNpl(UUID idCrosMasterDebitur) {
        val posisiFasilitasNplProjection = crosNplRepository.inquiryDetailPosisiFasilitasNpl(idCrosMasterDebitur);

        if (posisiFasilitasNplProjection == null)
            throw new CustomErrorException(Errors.EMPTY_DATA, Errors.EMPTY_DATA.toString());

        val posisiFasilitasNpl = posisiFasilitasNplProjection.stream().map(PosisiFasilitasNplDto::fromProjection).toList();

        PosisiFasilitasNplResultDto resultDto = new PosisiFasilitasNplResultDto();
        resultDto.setPosisiFasilitasNpl(posisiFasilitasNpl);
        setTotalAndEquivalentIdrPosisiFasilitasNpl(resultDto);

        return resultDto;
    }

    private void setTotalAndEquivalentIdrPosisiFasilitasNpl(PosisiFasilitasNplResultDto resultDto) {
        resetTotalAndEquivalentIdr();

        for (PosisiFasilitasNplDto data : resultDto.getPosisiFasilitasNpl()) {
            outstandingTotal = outstandingTotal.add(commonUtil.bigDecimalNullChecker(data.getNplBalanceOcur()));
            bungaTotal = bungaTotal.add(commonUtil.bigDecimalNullChecker(data.getNplBungaOcur()));
            dendaTotal = dendaTotal.add(commonUtil.bigDecimalNullChecker(data.getNplDendaOcur()));
            outstandingEquivalentIdr = outstandingEquivalentIdr.add(commonUtil.bigDecimalNullChecker(data.getNplBalanceIdr()));
            bungaEquivalentIdr = bungaEquivalentIdr.add(commonUtil.bigDecimalNullChecker(data.getNplBungaIdr()));
            dendaEquivalentIdr = dendaEquivalentIdr.add(commonUtil.bigDecimalNullChecker(data.getNplDendaIdr()));
        }

        resultDto.setOutstandingTotal(outstandingTotal);
        resultDto.setBungaTotal(bungaTotal);
        resultDto.setDendaTotal(dendaTotal);
        resultDto.setOutstandingEquivalentIdr(outstandingEquivalentIdr);
        resultDto.setBungaEquivalentIdr(bungaEquivalentIdr);
        resultDto.setDendaEquivalentIdr(dendaEquivalentIdr);
        resultDto.setRecordTotal(resultDto.getPosisiFasilitasNpl().size());
    }

    public PosisiFasilitasCol3ResultDto inquiryDetailPosisiFasilitasCol3(UUID idCrosMasterDebitur) {
        val posisiFasilitasCol3Projection = crosNplRepository.inquiryDetailPosisiFasilitasCol3(idCrosMasterDebitur);

        if (posisiFasilitasCol3Projection == null)
            throw new CustomErrorException(Errors.EMPTY_DATA, Errors.EMPTY_DATA.toString());

        val posisiFasilitasCol3 = posisiFasilitasCol3Projection.stream().map(PosisiFasilitasCol3Dto::fromProjection).toList();

        PosisiFasilitasCol3ResultDto resultDto = new PosisiFasilitasCol3ResultDto();
        resultDto.setPosisiFasilitasCol3(posisiFasilitasCol3);
        setTotalAndEquivalentIdrPosisiFasilitasCol3(resultDto);

        return resultDto;
    }

    private void setTotalAndEquivalentIdrPosisiFasilitasCol3(PosisiFasilitasCol3ResultDto resultDto) {
        resetTotalAndEquivalentIdr();

        for (PosisiFasilitasCol3Dto data : resultDto.getPosisiFasilitasCol3()) {
            outstandingTotal = outstandingTotal.add(commonUtil.bigDecimalNullChecker(data.getCol3BalanceOcur()));
            bungaTotal = bungaTotal.add(commonUtil.bigDecimalNullChecker(data.getCol3BungaOcur()));
            dendaTotal = dendaTotal.add(commonUtil.bigDecimalNullChecker(data.getCol3DendaOcur()));
            outstandingEquivalentIdr = outstandingEquivalentIdr.add(commonUtil.bigDecimalNullChecker(data.getCol3BalanceIdr()));
            bungaEquivalentIdr = bungaEquivalentIdr.add(commonUtil.bigDecimalNullChecker(data.getCol3BungaIdr()));
            dendaEquivalentIdr = dendaEquivalentIdr.add(commonUtil.bigDecimalNullChecker(data.getCol3DendaIdr()));
        }

        resultDto.setOutstandingTotal(outstandingTotal);
        resultDto.setBungaTotal(bungaTotal);
        resultDto.setDendaTotal(dendaTotal);
        resultDto.setOutstandingEquivalentIdr(outstandingEquivalentIdr);
        resultDto.setBungaEquivalentIdr(bungaEquivalentIdr);
        resultDto.setDendaEquivalentIdr(dendaEquivalentIdr);
    }

    public PosisiFasilitasCol4ResultDto inquiryDetailPosisiFasilitasCol4(UUID idCrosMasterDebitur) {
        val posisiFasilitasCol4Projection = crosNplRepository.inquiryDetailPosisiFasilitasCol4(idCrosMasterDebitur);

        if (posisiFasilitasCol4Projection == null)
            throw new CustomErrorException(Errors.EMPTY_DATA, Errors.EMPTY_DATA.toString());

        val posisiFasilitasCol4 = posisiFasilitasCol4Projection.stream().map(PosisiFasilitasCol4Dto::fromProjection).toList();

        PosisiFasilitasCol4ResultDto resultDto = new PosisiFasilitasCol4ResultDto();
        resultDto.setPosisiFasilitasCol4(posisiFasilitasCol4);
        setTotalAndEquivalentIdrPosisiFasilitasCol4(resultDto);

        return resultDto;
    }

    private void setTotalAndEquivalentIdrPosisiFasilitasCol4(PosisiFasilitasCol4ResultDto resultDto) {
        resetTotalAndEquivalentIdr();

        for (PosisiFasilitasCol4Dto data : resultDto.getPosisiFasilitasCol4()) {
            outstandingTotal = outstandingTotal.add(commonUtil.bigDecimalNullChecker(data.getCol4BalanceOcur()));
            bungaTotal = bungaTotal.add(commonUtil.bigDecimalNullChecker(data.getCol4BungaOcur()));
            dendaTotal = dendaTotal.add(commonUtil.bigDecimalNullChecker(data.getCol4DendaOcur()));
            outstandingEquivalentIdr = outstandingEquivalentIdr.add(commonUtil.bigDecimalNullChecker(data.getCol4BalanceIdr()));
            bungaEquivalentIdr = bungaEquivalentIdr.add(commonUtil.bigDecimalNullChecker(data.getCol4BungaIdr()));
            dendaEquivalentIdr = dendaEquivalentIdr.add(commonUtil.bigDecimalNullChecker(data.getCol4DendaIdr()));
        }

        resultDto.setOutstandingTotal(outstandingTotal);
        resultDto.setBungaTotal(bungaTotal);
        resultDto.setDendaTotal(dendaTotal);
        resultDto.setOutstandingEquivalentIdr(outstandingEquivalentIdr);
        resultDto.setBungaEquivalentIdr(bungaEquivalentIdr);
        resultDto.setDendaEquivalentIdr(dendaEquivalentIdr);
    }

    public PosisiFasilitasCol5ResultDto inquiryDetailPosisiFasilitasCol5(UUID idCrosMasterDebitur) {
        val posisiFasilitasCol5Projection = crosNplRepository.inquiryDetailPosisiFasilitasCol5(idCrosMasterDebitur);

        if (posisiFasilitasCol5Projection == null)
            throw new CustomErrorException(Errors.EMPTY_DATA, Errors.EMPTY_DATA.toString());

        val posisiFasilitasCol5 = posisiFasilitasCol5Projection.stream().map(PosisiFasilitasCol5Dto::fromProjection).toList();

        PosisiFasilitasCol5ResultDto resultDto = new PosisiFasilitasCol5ResultDto();
        resultDto.setPosisiFasilitasCol5(posisiFasilitasCol5);
        setTotalAndEquivalentIdrPosisiFasilitasCol5(resultDto);

        return resultDto;
    }

    private void setTotalAndEquivalentIdrPosisiFasilitasCol5(PosisiFasilitasCol5ResultDto resultDto) {
        resetTotalAndEquivalentIdr();

        for (PosisiFasilitasCol5Dto data : resultDto.getPosisiFasilitasCol5()) {
            outstandingTotal = outstandingTotal.add(commonUtil.bigDecimalNullChecker(data.getCol5BalanceOcur()));
            bungaTotal = bungaTotal.add(commonUtil.bigDecimalNullChecker(data.getCol5BungaOcur()));
            dendaTotal = dendaTotal.add(commonUtil.bigDecimalNullChecker(data.getCol5DendaOcur()));
            outstandingEquivalentIdr = outstandingEquivalentIdr.add(commonUtil.bigDecimalNullChecker(data.getCol5BalanceIdr()));
            bungaEquivalentIdr = bungaEquivalentIdr.add(commonUtil.bigDecimalNullChecker(data.getCol5BungaIdr()));
            dendaEquivalentIdr = dendaEquivalentIdr.add(commonUtil.bigDecimalNullChecker(data.getCol5DendaIdr()));
        }

        resultDto.setOutstandingTotal(outstandingTotal);
        resultDto.setBungaTotal(bungaTotal);
        resultDto.setDendaTotal(dendaTotal);
        resultDto.setOutstandingEquivalentIdr(outstandingEquivalentIdr);
        resultDto.setBungaEquivalentIdr(bungaEquivalentIdr);
        resultDto.setDendaEquivalentIdr(dendaEquivalentIdr);
    }

    public GraphTotalEksposurResultDto inquiryDetailGraphTotalEksposur(UUID idCrosMasterDebitur) {
        val graphTotalEksposur = crosMasterKomitmenRepository.inquiryDetailGraphTotalEksposur(idCrosMasterDebitur);

        GraphTotalEksposurResultDto resultDto = new GraphTotalEksposurResultDto();
        resultDto.setGraphTotalEksposur(graphTotalEksposur);

        return resultDto;
    }

    public GraphPerkembanganFasilitasResultDto inquiryDetailGraphPerkembanganFasilitas(UUID idCrosMasterDebitur, int bulanAwal, int tahunAwal, int bulanAkhir, int tahunAkhir) {
        val graphPerkembanganFasilitas = crosMasterKomitmenRepository.inquiryDetailGraphPerkembanganFasilitas(idCrosMasterDebitur, bulanAwal, tahunAwal, bulanAkhir, tahunAkhir);

        GraphPerkembanganFasilitasResultDto resultDto = new GraphPerkembanganFasilitasResultDto();
        resultDto.setGraphPerkembanganFasilitas(graphPerkembanganFasilitas);

        return resultDto;
    }

    public JaminanAsetDebiturResultDto inquiryDetailJaminanAsetDebiturSolid(UUID idCrosMasterDebitur, String solidNonSolid) {
        val jaminanAsetDebitur = crosJaminanDebiturRepository.inquiryDetailJaminanAsetDebiturSolid(idCrosMasterDebitur, solidNonSolid);

        JaminanAsetDebiturResultDto resultDto = new JaminanAsetDebiturResultDto();
        resultDto.setJaminanAsetDebitur(jaminanAsetDebitur);
        setTotalAndEquivalentIdrJaminanAsetDebitur(resultDto);

        return resultDto;
    }

    private void setTotalAndEquivalentIdrJaminanAsetDebitur(JaminanAsetDebiturResultDto resultDto) {
        BigDecimal nilaiPasarTotal = new BigDecimal(0);
        BigDecimal nilaiTaksasiTotal = new BigDecimal(0);
        BigDecimal nilaiHakTanggunganTotal = new BigDecimal(0);
        BigDecimal nilaiLikuidasiTotal = new BigDecimal(0);
        BigDecimal nilaiPasarEquivalentIdr = new BigDecimal(0);
        BigDecimal nilaiTaksasiEquivalentIdr = new BigDecimal(0);
        BigDecimal nilaiHakTanggunganEquivalentIdr = new BigDecimal(0);
        BigDecimal nilaiLikuidasiEquivalentIdr = new BigDecimal(0);

        for (JaminanAsetDebiturDto data : resultDto.getJaminanAsetDebitur()) {
            nilaiPasarTotal = nilaiPasarTotal.add(data.getNilaiPasarOcur());
            nilaiTaksasiTotal = nilaiTaksasiTotal.add(data.getNilaiTaksasiOcur());
            nilaiHakTanggunganTotal = nilaiHakTanggunganTotal.add(data.getNilaiHakTanggunganOcur());
            nilaiLikuidasiTotal = nilaiLikuidasiTotal.add(data.getNilaiLikuidasiOcur());
            nilaiPasarEquivalentIdr = nilaiPasarEquivalentIdr.add(data.getNilaiPasarIdr());
            nilaiTaksasiEquivalentIdr = nilaiTaksasiEquivalentIdr.add(data.getNilaiTaksasiIdr());
            nilaiHakTanggunganEquivalentIdr = nilaiHakTanggunganEquivalentIdr.add(data.getNilaiHakTanggunganIdr());
            nilaiLikuidasiEquivalentIdr = nilaiLikuidasiEquivalentIdr.add(data.getNilaiLikuidasiIdr());
        }

        resultDto.setNilaiPasarTotal(nilaiPasarTotal);
        resultDto.setNilaiTaksasiTotal(nilaiTaksasiTotal);
        resultDto.setNilaiHakTanggunganTotal(nilaiHakTanggunganTotal);
        resultDto.setNilaiLikuidasiTotal(nilaiLikuidasiTotal);
        resultDto.setNilaiPasarEquivalentIdr(nilaiPasarEquivalentIdr);
        resultDto.setNilaiTaksasiEquivalentIdr(nilaiTaksasiEquivalentIdr);
        resultDto.setNilaiHakTanggunganEquivalentIdr(nilaiHakTanggunganEquivalentIdr);
        resultDto.setNilaiLikuidasiEquivalentIdr(nilaiLikuidasiEquivalentIdr);
    }

    public List<PopUpFasilitasCollateralDto> inquiryDetailPopUpFasilitasCollateral(UUID idCrosMasterDebitur, UUID idCrosJaminanDebitur) {
        return crosMasterKomitmenRepository.inquiryDetailPopUpFasilitasCollateral(idCrosMasterDebitur, idCrosJaminanDebitur);
    }
}
