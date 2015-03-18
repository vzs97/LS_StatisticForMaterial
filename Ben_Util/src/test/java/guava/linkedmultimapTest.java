package guava;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import org.junit.Test;
import utils.DateUtil;

import java.util.Set;

/**
 * Created by byao on 2/2/15.
 */
public class LinkedmultimapTest {
    @Test
    public void testLinkedMap(){
        Multimap<Long, String> productIdVendorItemsMap = LinkedListMultimap.create();
        productIdVendorItemsMap.put(1L, "z");
        productIdVendorItemsMap.put(3L, "a");
        productIdVendorItemsMap.put(2L, "b");
        productIdVendorItemsMap.put(5L, "d");
        productIdVendorItemsMap.put(9L, "e");
        productIdVendorItemsMap.put(1L, "e");

        Set<Long> longs = productIdVendorItemsMap.keySet();
        for(Long id : longs){
            System.out.println(id);
            System.out.println(productIdVendorItemsMap.get(id));
        }

        System.out.println(productIdVendorItemsMap.values());
    }
}
