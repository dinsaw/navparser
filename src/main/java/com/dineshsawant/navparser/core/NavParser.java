package com.dineshsawant.navparser.core;

import com.dineshsawant.navparser.dto.MutualFund;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by dinsaw on 8/10/18.
 */
public interface NavParser {
    /**
     * @param file
     * @return list of {@link MutualFund}
     * @throws FileNotFoundException
     */
    List<MutualFund> parse(File file) throws FileNotFoundException;
}
