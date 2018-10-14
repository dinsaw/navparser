package com.dineshsawant.navparser.india;

import com.dineshsawant.navparser.core.TextNavParser;
import com.dineshsawant.navparser.dto.MutualFund;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 *
 * Parses NAV file which is downloadable at https://www.amfiindia.com/spages/NAVAll.txt.
 *
 * Created by dinsaw on 8/10/18.
 */
public class AmfiIndiaNavParser implements TextNavParser {

    private static final String NAV_DATE_PATTERN = "dd-MMM-yyyy";
    private static final int SCHEME_INDEX = 0;
    private static final int ISIN_DIV_PAYOUT_INDEX = 1;
    private static final int ISIN_DIV_REINVESTMENT_INDEX = 2;
    private static final int SCHEME_NAME_INDEX = 3;
    private static final int NAV_INDEX = 4;

    /**
     *
     * Scheme Code;ISIN Div Payout/ ISIN Growth;ISIN Div Reinvestment;Scheme Name;Net Asset Value;Date
     * @param line
     * @return
     */
    @Override
    public Optional<MutualFund> parseLine(String line) {
        String[] words = line.split(";");
        return Optional.ofNullable(MutualFund.builder()
                .schemeCode(words[SCHEME_INDEX])
                .isinDivPayout(words[ISIN_DIV_PAYOUT_INDEX])
                .isinDivReinvestment(words[ISIN_DIV_REINVESTMENT_INDEX])
                .schemeName(words[SCHEME_NAME_INDEX])
                .netAssetValue(parseNetAssetValue(words[NAV_INDEX]))
                .navDate(LocalDate.parse(words[5], DateTimeFormatter.ofPattern(NAV_DATE_PATTERN)))
                .build());
    }

    private Double parseNetAssetValue(String word) {
        try {
            return Double.valueOf(word);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean isHeader(String line) {
        return line.contains("Scheme Code");
    }

    @Override
    public boolean shouldSkip(String line) {
        return line.isEmpty() || !line.contains(";");
    }
}
