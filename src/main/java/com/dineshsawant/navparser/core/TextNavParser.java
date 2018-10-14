package com.dineshsawant.navparser.core;

import com.dineshsawant.navparser.dto.MutualFund;

import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by dinsaw on 14/10/18.
 */
public interface TextNavParser extends NavParser {
    /**
     *
     * @param line
     * @return MutualFund
     */
    Optional<MutualFund> parseLine(String line);

    /**
     *
     * @param line
     * @return true if line is identified as Header
     */
    boolean isHeader(String line);

    /**
     * @param line
     * @return true if line is not parsable as {@link MutualFund}
     */
    boolean shouldSkip(String line);

    default List<MutualFund> parse(File file) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

        return bufferedReader.lines()
                .filter(l -> !isHeader(l))
                .filter(l -> !shouldSkip(l))
                .map(l -> parseLine(l))
                .filter(l -> l.isPresent())
                .map(o -> o.get())
                .collect(Collectors.toList());
    }

}
