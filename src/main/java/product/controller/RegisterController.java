package product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import product.domain.DateFormula;
import product.service.CalculationServise;

/*
 * 日付計算式を新規登録する画面
 */

@Controller
@RequestMapping("/register")
public class RegisterController {

	/** CalculationServiceに依存 */
	@Autowired
	private CalculationServise servise;

	@GetMapping
	public String index(@ModelAttribute DateFormula form) {
		return "register";
	}

	/**
	 * 日付計算式を新規登録する
	 * 登録した後は一覧画面に戻る
	 *
	 * @param form 画面フォーム
	 * @param model モデル
	 * @return 表示するテンプレート
	 */
	@PostMapping
	public String register(@ModelAttribute @Validated DateFormula form, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "register";
		}

		servise.register(form);
		// model.addAttribute("simulationForm", new SimulationForm());
		return "simulation";

	}

}
