package src.experiments;

import org.junit.Test;
import static org.junit.Assert.*;

public class DateEqualsTest {
    @Test
    public void testEquals() {
        Date date1 = new Date(5, 10, 2010);
        Date date2 = new Date(5, 10, 2010);
        Date date3 = new Date(10, 11, 2012);

        assertEquals(date1,date1);
        assertEquals(date1, date2);
        assertNotEquals(date1, date3);
        assertNotEquals(date1, "horse");
        assertNotEquals(date1, null);
    }
}
