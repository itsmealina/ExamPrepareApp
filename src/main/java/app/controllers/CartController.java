package app.controllers;

import app.entities.CartItem;
import app.entities.PracticumTheme;
import app.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    // Просмотр корзины
    @GetMapping
    public String viewCart(Model model, Principal principal) {
        String login = principal.getName();

        List<CartItem> items = cartService.listForUser(login);
        Map<Long, PracticumTheme> themes = cartService.getThemesMapForCart(items);

        model.addAttribute("items", items);
        model.addAttribute("themes", themes);
        model.addAttribute("total", cartService.getTotalForUser(login));

        return "cart/index";
    }

    // Добавить курс в корзину
    @PostMapping("/add/{themeId}")
    public String addToCart(@PathVariable Integer themeId, Principal principal) {
        cartService.addToCart(principal.getName(), themeId);
        return "redirect:/practicum"; // остаёмся в каталоге
    }

    // Удалить курс из корзины
    @PostMapping("/remove/{itemId}")
    public String removeFromCart(@PathVariable Long itemId, Principal principal) {
        cartService.removeFromCartById(principal.getName(), itemId);
        return "redirect:/cart";
    }

    // Сформировать заказ (очистка корзины)
    @PostMapping("/checkout")
    public String checkout(Principal principal) {
        cartService.clearCart(principal.getName());
        return "redirect:/practicum";
    }
}
