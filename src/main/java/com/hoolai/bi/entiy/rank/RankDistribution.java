package com.hoolai.bi.entiy.rank;

import com.hoolai.bi.excel.info.ExtraType;
import com.hoolai.bi.entiy.GameInfo;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-11-04 19:17
 */

@Data
public class RankDistribution extends GameInfo implements Comparable {
    private int level;
    private int numbers;
    private int totalNumbers;
    private float rankNumPercentages;

    public void init() {
        rankNumPercentages = checkDivide(numbers, totalNumbers) * 100;
    }

    public void fullRow(List<Object> row, int needLength) {
        row.set(level + needLength, String.format("%.2f", rankNumPercentages) + "%");
    }

    @Override
    public int compareTo(Object o) {
        return level - ((RankDistribution) o).level;
    }
}
