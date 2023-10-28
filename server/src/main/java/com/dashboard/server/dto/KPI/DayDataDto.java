package com.dashboard.server.dto.KPI;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DayDataDto {
    private Long id;

    // JsonFormat추가하지 않으면 TimeStamp형식으로 나온다.
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private BigDecimal revenue;

    private BigDecimal expenses;
}
