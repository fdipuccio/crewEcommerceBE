import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping
    public List<CartItem> getCartItems(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return cartService.getCartItems(user);
    }

    @PostMapping
    public ResponseEntity<CartItem> addToCart(@RequestBody CartItem cartItem) {
        cartService.addToCart(cartItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartItem);
    }
}