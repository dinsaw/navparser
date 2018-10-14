package com.dineshsawant.navparser.india;

import com.dineshsawant.navparser.dto.MutualFund;
import org.junit.Test;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by dinsaw on 14/10/18.
 */
public class AmfiIndiaNavParserTest {

    private static final String NAV_SMALL_TXT = "amfiIndia/nav-small.txt";
    private static final String NAV_LARGE_TXT = "amfiIndia/nav-large.txt";

    private AmfiIndiaNavParser amfiIndiaNavParser = new AmfiIndiaNavParser();

    @Test(timeout = 500)
    public void testParseSmallFile() throws Exception {
        final File navFile = new File(getClass().getClassLoader().getResource(NAV_SMALL_TXT).toURI());

        List<MutualFund> mutualFunds = amfiIndiaNavParser.parse(navFile);
        assertEquals(12, mutualFunds.size());

        MutualFund mutualFund = mutualFunds.get(0);
        assertEquals("119551", mutualFund.getSchemeCode());
        assertEquals("INF209KA12Z1", mutualFund.getIsinDivPayout());
        assertEquals("INF209KA13Z9", mutualFund.getIsinDivReinvestment());
        assertEquals("Aditya Birla Sun Life Banking & PSU Debt Fund  - Direct Plan-Dividend", mutualFund.getSchemeName());
        assertEquals(Double.valueOf(145.82), mutualFund.getNetAssetValue());
        assertEquals(LocalDate.of(2018, 10, 01), mutualFund.getNavDate());
    }

    @Test(timeout = 1000)
    public void testParseLargeFile() throws Exception {
        final File navFile = new File(getClass().getClassLoader().getResource(NAV_LARGE_TXT).toURI());

        List<MutualFund> mutualFunds = amfiIndiaNavParser.parse(navFile);
        assertEquals(14788, mutualFunds.size());

        MutualFund mutualFund = mutualFunds.get(0);
        assertEquals("119551", mutualFund.getSchemeCode());
        assertEquals("INF209KA12Z1", mutualFund.getIsinDivPayout());
        assertEquals("INF209KA13Z9", mutualFund.getIsinDivReinvestment());
        assertEquals("Aditya Birla Sun Life Banking & PSU Debt Fund  - Direct Plan-Dividend", mutualFund.getSchemeName());
        assertEquals(Double.valueOf(145.82), mutualFund.getNetAssetValue());
        assertEquals(LocalDate.of(2018, 10, 1), mutualFund.getNavDate());
    }

}