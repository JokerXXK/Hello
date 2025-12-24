package src.experiments;

public class Date {
    private final int month;
    private final int day;
    private final int year;

    public Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }
    @Override
    public boolean equals(Object o){
        if (o == null) {
            return false;
            
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        Date uddDate = (Date) o;
        return this.month == uddDate.month && this.day == uddDate.day && this.year == uddDate.year;
        
    }
}
