package product.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import product.domain.DateFormula;

/**
 * 日付計算式を扱うリポジトリ
 * 実体は{@link DateFormula}とDBテーブル(dateformula)を紐づけるためのORMクラス
 *
 */

@Mapper
public interface DateFormulaRepository {

	/**
	 * 日付計算式の一覧を取得
	 * @return 日付計算式の一覧
	 *
	 */

	@Select("SELECT * FROM dateformula ORDER BY dateId ASC")
	List<DateFormula> select();

	/**
	 * 日付計算式を取得
	 *
	 * @param dateId 日付ID
	 * @return 日付計算式
	 */

	@Select("SELECT * FROM dateformula WHERE dateId = #{dateId}")
	DateFormula selectPK(String dateId);

	/**
	 * 日付計算式を新規登録
	 *
	 * @param formula 登録する日付計算式
	 */

	@Insert("INSERT INTO dateformula VALUES(#{dateId}, #{dateName}, #{adjustmentYear}, #{adjustmentMonth}, #{adjustmentDay})")
	void insert(DateFormula formula);

	/**
	 * 日付計算式を更新
	 *
	 * @param formula 登録する日付計算式
	 *
	 */

	@Update("UPDATE dateformula SET dateName = #{dateName}, adjustmentYear = #{adjustmentYear},"
			+ " adjustmentMonth = #{adjustmentMonth}, adjustmentDay = #{adjustmentDay} WHERE dateId = #{dateId}")
	void update(DateFormula formula);

	/**
	 * 日付計算式を削除
	 *
	 * @param dateId 日付ID
	 */

	@Delete("DELETE FROM dateformula WHERE dateId = #{dateId}")
	void deletePK(String dateId);

}
