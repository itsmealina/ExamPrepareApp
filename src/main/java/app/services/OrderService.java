package app.services;

import app.entities.CartItem;
import app.entities.OrderEntity;
import app.entities.OrderItem;
import app.repositories.CartItemRepository;
import app.repositories.OrderItemRepository;
import app.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepo;
    private final OrderItemRepository orderItemRepo;
    private final CartItemRepository cartItemRepo;

    public OrderService(OrderRepository orderRepo,
                        OrderItemRepository orderItemRepo,
                        CartItemRepository cartItemRepo) {
        this.orderRepo = orderRepo;
        this.orderItemRepo = orderItemRepo;
        this.cartItemRepo = cartItemRepo;
    }

    @Transactional
    public Long createOrderFromCart(String login) {
        List<CartItem> items = cartItemRepo.findByOwnerLogin(login);
        if (items.isEmpty()) throw new RuntimeException("Cart is empty");

        int total = items.stream().mapToInt(i -> i.getPrice() * i.getQuantity()).sum();

        OrderEntity order = new OrderEntity();
        order.setOwnerLogin(login);
        order.setTotalPrice(total);
        orderRepo.save(order);

        for (CartItem ci : items) {
            OrderItem oi = new OrderItem();
            oi.setOrderId(order.getId());
            oi.setThemeId(ci.getThemeId());
            oi.setPrice(ci.getPrice());
            oi.setQuantity(ci.getQuantity());
            orderItemRepo.save(oi);
        }

        // очистить корзину пользователя
        for (CartItem ci : items) cartItemRepo.delete(ci);

        return order.getId();
    }

    public List<OrderEntity> listOrdersForUser(String login) {
        return orderRepo.findByOwnerLogin(login);
    }
}
