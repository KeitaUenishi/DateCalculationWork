package product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import product.service.CalculationServise;

@Controller
public class SimulationController {

	/** CalculationServiseに依存 */
	@Autowired
	private CalculationServise servise;

	/**
	 * 初期表示処理
	 * @param form 画面フォーム
	 * @return 表示するテンプレート
	 */
	@GetMapping("/")
	public String index() {
		return "simulation";
	}

}
