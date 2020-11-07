package product.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 日付計算式を表現するオブジェクト
 */
public class DateFormula {

	@NotBlank
	@Size(min = 1, max = 6)
	private String dateId;

	@NotBlank
	@Size(min = 1, max = 32)
	private String dateName;

	private int adjustmentYear;

	private int adjustmentMonth;

	private int adjustmentDay;

	/**
	 * インスタンスの名前の出力
	 * String型を返す 引数はない。メソッド内で加工する変数はない
	 * @return
	 */
	public String getDateId() {
		return dateId;
	}

	/**
	 * インスタンスの名前の設定
	 * 左のdateIdは、インスタンスの要素のひとつとしてのdateId
	 * 右のdateIdは、「public void setDateId(String dateId)」の中のdateId
	 */
	public void setDateId(String dateId) {
		this.dateId = dateId;
	}

	public String getDateName() {
		return dateName;
	}

	public void setDateName(String dateName) {
		this.dateName = dateName;
	}

	public int getAdjustmentYear() {
		return adjustmentYear;
	}

	public void setAdjustmentYear(int adjustmentYear) {
		this.adjustmentYear = adjustmentYear;
	}

	public int getAdjustmentMonth() {
		return adjustmentMonth;
	}

	public void setAdjustmentMonth(int adjustmentMonth) {
		this.adjustmentMonth = adjustmentMonth;
	}

	public int getAdjustmentDay() {
		return adjustmentDay;
	}

	public void setAdjustmentDay(int adjustmentDay) {
		this.adjustmentDay = adjustmentDay;
	}
}
