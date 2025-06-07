package co.id.bca.cros.module.search.service;

import co.id.bca.cros.module.auth.uidm.dto.login.LoggedUserDetailDto;
import co.id.bca.cros.module.search.dto.defaultfilter.DefaultFilterDto;
import co.id.bca.cros.module.search.dto.defaultfilter.GetKanwilKancabDto;
import co.id.bca.cros.module.search.dto.searchdatadebitur.SearchDataDebiturFilterDto;
import co.id.bca.cros.module.search.dto.searchdatadebitur.SearchDataDebiturResultDto;
import co.id.bca.cros.module.search.dto.searchdatadebitur.SearchDataDebiturOutputSchema;
import co.id.bca.cros.module.search.dto.kantordropdownlist.KantorCabangDropdownMapDto;
import co.id.bca.cros.module.search.dto.kantordropdownlist.KantorWilayahDropdownMapDto;
import co.id.bca.cros.shared.constant.UserTypeConstant;
import co.id.bca.cros.shared.data.repository.CrosMasterDebiturRepository;
import co.id.bca.cros.shared.data.repository.MappingKantorCabangRepository;
import co.id.bca.cros.shared.data.repository.MappingKantorWilayahRepository;
import co.id.bca.cros.shared.dto.PaginationSchema;
import co.id.bca.cros.shared.enumeration.CreditDebiturCategory;
import co.id.bca.cros.shared.enumeration.DebiturStatus;
import co.id.bca.cros.shared.enumeration.Errors;
import co.id.bca.cros.shared.exception.CustomErrorException;
import co.id.bca.cros.shared.helper.db.DbHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchService {
    private final DbHelper dbHelper;
    private final CrosMasterDebiturRepository crosMasterDebiturRepository;
    private final MappingKantorCabangRepository mappingKantorCabangRepository;
    private final MappingKantorWilayahRepository mappingKantorWilayahRepository;

    public DefaultFilterDto searchDefaultFilter(LoggedUserDetailDto user) {
        val dto = new DefaultFilterDto();
        dto.setTotalOutstandingMaxBalance(crosMasterDebiturRepository.getMaxBalanceIdr());

        // if user bukan GCR / DBKK, maka ambil data kanwil & kcu dari user utk default
        // value, else kasih null (value null = all)
        if (!Set.of(UserTypeConstant.GCR, UserTypeConstant.DBKK).contains(user.getUserType())) {
            GetKanwilKancabDto kanwilKancabDto = mappingKantorCabangRepository.getKanwilKancab(user.getOfficeCode());
            dto.setDefaultKantorWilayahCode(kanwilKancabDto.getKanwilCode());
            dto.setDefaultKantorWilayahName(kanwilKancabDto.getKanwilName());
            dto.setDefaultKantorCabangCode(kanwilKancabDto.getKancabCode());
            dto.setDefaultKantorCabangName(kanwilKancabDto.getKancabName());
        }
        return dto;
    }

    public KantorWilayahDropdownMapDto getKantorWilayahDropdownMap(LoggedUserDetailDto user) {
        val kanwilDropdownMap = new TreeMap<String, String>();

        if (Set.of(UserTypeConstant.GCR, UserTypeConstant.DBKK).contains(user.getUserType())) {
            // get all kanwil
            mappingKantorWilayahRepository.getKantorWilayahList()
                    .forEach(kantorWilayah -> kanwilDropdownMap.put(kantorWilayah.getKantorCode(),
                            kantorWilayah.getKantorName()));
        } else {
            // get 1 kanwil
            val kanwilKancabDto = mappingKantorCabangRepository.getKanwilKancab(user.getOfficeCode());
            kanwilDropdownMap.put(kanwilKancabDto.getKanwilCode(), kanwilKancabDto.getKanwilName());
        }

        return KantorWilayahDropdownMapDto.builder()
                .kantorWilayahDropdownMap(kanwilDropdownMap)
                .build();
    }

    public KantorCabangDropdownMapDto getKantorCabangDropdownMap(LoggedUserDetailDto user, String kantorWilayahCode) {
        val kancabDropdownMap = new TreeMap<String, String>();
        GetKanwilKancabDto kanwilKancabDto = new GetKanwilKancabDto();

        if (!Set.of(UserTypeConstant.GCR, UserTypeConstant.DBKK).contains(user.getUserType())) {
            kanwilKancabDto = mappingKantorCabangRepository.getKanwilKancab(user.getOfficeCode());
            if (!Objects.equals(kanwilKancabDto.getKanwilCode(), kantorWilayahCode)) {
                throw new CustomErrorException(Errors.UNAUTHORIZED,
                        "User does not have permission to access this resource.");
            }
        }

        if (!UserTypeConstant.CABANG.equals(user.getUserType())) {
            // get all kancab by kanwil code
            mappingKantorCabangRepository.getKantorCabangList(kantorWilayahCode)
                    .forEach(kantorCabang -> kancabDropdownMap.put(kantorCabang.getKantorCode(),
                            kantorCabang.getKantorName()));
        } else {
            // get 1 kancab
            kancabDropdownMap.put(kanwilKancabDto.getKancabCode(), kanwilKancabDto.getKancabName());
        }
        return KantorCabangDropdownMapDto.builder()
                .kantorCabangDropdownMap(kancabDropdownMap)
                .build();
    }

    public PaginationSchema<SearchDataDebiturOutputSchema> searchDataDebitur(LoggedUserDetailDto user,
            SearchDataDebiturFilterDto filter) {
        // force set filter kategori kredit by user role
        if (Set.of(UserTypeConstant.DBKK, UserTypeConstant.KKK, UserTypeConstant.KFKK).contains(user.getUserType())) {
            if (Objects.isNull(filter.getKategoriKredit())) {
                filter.setKategoriKredit(CreditDebiturCategory.CONSUMER.toString());
            }
            if (!Objects.equals(
                    CreditDebiturCategory.valueOf(filter.getKategoriKredit()),
                    CreditDebiturCategory.CONSUMER)) {
                return PaginationSchema.of(Collections.emptyList(), filter.getPageNo(), filter.getRowsPerPage(), 0);
            }
        }

        // force set filter kanwil & kancab by user role
        if (!Set.of(UserTypeConstant.GCR, UserTypeConstant.DBKK).contains(user.getUserType())) {
            val kanwilKancabDto = mappingKantorCabangRepository.getKanwilKancab(user.getOfficeCode());
            if (Objects.isNull(filter.getKantorWilayah())) {
                filter.setKantorWilayah(kanwilKancabDto.getKanwilCode());
            }
            if (!kanwilKancabDto.getKanwilCode().equals(filter.getKantorWilayah())) {
                return PaginationSchema.of(Collections.emptyList(), filter.getPageNo(), filter.getRowsPerPage(), 0);
            }
            if (UserTypeConstant.CABANG.equals(user.getUserType())) {
                if (Objects.isNull(filter.getKantorCabang())) {
                    filter.setKantorCabang(kanwilKancabDto.getKancabCode());
                }
                if (!kanwilKancabDto.getKancabCode().equals(filter.getKantorCabang())) {
                    return PaginationSchema.of(Collections.emptyList(), filter.getPageNo(), filter.getRowsPerPage(), 0);
                }
            }
        }

        // language=sql
        String query = """
                SELECT
                    distinct(cmd.id_cros_master_debitur),
                    cmd.cust_ln_curr_key,
                    cmd.cin as no_cis,
                    cmd.nama_debitur,
                    mkw.region_lng_nm as wilayah,
                    mkc.branch_name as cabang,
                    cmd.kategori_kredit,
                    cmd.collect_key as kolektibilitas,
                    cmd.balance_idr as total_outstanding,
                    cmd.hari_tunggakan,
                    cmd.flag_hb as status_debitur,
                    null as penanganan,
                    count(*) over() as total
                FROM cros_master_debitur cmd
                    left join cros_master_komitmen cmk on cmd.cust_ln_curr_key = cmk.cust_ln_curr_key
                    left join cros_jaminan_debitur cjd on cmd.cust_ln_curr_key = cjd.cust_ln_curr_key
                    left join mapping_kantor_wilayah mkw on cmd.kode_kanwil = mkw.region_cd -- nama tabel akan diganti nanti sesuai req
                    left join mapping_kantor_cabang mkc on cmd.kode_kcu = mkc.branch_cd -- nama tabel akan diganti nanti sesuai req
                WHERE (:kantorWilayah is null or cmd.kode_kanwil = :kantorWilayah) -- tbc filter pakai kode kanwil atau langsung nama kanwil?
                    and (:kantorCabang is null or cmd.kode_kcu = :kantorCabang) -- tbc filter pakai kode kcu atau langsung nama kcu?
                    and (:kolektibilitas is null or
                            (
                                case
                                    when :kolektibilitas = 'C' then cmd.collect_key = 5 and cmd.flag_hb in (1, -1)
                                    else cmd.collect_key = :kolektibilitas
                                end
                            )
                        )
                    and (:kategoriDebitur is null or cmd.kategori_debitur = :kategoriDebitur)
                    and (:kategoriKredit is null or cmd.kategori_kredit = :kategoriKredit)
                    and (:statusDebitur is null or
                            (
                                case
                                    when :statusDebitur = -99 then cmd.is_active = false
                                    else cmd.flag_hb = :statusDebitur
                                end
                            )
                        )
                    and (:totalOutstandingMin is null or cmd.balance_idr >= :totalOutstandingMin)
                    and (:totalOutstandingMax is null or cmd.balance_idr <= :totalOutstandingMax)
                    and (:searchKey is null or :searchValue is null or
                            (
                                case
                                    when lower(:searchKey) = 'no_cis' then lower(cmd.cin)
                                    when lower(:searchKey) = 'nama_debitur' then lower(cmd.nama_debitur)
                                    when lower(:searchKey) = 'no_rekening_ils' then lower(cmk.no_rekening_pinjaman)
                                    when lower(:searchKey) = 'alamat_jaminan' then lower(cjd.deskripsi_jaminan)
                                end
                            ) like lower(:searchValue)
                        )
                ORDER BY nama_debitur, wilayah, cabang, kategori_kredit, id_cros_master_debitur
                LIMIT :rowsPerPage OFFSET :rowsPerPage * (:pageNo - 1)
                """;

        val queryResult = dbHelper.executeQueryEm(query, filter, SearchDataDebiturResultDto.class);
        val responseList = queryResult.stream().map(rs -> SearchDataDebiturOutputSchema.builder()
                .idCrosMasterDebitur(rs.getIdCrosMasterDebitur())
                .custLnCurrkey(rs.getCustLnCurrkey())
                .noCis(rs.getNoCis())
                .namaDebitur(rs.getNamaDebitur())
                .wilayah(rs.getWilayah())
                .cabang(rs.getCabang())
                .kategoriKredit(rs.getKategoriKredit())
                .kolektibilitas(rs.getKolektibilitas())
                .totalOutstanding(rs.getTotalOutstanding())
                .hariTunggakan(rs.getHariTunggakan())
                .statusDebitur(DebiturStatus.valueOfNumber(rs.getStatusDebitur()).toFlagName())
                .penanganan(rs.getPenanganan())
                .build()).toList();
        val total = queryResult.isEmpty() ? 0L : queryResult.get(0).getTotal();

        return PaginationSchema.of(responseList, filter.getPageNo(), filter.getRowsPerPage(), total);
    }
}
