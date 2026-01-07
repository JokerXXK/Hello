package src.structure_and_algorithm.Compare;

import java.util.Objects;

public class Date implements Comparable<Date> { // 1. 实现接口
    private final int month;
    private final int day;
    private final int year;

    public Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    // 2. 实现比较逻辑
    @Override
    public int compareTo(Date that) {
        if (this.year != that.year) return Integer.compare(this.year, that.year);
        if (this.month != that.month) return Integer.compare(this.month, that.month);
        return Integer.compare(this.day, that.day);
    }

    // 你之前的 equals 方法保持不变
    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) return false;
        Date uddDate = (Date) o;
        return this.month == uddDate.month && this.day == uddDate.day && this.year == uddDate.year;
    }
    
    // 建议加上 hashCode 保持一致性
    @Override
    public int hashCode() {
        return Objects.hash(month, day, year);
    }
}