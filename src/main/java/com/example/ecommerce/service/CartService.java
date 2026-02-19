import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public List<CartItem> getCartItems(User user) {
        return cartRepository.findByUser(user);
    }

    public void addToCart(CartItem cartItem) {
        cartRepository.save(cartItem);
    }
}