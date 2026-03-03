package KiemTraDauGio;

import java.util.*;

public class ProductRepository implements IRepository<Product> {
    private List<Product> productList;
    private Map<String, Product> productMap;

    public ProductRepository() {
        productList = new ArrayList<>();
        productMap = new HashMap<>();
    }

    @Override
    public boolean add(Product item) {
        if (item == null || productMap.containsKey(item.getId())) {
            return false;
        }
        productList.add(item);
        productMap.put(item.getId(), item);
        return true;
    }

    @Override
    public boolean removeById(String id) {
        if (id == null || !productMap.containsKey(id)) {
            return false;
        }
        Product removed = productMap.remove(id);
        return productList.remove(removed);
    }

    @Override
    public Product findById(String id) {
        return id == null ? null : productMap.get(id);
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(productList);
    }

    public void sortProductsByPrice() {
        Collections.sort(productList, Comparator.comparingDouble(Product::calculateFinalPrice));
    }

    public Map<String, Integer> countProductTypes() {
        Map<String, Integer> result = new HashMap<>();
        for (Product product : productList) {
            String type = product.getClass().getSimpleName();
            result.put(type, result.getOrDefault(type, 0) + 1);
        }
        return result;
    }
}
