package com.github.dinsaw.navparser.core;

import com.github.dinsaw.navparser.dto.MutualFund;

import java.io.*;
import java.net.URL;
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

    @Override
    default List<MutualFund> parse(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamReader in = new InputStreamReader(fileInputStream);
        try(BufferedReader bufferedReader = new BufferedReader(in)) {
            return getMutualFunds(bufferedReader);
        }
    }

    default List<MutualFund> getMutualFunds(BufferedReader bufferedReader) {
        return bufferedReader.lines()
            .filter(l -> !isHeader(l))
            .filter(l -> !shouldSkip(l))
            .map(l -> parseLine(l))
            .filter(l -> l.isPresent())
            .map(o -> o.get())
            .collect(Collectors.toList());
    }

    @Override
    default List<MutualFund> parse(URL url) throws IOException {
        InputStreamReader in = new InputStreamReader(url.openStream());
        try(BufferedReader bufferedReader = new BufferedReader(in)) {
            return getMutualFunds(bufferedReader);
        }
    }
}
