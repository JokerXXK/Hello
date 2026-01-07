package src.structure_and_algorithm.Compare;

import java.util.*;
import java.util.stream.Collectors;


// 1. 产品类，实现 Comparable 接口（自然排序：按ID）
class Product implements Comparable<Product> {
    private int id;
    private String name;
    private double price;
    private int stock;
    private String category;
    private Date createDate;
    
    public Product(int id, String name, double price, int stock, String category, Date createDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.createDate = createDate;
    }
    
    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public String getCategory() { return category; }
    public Date getCreateDate() { return createDate; }
    
    // 1. 实现 Comparable 接口 - 按ID排序（自然顺序）
    @Override
    public int compareTo(Product other) {
        return Integer.compare(this.id, other.id);
    }
    
    @Override
    public String toString() {
        return String.format("ID:%d | %-10s | 价格:¥%-6.1f | 库存:%-3d | 分类:%-5s | 日期:%tF", 
                           id, name, price, stock, category, createDate);
    }
    
    // 2. 静态 Comparator 工厂方法（类内部定义）
    
    // 按价格升序
    public static Comparator<Product> byPrice() {
        return Comparator.comparingDouble(Product::getPrice);
    }
    
    // 按价格降序
    public static Comparator<Product> byPriceDesc() {
        return Comparator.comparingDouble(Product::getPrice).reversed();
    }
    
    // 按名称排序（字母顺序）
    public static Comparator<Product> byName() {
        return Comparator.comparing(Product::getName);
    }
    
    // 按库存降序（库存多的排前面）
    public static Comparator<Product> byStockDesc() {
        return Comparator.comparingInt(Product::getStock).reversed();
    }
    
    // 复杂排序：先按分类，再按价格降序
    public static Comparator<Product> byCategoryThenPriceDesc() {
        return Comparator.comparing(Product::getCategory)
                       .thenComparing(Product::getPrice).reversed();
    }
    
    // 按创建日期降序（最新的排前面）
    public static Comparator<Product> byDateDesc() {
        return Comparator.comparing(Product::getCreateDate).reversed();
    }
}

// 3. 独立的外部 Comparator 类
class ProductValueComparator implements Comparator<Product> {
    // 按性价比排序：库存/价格 比值高的排前面
    @Override
    public int compare(Product p1, Product p2) {
        double value1 = p1.getStock() / p1.getPrice();
        double value2 = p2.getStock() / p2.getPrice();
        return Double.compare(value2, value1); // 降序
    }
}

// 4. 带权重的自定义 Comparator
class ProductWeightedComparator implements Comparator<Product> {
    private final Map<String, Integer> categoryWeights;
    
    public ProductWeightedComparator() {
        categoryWeights = new HashMap<>();
        categoryWeights.put("电子产品", 1);
        categoryWeights.put("服装", 2);
        categoryWeights.put("食品", 3);
        categoryWeights.put("图书", 4);
    }
    
    @Override
    public int compare(Product p1, Product p2) {
        // 先按分类权重排序，再按价格
        int weight1 = categoryWeights.getOrDefault(p1.getCategory(), 99);
        int weight2 = categoryWeights.getOrDefault(p2.getCategory(), 99);
        
        if (weight1 != weight2) {
            return Integer.compare(weight1, weight2);
        }
        return Double.compare(p1.getPrice(), p2.getPrice());
    }
}

// 5. 主类，包含 main 方法
public class ProductComparisonDemo {
    public static void main(String[] args) {
        // 创建产品列表
        List<Product> products = Arrays.asList(
            new Product(103, "iPhone 15", 8999.0, 50, "电子产品", new Date(123, 0, 15)),
            new Product(101, "牛仔裤", 299.0, 200, "服装", new Date(123, 1, 20)),
            new Product(105, "Java编程思想", 128.0, 150, "图书", new Date(122, 11, 10)),
            new Product(102, "巧克力", 45.5, 500, "食品", new Date(123, 2, 5)),
            new Product(104, "耳机", 399.0, 80, "电子产品", new Date(123, 1, 8)),
            new Product(106, "T恤", 89.0, 300, "服装", new Date(123, 0, 30))
        );
        
        System.out.println("=== 原始数据 ===");
        products.forEach(System.out::println);
        
        // 1. 使用 Comparable 自然排序（按ID）
        System.out.println("\n=== 1. 自然排序（Comparable - 按ID） ===");
        List<Product> naturalSorted = products.stream()
            .sorted()
            .collect(Collectors.toList());
        naturalSorted.forEach(System.out::println);
        
        // 2. 使用内部静态 Comparator（按价格）
        System.out.println("\n=== 2. 按价格升序 ===");
        List<Product> priceSorted = products.stream()
            .sorted(Product.byPrice())
            .collect(Collectors.toList());
        priceSorted.forEach(System.out::println);
        
        // 3. 使用内部静态 Comparator（按价格降序）
        System.out.println("\n=== 3. 按价格降序 ===");
        List<Product> priceDescSorted = products.stream()
            .sorted(Product.byPriceDesc())
            .collect(Collectors.toList());
        priceDescSorted.forEach(System.out::println);
        
        // 4. 使用内部静态 Comparator（按库存降序）
        System.out.println("\n=== 4. 按库存降序 ===");
        List<Product> stockDescSorted = products.stream()
            .sorted(Product.byStockDesc())
            .collect(Collectors.toList());
        stockDescSorted.forEach(System.out::println);
        
        // 5. 使用链式 Comparator（先分类，再价格降序）
        System.out.println("\n=== 5. 先按分类，再按价格降序 ===");
        List<Product> categoryPriceSorted = products.stream()
            .sorted(Product.byCategoryThenPriceDesc())
            .collect(Collectors.toList());
        categoryPriceSorted.forEach(System.out::println);
        
        // 6. 使用独立的外部 Comparator（性价比排序）
        System.out.println("\n=== 6. 按性价比（库存/价格）排序 ===");
        List<Product> valueSorted = products.stream()
            .sorted(new ProductValueComparator())
            .collect(Collectors.toList());
        valueSorted.forEach(System.out::println);
        
        // 7. 使用自定义权重 Comparator
        System.out.println("\n=== 7. 按分类权重排序 ===");
        List<Product> weightedSorted = products.stream()
            .sorted(new ProductWeightedComparator())
            .collect(Collectors.toList());
        weightedSorted.forEach(System.out::println);
        
        // 8. 使用 Lambda 表达式创建即时 Comparator
        System.out.println("\n=== 8. 按名称长度排序（Lambda） ===");
        List<Product> nameLengthSorted = products.stream()
            .sorted((p1, p2) -> 
                Integer.compare(p1.getName().length(), p2.getName().length()))
            .collect(Collectors.toList());
        nameLengthSorted.forEach(System.out::println);
        
        // 9. 使用 Java 8 方法引用创建 Comparator
        System.out.println("\n=== 9. 按创建日期排序 ===");
        List<Product> dateSorted = products.stream()
            .sorted(Comparator.comparing(Product::getCreateDate))
            .collect(Collectors.toList());
        dateSorted.forEach(System.out::println);
        
        // 10. 复杂排序示例：多条件排序
        System.out.println("\n=== 10. 多条件复杂排序 ===");
        List<Product> complexSorted = products.stream()
            .sorted(Comparator
                .comparing(Product::getCategory)  // 先按分类
                .thenComparing(Product::getStock).reversed()  // 再按库存降序
                .thenComparing(Product::getPrice)  // 最后按价格升序
            )
            .collect(Collectors.toList());
        complexSorted.forEach(System.out::println);
        
        // 11. 使用 TreeSet 演示自动排序
        System.out.println("\n=== 11. TreeSet 自动排序（使用 Comparable） ===");
        Set<Product> productSet = new TreeSet<>(products);
        productSet.forEach(System.out::println);
        
        // 12. 使用 TreeSet 带自定义 Comparator
        System.out.println("\n=== 12. TreeSet 按价格排序 ===");
        Set<Product> priceSet = new TreeSet<>(Product.byPrice());
        priceSet.addAll(products);
        priceSet.forEach(System.out::println);
        
        // 13. 查找最大值和最小值
        System.out.println("\n=== 13. 查找最贵和最便宜的产品 ===");
        Optional<Product> mostExpensive = products.stream()
            .max(Comparator.comparingDouble(Product::getPrice));
        Optional<Product> cheapest = products.stream()
            .min(Comparator.comparingDouble(Product::getPrice));
        
        mostExpensive.ifPresent(p -> System.out.println("最贵: " + p));
        cheapest.ifPresent(p -> System.out.println("最便宜: " + p));
        
        // 14. 分组排序
        System.out.println("\n=== 14. 按分类分组，每组按价格排序 ===");
        Map<String, List<Product>> groupedProducts = products.stream()
            .collect(Collectors.groupingBy(Product::getCategory));
        
        groupedProducts.forEach((category, productList) -> {
            System.out.println("\n分类: " + category);
            productList.stream()
                .sorted(Product.byPrice())
                .forEach(System.out::println);
        });
        
        // 15. 传统方式排序（对比）
        System.out.println("\n=== 15. 传统 Collections.sort() 方式 ===");
        List<Product> traditionalList = new ArrayList<>(products);
        
        // 使用 Comparable
        Collections.sort(traditionalList);
        System.out.println("传统方式 - 按ID排序:");
        traditionalList.forEach(System.out::println);
        
        // 使用 Comparator
        Collections.sort(traditionalList, Product.byPriceDesc());
        System.out.println("\n传统方式 - 按价格降序:");
        traditionalList.forEach(System.out::println);
    }
}
