package com.github.dinsaw.navparser.core;

import com.github.dinsaw.navparser.dto.MutualFund;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by dinsaw on 8/10/18.
 */
public interface NavParser {
    /**
     * @param file
     * @return list of {@link MutualFund}
     * @throws IOException
     */
    List<MutualFund> parse(File file) throws IOException;

    /**
     * @param url
     * @return list of {@link MutualFund}
     * @throws IOException
     */
    List<MutualFund> parse(URL url) throws IOException;
}
