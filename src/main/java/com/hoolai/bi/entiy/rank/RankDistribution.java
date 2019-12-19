package com.hoolai.bi.entiy.rank;

import com.hoolai.bi.excel.info.ExtraType;
import com.hoolai.bi.entiy.GameInfo;

import java.util.List;

/**
 * @description:
 * @author: Ksssss(chenlin @ hoolai.com)
 * @time: 2019-11-04 19:17
 */

public class RankDistribution extends GameInfo implements Comparable {
    private int level;
    private int numbers;
    private int totalNumbers;
    private float rankNumPercentages;

    public void init(){
        rankNumPercentages = checkDivide(numbers, totalNumbers) * 100;
    }

    public void fullRow(List<Object> row,ExtraType extraType) {
        row.set(level+extraType.getNeedRowLength(), String.format("%.2f", rankNumPercentages) + "%");
    }

    @Override
    public int compareTo(Object o) {
        return level - ((RankDistribution) o).level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getNumbers() {
        return numbers;
    }

    public void setNumbers(int numbers) {
        this.numbers = numbers;
    }

    public int getTotalNumbers() {
        return totalNumbers;
    }

    public void setTotalNumbers(int totalNumbers) {
        this.totalNumbers = totalNumbers;
    }

    public float getRankNumPercentages() {
        return rankNumPercentages;
    }

    public void setRankNumPercentages(float rankNumPercentages) {
        this.rankNumPercentages = rankNumPercentages;
    }
}
