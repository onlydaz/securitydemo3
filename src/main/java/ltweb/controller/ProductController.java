package ltweb.controller;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public String viewProducts(Model model) {
        model.addAttribute("products", productService.listAll());
        return "products";
    }

    @GetMapping("/product/{id}")
    public String viewProduct(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.get(id));
        return "product-details";
    }
}
