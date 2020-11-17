package product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import product.domain.Result;
import product.domain.SimulationForm;
import product.service.CalculationService;

@Controller
@RequestMapping
public class SimulationController {

	// private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/** CalculationServiseに依存 */
	@Autowired
	private CalculationService service;

	/**
	 * 初期表示処理
	 * @param form 画面フォーム
	 * @return 表示するテンプレート
	 */
	@GetMapping
	public String index(
			@ModelAttribute SimulationForm form) {
		return "simulation";
	}

	/**
	 * 日付計算式をもとにシミュレーション結果を一覧表示する
	 *
	 * @param form 画面フォーム
	 * @param bindingResult 入力値検証の結果
	 * @param model モデル
	 * @return 表示するテンプレート
	 */
	@PostMapping
	public String simulation(
			@ModelAttribute @Validated SimulationForm form,
			BindingResult bindingResult, Model model) {
		//		System.out.println("hoooooo");
		if (bindingResult.hasErrors()) {
			return "simulation";
		}

		/**
		 * SimulationForm型のインスタンスを作成
		 * SimlationFormの引数にgetBaseDate「計算基準日」、servise.search「日付計算式の一覧を全件検索して取得する」
		 */
		SimulationForm simulationForm = new SimulationForm(form.getBaseDate(), service.search());
		List<Result> results = simulationForm.getResults();

		//ラムダ式
		results.stream().forEach(e -> e.setCalculated(service.calculate(form.getBaseDate(), e.getFormula())));

		/**
		 * model.addAttributeとは
		 * ・コントローラーからビュー側に値を渡す処理を行う場合に使う
		 * 第一引数にテンプレートから参照する変数名、第二引数にオブジェクト名として格納
		 */
		model.addAttribute("results", results);
		//		System.out.println(simulationForm.getBaseDate());
		// dateformulaテーブルのデータをログに出力
		// logger.info("[dateformulaテーブルの中身]" + model.getAttribute("dateformula").toString());
		return "simulation";
	}

	/**
	 * 任意の日付計算式を削除する
	 *
	 * @param form 画面フォーム
	 * @param model モデル
	 * @return 表示するテンプレート
	 */
	@PostMapping(value = "/{dateId}")
	public String delete(@PathVariable String dateId, Model model) {
		service.delete(dateId);
		model.addAttribute("simulationForm", new SimulationForm());
		return "simulation";
	}

}
