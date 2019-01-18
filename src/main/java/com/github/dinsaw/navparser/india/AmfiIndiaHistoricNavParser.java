package com.github.dinsaw.navparser.india;

import com.github.dinsaw.navparser.dto.MutualFund;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 *
 * Parses file Scheme Code;Scheme Name;Net Asset Value;Repurchase Price;Sale Price;Date
 *
 */
public class AmfiIndiaHistoricNavParser extends AmfiIndiaNavParser {

    private static final String NAV_DATE_PATTERN = "dd-MMM-yyyy";
    private static final int SCHEME_INDEX = 0;
    private static final int SCHEME_NAME_INDEX = 1;
    private static final int NAV_INDEX = 2;
    private static final int NAV_DATE_INDEX = 5;

    @Override
    public Optional<MutualFund> parseLine(String line) {
        String[] words = line.split(";");
        return Optional.ofNullable(MutualFund.builder()
                .schemeCode(words[SCHEME_INDEX])
                .schemeName(words[SCHEME_NAME_INDEX])
                .netAssetValue(parseNetAssetValue(words[NAV_INDEX]))
                .navDate(LocalDate.parse(words[NAV_DATE_INDEX], DateTimeFormatter.ofPattern(NAV_DATE_PATTERN)))
                .build());
    }

}
