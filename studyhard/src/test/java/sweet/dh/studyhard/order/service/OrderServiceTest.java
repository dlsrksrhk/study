package sweet.dh.studyhard.order.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import sweet.dh.studyhard.order.entity.Item;
import sweet.dh.studyhard.order.entity.Order;
import sweet.dh.studyhard.order.service.OrderService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private EntityManager entityManager;

    private Order order;

    @BeforeEach
    public void setUp() {
        order = new Order();

        Item item1 = new Item();
        item1.setItemName("Item 1");
        item1.setAmount(100L);
        item1.setStockQuantity(10L);

        Item item2 = new Item();
        item2.setItemName("Item 2");
        item2.setAmount(200L);
        item2.setStockQuantity(20L);

        order.addItem(item1);
        order.addItem(item2);
    }

    @Test
    public void testSaveOrder() {
        Order savedOrder = orderService.saveOrder(order);
        assertNotNull(savedOrder.getId());
    }

    @Test
    public void testGetAllOrders() {
        orderService.saveOrder(order);
        List<Order> orders = orderService.getAllOrders();
        assertFalse(orders.isEmpty());
    }

    @Test
    public void testGetOrderById() {
        Order savedOrder = orderService.saveOrder(order);
        Order foundOrder = orderService.getOrderById(savedOrder.getId());
        assertNotNull(foundOrder);
    }

    @Test
    public void testDeleteOrder() {
        Order savedOrder = orderService.saveOrder(order);
        orderService.deleteOrder(savedOrder.getId());
        Order deletedOrder = orderService.getOrderById(savedOrder.getId());
        assertNull(deletedOrder);
    }

    @Test
    public void jpqlJoinTest() {
        TypedQuery<Item> query = entityManager
//                .createQuery("SELECT i.order FROM Item i WHERE i.itemName = :itemName", Item.class);
                .createQuery("SELECT o FROM Item i JOIN i.order o WHERE i.itemName = :itemName", Item.class);
        query.setParameter("itemName", "Item 1");
        List<Item> items = query.getResultList();
        System.out.println(items);
    }
}