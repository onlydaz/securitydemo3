package ltweb.controller;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("signUpDto", new SignUpDto());
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(@ModelAttribute SignUpDto signUpDto) {
        userService.signup(signUpDto);
        return "redirect:/login";
    }
}

