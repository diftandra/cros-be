/*TRX - Create table (still not fixed)*/
CREATE SEQUENCE TRX_DEBTOR_EXTENDED_ID_SEQ as integer;
CREATE TABLE TRX_DEBTOR_EXTENDED
(
    ID_TRX_DEBITUR_EXTENDED      int default nextval('trx_debtor_extended_id_seq'),
    ID_CROS_MASTER_DEBITUR       UUID,
    DATE_CREATED                 timestamp with time zone,
    USER_CREATED                 varchar(50)/*todo: make sure data type*/,
    DATE_MODIFIED                timestamp with time zone,
    USER_MODIFIED                varchar(50),
    CUST_LN_CURR_KEY             int,
    IS_DEBITUR_KOMKOR            int,
    KETERANGAN_DEBITUR           varchar(4000),
    PENYEBAB_BERMASALAH          varchar(4000),
    MAPPINGPENYEBABBERMASALAHID  int,
    IS_LEGAL_REVIEW              boolean,
    MAPPINGCARAPENYELESAIANID    int,
    TGL_PENGALIHAN               timestamp with time zone,
    TARGET_PENYELESAIAN          timestamp with time zone,
    OUTSTANDING_SAAT_PENGALIHAN  numeric(37, 8)/*todo: make sure data type*/,
    PROFIT_DARI_DEBITUR          numeric(37, 8),
    PIC_GCR_UD                   varchar(7)/*todo: make sure data type*/,
    PIC_GCR_NM                   varchar(50)/*todo: make sure data type*/,
    PIC_SBK_UD                   varchar(7)/*todo: make sure data type*/,
    PIC_SBK_NM                   varchar(50)/*todo: make sure data type*/,
    PIC_DEBITUR                  varchar(50)/*todo: make sure data type*/,
    TGL_MPK_TERAKHIR             timestamp with time zone,
    HASIL_NEGO_TERAKHIR          text,
    HASIL_PENGECEKAN_DOKUMEN_KRE varchar(2000),
    PENANGANAN_OLEH              varchar,
    constraint TRX_DEBTOR_EXTENDED_PKEY primary key (ID_TRX_DEBITUR_EXTENDED)
);

CREATE SEQUENCE TRX_AGUNAN_LAIN_ID_SEQ as integer;
CREATE TABLE TRX_AGUNAN_LAIN
(
    ID_TRX_AGUNAN_LAIN     int default nextval('trx_agunan_lain_id_seq'),
    CUST_LN_CURR_KEY       int,
    DIMENSIONJENISAGUNANID int,
    LOKASI_AGUNAN          varchar(2000),
    KETERANGAN             varchar(2000),
    IS_ACTIVE              int/*todo: make sure data type and used or no*/,
    constraint TRX_AGUNAN_LAIN_PKEY primary key (ID_TRX_AGUNAN_LAIN)
);

CREATE SEQUENCE TRX_JAMINAN_DEBITUR_EXTENDED_ID_SEQ as integer;
CREATE TABLE TRX_JAMINAN_DEBITUR_EXTENDED
(
    ID_TRX_JAMINAN_DEBITUR_EXTENDED int default nextval('trx_jaminan_debitur_extended_id_seq'),
    ID_CROS_JAMINAN_DEBITUR         UUID,
    DWHJAMINANDEBITURID             int,
    NILAI_LIKUIDASI                 numeric(37, 8),
    LOKASI_SINGKAT                  varchar(500),
    KETERANGAN_JAMINAN              varchar(50),
    INSPEKSI_APPRAISAL_DATE         timestamp with time zone,
    BAP_DATE                        timestamp with time zone,
    DWHDATADEBITURID                bigint,
    USERID_INPUT                    varchar(50),
    NILAI_PASAR_ACTUAL              numeric(37, 8),
    NILAI_TAKSASI_ACTUAL            numeric(37, 8),
    COLL_CURR_KEY                   int,
    TEMPORARY_NO_SURAT_SPK          varchar(50),
    TEMPORARY_TGL_SPK               timestamp with time zone,
    TEMPORARY_KEY_SPK               int,
    LPPA_DATE                       timestamp with time zone,
    IDBAP                           bigint,
    constraint TRX_JAMINAN_DEBITUR_EXTENDED_PKEY primary key (ID_TRX_JAMINAN_DEBITUR_EXTENDED)
);

CREATE SEQUENCE TRX_PENANGANAN_DEBITUR_ID_SEQ as int;
CREATE TABLE TRX_PENANGANAN_DEBITUR
(
    DATADEBITURID                int,
    ID                           int default nextval('trx_penanganan_debitur_id_seq'),
    REGISTRATION_DATE            timestamp with time zone,
    KONDISI_SAAT_INI             varchar(2000),
    ACTION_PLAN                  varchar(2000),
    DIMENSIONSTATUSPENANGANANDEB int,
    REMINDER_DATE                timestamp with time zone,
    USERID_INPUT                 varchar(50),
    CUST_LN_CURR_KEY             int,
    KANWIL_KEY                   int,
    KCU_KEY                      int,
    DIMENSIONKATEGORIACTPLANID   int,
    DATE_MODIFIED                timestamp with time zone,
    IS_ACTIVE                    boolean,
    IS_REPORT                    boolean,
    IS_DONE                      boolean,
    SEGMENTASI_KREDIT            varchar(15),
    SEGMENTASI_PENANGANAN        varchar(50),
    TRXBCRID                     bigint,
    KODE_PENANGGUNGJAWAB         varchar(200),
    IS_BCR_INTERNAL              boolean,
    constraint TRX_PENANGANAN_DEBITUR_PKEY primary key (ID)
);

CREATE SEQUENCE TRX_DOKUMEN_ID_SEQ as bigint;
CREATE TABLE TRX_DOKUMEN
(
    ID                           bigint default nextval('trx_dokumen_id_seq'),
    CUST_LN_CURR_KEY             int,
    KANWIL_KEY                   int,
    KCU_KEY                      int,
    COLL_CURR_KEY                int,
    TRXPROSESLELANGID            bigint,
    NAMA_DOKUMEN                 varchar(50),
    NOMOR_DOKUMEN                varchar(50),
    KETERANGAN_DOKUMEN           varchar(200),
    PREV_STAT_LELANG             int,
    TGL_INFO_DOKUR               timestamp with time zone,
    TGL_DITERIMA                 timestamp with time zone,
    IS_LELANG                    boolean,
    IS_DOKUR                     boolean,
    IS_ACTIVE                    boolean,
    ALASAN_NONAKTIF              varchar(50),
    USER_NONAKTIF                varchar(50),
    MAPPINGJENISDOKUMENID        bigint,
    KODE_FILE_HCP                bigint,
    IS_FILE_UPLOADED             boolean,
    PENAMAAN_DOKUMEN             varchar(1000),
    TGL_DOKUMEN                  timestamp with time zone,
    IS_DOKUR_LELANG              boolean,
    IS_DOKUR_NONLELANG           boolean,
    FILE_EXT_TYPE                varchar(50),
    FILE_NAME                    varchar(100),
    FILE_TYPE_EXT                varchar(10),
    IS_DEBITUR                   boolean,
    TRXNOMOID                    bigint,
    IS_GENERATED_BY_NOMOD        boolean,
    REGISTRATION_DATE            timestamp with time zone,
    IS_INVOICE_LELANG_SDH_BAYA   boolean,
    TGL_INVOICE_LELANG_DIBAYAR   timestamp with time zone,
    PASCALELANG_TGL_DOK_KIRIM_CA timestamp with time zone,
    PASCALELANG_PIC_CABANG       varchar(100),
    PASCALELANG_TGL_INVOICE_DIBA timestamp with time zone,
    BAP_NILAI_PASAR              numeric(37, 8),
    BAP_NILAI_TAKSASI            numeric(37, 8),
    BAP_NILAI_LIKUIDASI          numeric(37, 8),
    BAP_TGL_INSPEKSI             timestamp with time zone,
    constraint TRX_DOKUMEN_PKEY primary key (ID)
);

CREATE SEQUENCE TRX_FAS_DEB_DI_BANK_LAIN_ID_SEQ as bigint;
CREATE TABLE TRX_FAS_DEB_DI_BANK_LAIN
(
    ID                   bigint default nextval('trx_fas_deb_di_bank_lain_id_seq'),
    REGISTRATION_DATE    timestamp with time zone,
    CUST_LN_CURR_KEY     int,
    KANWIL_KEY           int,
    KCU_KEY              int,
    MAPPINGLISTBANKID    bigint,
    JENIS_FASILITAS      varchar(50),
    PLAFOND              numeric(37, 8),
    OUTSTANDING          numeric(37, 8),
    KOLEKTIBILITAS       varchar(1),
    ALAMAT_AGUNAN        varchar(50),
    JENIS_AGUNAN         varchar(50),
    BAP_DATE             timestamp with time zone,
    USERID_INPUT         varchar(50),
    IS_ACTIVE            boolean,
    MAPPINGJENISAGUNANID int,
    constraint TRX_FAS_DEB_DI_BANK_LAIN_PKEY primary key (ID)
);

CREATE SEQUENCE TRX_KANWIL_EXTENDED_INFORMATION_ID_SEQ as bigint;
CREATE TABLE TRX_KANWIL_EXTENDED_INFORMATION
(
    ID            bigint default nextval('trx_kanwil_extended_information_id_seq'),
    KANWIL_KEY    int,
    KEPALA_PKW    varchar(100),
    EMAIL_PKW     varchar(250),
    MODIFIED_DATE timestamp with time zone,
    MODIFIED_BY   varchar(50),
    IS_ACTIVE     boolean,
    constraint TRX_KANWIL_EXTENDED_INFORMATION_PKEY primary key (ID)
);

CREATE SEQUENCE TRX_PROYEKSI_KOLEK_ID_SEQ as bigint;
CREATE TABLE TRX_PROYEKSI_KOLEK
(
    ID                bigint default nextval('trx_proyeksi_kolek_id_seq'),
    REGISTRATION_DATE timestamp with time zone,
    TGL_PROYEKSI      varchar(50),
    CUST_LN_CURR_KEY  int,
    KANWIL_KEY        int,
    KCU_KEY           int,
    KOLEKTIBILITAS    varchar(50),
    IS_ACTIVE         boolean,
    constraint TRX_PROYEKSI_KOLEK_PKEY primary key (ID)
);

CREATE SEQUENCE TRX_RESTRUKTURISASI_ID_SEQ as bigint;
CREATE TABLE TRX_RESTRUKTURISASI
(
    ID                  bigint default nextval('trx_restrukturisasi_id_seq'),
    REGISTRATION_DATE   timestamp with time zone,
    CUST_LN_CURR_KEY    int,
    KANWIL_KEY          int,
    KCU_KEY             int,
    TGL_RESTRUKTURISASI timestamp with time zone,
    RESTRUKTURISASI_KE  int,
    SKEMA               varchar(2000),
    USERID              varchar(50),
    MODIFIED_DATE       timestamp with time zone,
    IS_ACTIVE           boolean,
    constraint TRX_RESTRUKTURISASI_PKEY primary key (ID)
);

CREATE SEQUENCE TRX_SYARAT_KHUSUS_ID_SEQ as bigint;
CREATE TABLE TRX_SYARAT_KHUSUS
(
    ID               bigint default nextval('trx_syarat_khusus_id_seq'),
    CUST_LN_CURR_KEY int,
    KANWIL_KEY       int,
    KCU_KEY          int,
    SYARAT_KHUSUS    varchar(50),
    IS_TERPENUHI     boolean,
    IS_ACTIVE        boolean,
    constraint TRX_SYARAT_KHUSUS_PKEY primary key (ID)
);