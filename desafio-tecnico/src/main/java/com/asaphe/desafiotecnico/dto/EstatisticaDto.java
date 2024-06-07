package com.asaphe.desafiotecnico.dto;

import lombok.Getter;

import java.util.DoubleSummaryStatistics;

@Getter
public class EstatisticaDto {

    private final long count;
    private final Double sum;
    private final Double avg;
    private final Double min;
    private final Double max;

    public EstatisticaDto() {
        this(new DoubleSummaryStatistics());
    }

    public EstatisticaDto(final DoubleSummaryStatistics doubleSummaryStatistics) {
        this.count = doubleSummaryStatistics.getCount();
        this.sum = doubleSummaryStatistics.getSum();
        this.avg = doubleSummaryStatistics.getAverage();
        this.min = Math.min(0, doubleSummaryStatistics.getMin());
        this.max = Math.max(0, doubleSummaryStatistics.getMax());
    }
}
