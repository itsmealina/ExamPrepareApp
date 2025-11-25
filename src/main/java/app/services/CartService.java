package app.services;

import app.entities.CartItem;
import app.entities.PracticumTheme;
import app.repositories.CartItemRepository;
import app.repositories.PracticumThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartItemRepository cartRepo;
    private final PracticumThemeRepository themeRepo;

    // Получение всех товаров в корзине пользователя
    public List<CartItem> listForUser(String login) {
        return cartRepo.findByOwnerLogin(login);
    }

    // Добавление товара в корзину
    @Transactional
    public void addToCart(String login, Integer themeId) {
        PracticumTheme theme = themeRepo.findById(themeId)
                .orElseThrow(() -> new RuntimeException("Theme not found"));

        if (!theme.isPaid()) return; // бесплатные не добавляем

        cartRepo.findByOwnerLoginAndThemeId(login, themeId)
                .ifPresentOrElse(item -> {
                    item.setQuantity(item.getQuantity() + 1);
                    cartRepo.save(item);
                }, () -> {
                    CartItem newItem = new CartItem();
                    newItem.setOwnerLogin(login);
                    newItem.setThemeId(themeId);
                    newItem.setPrice(theme.getPrice());
                    newItem.setQuantity(1);
                    cartRepo.save(newItem);
                });
    }

    // Удаление элемента корзины
    @Transactional
    public void removeFromCartById(String login, Long cartItemId) {
        cartRepo.findById(cartItemId).ifPresent(ci -> {
            if (login.equals(ci.getOwnerLogin())) {
                cartRepo.delete(ci);
            }
        });
    }

    // Очистка корзины при оформлении заказа
    @Transactional
    public void clearCart(String login) {
        List<CartItem> items = cartRepo.findByOwnerLogin(login);
        cartRepo.deleteAll(items);
    }

    // Сумма корзины
    public Integer getTotalForUser(String login) {
        return listForUser(login).stream()
                .mapToInt(ci -> ci.getPrice() * ci.getQuantity())
                .sum();
    }

    // Map для шаблона (item.id → PracticumTheme)
    public Map<Long, PracticumTheme> getThemesMapForCart(List<CartItem> items) {
        Set<Integer> ids = items.stream().map(CartItem::getThemeId).collect(Collectors.toSet());
        List<PracticumTheme> themes = themeRepo.findAllById(ids);

        Map<Long, PracticumTheme> map = new HashMap<>();
        for (CartItem item : items) {
            themes.stream()
                    .filter(t -> t.getId().equals(item.getThemeId()))
                    .findFirst()
                    .ifPresent(theme -> map.put(item.getId(), theme));
        }
        return map;
    }
}
