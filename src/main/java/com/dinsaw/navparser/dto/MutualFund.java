package com.dinsaw.navparser.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

/**
 * Created by dinsaw on 8/10/18.
 */
@Data
@Builder
public class MutualFund {
    private String schemeCode;
    private String isinDivPayout;
    private String isinDivReinvestment;
    private String schemeName;
    private Double netAssetValue;
    private LocalDate navDate;
}
