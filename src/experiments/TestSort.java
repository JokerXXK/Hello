package src.experiments;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestSort {
    @Test  //  使用导入的 Test 注解
    public void testSort() {
        String[] input = {"I", "have", "an", "egg"};
        String[] expected = {"an", "egg", "have", "I"};
        Sort.sort(input);
        assertArrayEquals(expected, input);  // 使用正确的断言方法
    }
    
    @Test
    public void testFindSmallest() {
        String[] input = {"I", "have", "an", "egg"};
        int expected = 2;
        int result = Sort.findSmallest(input, 0);
        assertEquals(expected, result);  // 使用正确的断言方法
    }

    @Test
    public void testSwap() {
        String[] input = {"I", "have", "an", "egg"};
        String[] expected = {"an", "have", "I", "egg"};
        Sort.swap(input, 0, 2);
        assertArrayEquals(expected, input);  // 使用正确的断言方法
    }
    // 注意：不需要 main 方法了！
    // JUnit 会自动发现和运行 @Test 方法
}