package com.github.dinsaw.navparser.india;

import com.github.dinsaw.navparser.core.TextNavParser;
import com.github.dinsaw.navparser.dto.MutualFund;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.net.URL;
import java.util.List;

import static org.junit.Assert.*;

public class AmfiIndiaHistoricNavParserTest {

    private static final String AMFI_INDIA_HISTORIC_NAV_URL = "http://portal.amfiindia.com/DownloadNAVHistoryReport_Po.aspx?frmdt=01-Jan-2017&todt=03-Jan-2017";

    private TextNavParser amfiIndiaHistoricNavParser = new AmfiIndiaHistoricNavParser();
    /**
     * This test will ensure the Parser is able to parse current amfi india NAV text file.
     * @throws Exception
     */
    @Test
    public void testParseRealAMFIUrl() throws Exception {
        URL url = new URL(AMFI_INDIA_HISTORIC_NAV_URL);

        List<MutualFund> mutualFunds = amfiIndiaHistoricNavParser.parse(url);
        assertTrue(0 < mutualFunds.size());

        MutualFund mutualFund = mutualFunds.get(0);
        System.out.println("Mf = " + mutualFund);

        assertNotNull(mutualFund.getSchemeCode());
        assertNull(mutualFund.getIsinDivPayout());
        assertNull(mutualFund.getIsinDivReinvestment());
        assertNotNull(mutualFund.getSchemeName());
        assertNotNull(mutualFund.getNetAssetValue());
        assertNotNull(mutualFund.getNavDate());
    }

}