package product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import product.domain.DateFormula;
import product.repository.DateFormulaRepository;

/**
 * 日付計算に関わる処理を行うサービス
 * @author keita
 * 登録、更新はDateFormulaと連携
 * 計算式の取得、削除はDateFormulaRepositoryと連携
 */

@Service
public class CalculationServise {

	/** 日付計算式リポジトリ */
	@Autowired
	private DateFormulaRepository repository;

	/**
	 * 日付計算式の一覧を全件検索して取得する
	 * @return 日付計算式の全件
	 */
	public List<DateFormula> search() {
		return repository.select();
	}

	/**
	 * 日付計算式を取得
	 * @return 日付計算式
	 * PK(Primal Key?)
	 */
	public DateFormula search(String dateId) {
		return repository.selectPK(dateId);
	}

	/**
	 * 日付計算式を登録
	 * @param formula 登録する日付計算式
	 */
	@Transactional
	public void register(DateFormula formula) {
		repository.insert(formula);
	}

	/**
	 * 日付計算式を更新
	 * @param formula 登録する日付計算式
	 */
	@Transactional
	public void update(DateFormula formula) {
		repository.update(formula);
	}

	/**
	 * 日付計算式を削除
	 * @param dateId 削除対象の日付ID
	 */
	@Transactional
	public void delete(String dateId) {
		repository.deletePK(dateId);
	}
}
