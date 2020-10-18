package product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import product.domain.DateFormula;

/*
 * 日付計算式を新規登録する画面
 */

@Controller
@RequestMapping("/register")
public class RegisterController {

	@GetMapping
	public String index(@ModelAttribute DateFormula form) {
		return "register";
	}

}
