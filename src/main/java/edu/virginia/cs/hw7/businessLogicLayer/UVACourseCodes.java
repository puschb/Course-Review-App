package edu.virginia.cs.hw7.businessLogicLayer;

import org.hibernate.mapping.Array;

import java.util.Arrays;

public class UVACourseCodes {
    private static final String[] COURSE_CODES = {"AAS", "ACCT", "AIRS", "ALAR", "AM", "AMST", "ANTH", "APMA", "ARAB",
            "ARAD", "ARAH", "ARCH", "ARCY", "ARH", "ARTH", "ARTR", "ARTS", "ASL", "ASTR", "BENG", "BIMS", "BIOC",
            "BIOE", "BIOL", "BIOM", "BIOP", "BME", "BUS", "CASS", "CE", "CELL", "CGBM", "CHE", "CHEM", "CHIN", "CHTR",
            "CJ", "CLAS", "COGS", "COLA", "COMM", "CONC", "CPE", "CPLT", "CREO", "CS", "DANC", "DEM", "DH", "DRAM",
            "DS", "EALC", "EAST", "ECE", "ECON", "EDHS", "EDIS", "EDLF", "EDNC", "EGMT", "ELA", "ENAM", "ENCR", "ENCW",
            "ENEC", "ENGL", "ENGN", "ENGR", "ENLS", "ENMC", "ENMD", "ENNC", "ENPG", "ENPW", "ENRN", "ENSP", "ENTP",
            "ENVH", "ENWR", "EP", "ESL", "ETP", "EURS", "EVAT", "EVEC", "EVGE", "EVHY", "EVSC", "FORU", "FREN", "FRLN",
            "FRTR", "GBAC", "GBUS", "GCCS", "GCNL", "GCOM", "GDS", "GERM", "GETR", "GHSS", "GNUR", "GREE", "GSAS",
            "GSCI", "GSGS", "GSMS", "GSSJ", "GSVS", "HBIO", "HEBR", "HETR", "HHE", "HIAF", "HIEA", "HIEU", "HILA",
            "HIME", "HIND", "HISA", "HIST", "HIUS", "HR", "HSCI", "HUMS", "IHGC", "IMP", "INST", "ISBU", "ISCP",
            "ISED", "ISHU", "ISIN", "ISLS", "ISSS", "IT", "ITAL", "ITTR", "JAPN", "JPTR", "JWST", "KICH", "KINE",
            "KLPA", "KOR", "KRTR", "LAR", "LASE", "LAST", "LATI", "LAW", "LING", "LNGS", "LPPA", "LPPL", "LPPP", "LPPS",
            "MAE", "MATH", "MDST", "MED", "MESA", "MEST", "MICR", "MISC", "MSE", "MSP", "MUBD", "MUEN", "MUPF", "MUSI",
            "NASC", "NCAR", "NCBM", "NCBS", "NCCJ", "NCCS", "NCDS", "NCED", "NCEN", "NCFA", "NCFL", "NCHP", "NCIS",
            "NCLE", "NCPD", "NCPH", "NCPR", "NCSS", "NCTH", "NESC", "NMVS", "NUCO", "NUIP", "NURS", "PASH", "PATH",
            "PAVS", "PC", "PERS", "PETR", "PHAR", "PHIL", "PHS", "PHY", "PHYS", "PLAC", "PLAD", "PLAN", "PLAP", "PLCP",
            "PLIR", "PLPT", "PLSK", "PMCC", "POL", "PORT", "POTR", "PPL", "PSCJ", "PSED", "PSHM", "PSHP", "PSLP",
            "PSLS", "PSPA", "PSPL", "PSPM", "PSPS", "PSSS", "PST", "PSTS", "PSWD", "PSYC", "RELA", "RELB", "RELC",
            "RELG", "RELH", "RELI", "RELJ", "RELS", "RSC", "RUSS", "RUTR", "SANS", "SARC", "SAST", "SATR", "SEC",
            "SEMS", "SLAV", "SLFK", "SLTR", "SOC", "SPAN", "SPTR", "STAT", "STS", "SWAH", "SYS", "TBTN", "TURK", "UD",
            "UKR", "UNST", "URDU", "USEM", "WGS", "XHOS", "YIDD", "YITR", "ZFOR"};

    public static boolean contains(String courseCode) {
        return Arrays.asList(COURSE_CODES).contains(courseCode);
    }
}
