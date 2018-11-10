package com.github.dinsaw.navparser.india;

import com.github.dinsaw.navparser.core.TextNavParser;
import com.github.dinsaw.navparser.dto.MutualFund;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by dinsaw on 14/10/18.
 */
public class AmfiIndiaNavParserTest {

    private static final String NAV_SMALL_TXT = "amfiIndia/nav-small.txt";
    private static final String NAV_LARGE_TXT = "amfiIndia/nav-large.txt";
    private static final String GITHUB_RAW_AMFI_INDIA_FILE_TEXT = "https://raw.githubusercontent.com/dinsaw/navparser/master/src/test/resources/amfiIndia/nav-small.txt";
    private static final String AMFI_INDIA_NAV_URL = "https://www.amfiindia.com/spages/NAVAll.txt";

    private TextNavParser amfiIndiaNavParser = new AmfiIndiaNavParser();

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

    @Test(timeout = 2000)
    public void testParseSmallFileFromURL() throws Exception {
        URL url = new URL(GITHUB_RAW_AMFI_INDIA_FILE_TEXT);

        List<MutualFund> mutualFunds = amfiIndiaNavParser.parse(url);
        assertEquals(12, mutualFunds.size());

        MutualFund mutualFund = mutualFunds.get(0);
        assertEquals("119551", mutualFund.getSchemeCode());
        assertEquals("INF209KA12Z1", mutualFund.getIsinDivPayout());
        assertEquals("INF209KA13Z9", mutualFund.getIsinDivReinvestment());
        assertEquals("Aditya Birla Sun Life Banking & PSU Debt Fund  - Direct Plan-Dividend", mutualFund.getSchemeName());
        assertEquals(Double.valueOf(145.82), mutualFund.getNetAssetValue());
        assertEquals(LocalDate.of(2018, 10, 01), mutualFund.getNavDate());
    }

    /**
     * This test will ensure the Parser is able to parse current amfi india NAV text file.
     * @throws Exception
     */
    @Test
    public void testParseRealAMFIUrl() throws Exception {
        URL url = new URL(AMFI_INDIA_NAV_URL);

        List<MutualFund> mutualFunds = amfiIndiaNavParser.parse(url);
        assertTrue(0 < mutualFunds.size());

        MutualFund mutualFund = mutualFunds.get(0);
        assertNotNull(mutualFund.getSchemeCode());
        assertNotNull(mutualFund.getIsinDivPayout());
        assertNotNull(mutualFund.getIsinDivReinvestment());
        assertNotNull(mutualFund.getSchemeName());
        assertNotNull(mutualFund.getNetAssetValue());
        assertNotNull(mutualFund.getNavDate());
    }

}