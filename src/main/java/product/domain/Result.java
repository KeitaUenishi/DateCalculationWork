package product.domain;

import java.util.Arrays;
import java.util.StringJoiner;

/**
 * 画面用の計算結果オブジェクト
 * 一覧画面の計算結果の行を表現する
 *
 * @author keita
 *
 */
public class Result {

	/** 日付計算式 */
	private DateFormula formula;
	/** 計算結果 */
	private String calculated;

	/**
	 * コンストラクタ
	 * コンストラクタとは「このクラスがnewされた直後に自動的に実行される」メソッド
	 * @param formula 日付計算式
	 *
	 * this 特別に準備された変数で、「自分自身のインスタンス」を意味している。
	 * 「自分自身のインスタンスのformulaフィールドにformulaを代入」という意味になる。
	 */
	public Result(DateFormula formula) {
		this.formula = formula;
	}

	/** {@link DateFormula#getDateId()} */
	public String getDateId() {
		return formula.getDateId();
	}

	/** {@link DateFormula#getDateName()} */
	public String getDateName() {
		return formula.getDateName();
	}

	/**
	 * 日付計算式を取得
	 *
	 * @return 計算結果
	 */
	public DateFormula getFormula() {
		return formula;
	}

	/**
	 * 計算結果を取得
	 *
	 * @return
	 */

	public String getCalculated() {
		return calculated;
	}

	/**
	 * 計算結果を設定
	 *
	 * @param calculated 計算結果
	 */

	public void setCalculated(String calculated) {
		this.calculated = calculated;
	}

	/**
	 * 年月日順に区切られた計算式を取得
	 * 画面用に「 / 」区切りで加減値をそれぞれ年月日順で表示する。
	 *
	 * @return 年月日順に区切られた計算式
	 *
	 */

	public String getYmdFormula() {
		int[] ymdFormula = { formula.getAdjustmentYear(), formula.getAdjustmentMonth(), formula.getAdjustmentDay() };
		StringJoiner joiner = new StringJoiner(" / ");
		// ラムダ式
		Arrays.stream(ymdFormula).forEach(i -> joiner.add(String.valueOf(i)));
		return joiner.toString();
	}
}
