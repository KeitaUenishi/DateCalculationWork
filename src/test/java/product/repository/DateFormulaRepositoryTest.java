package product.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import product.domain.DateFormula;

@RunWith(SpringRunner.class)
@MybatisTest
@TestPropertySource(locations = "classpath:test.properties")
public class DateFormulaRepositoryTest {

	// sut テスト対象システム
	@Autowired
	private DateFormulaRepository sut;

	@Test
	public void 検索_全件して結果をリストで取得できること() throws Exception {
		List<DateFormula> acutual = sut.select();

		/**
		 * assertThatの引数に、検証したい対象の値を渡す assertThat(リスト(acutual)の要素数(.size()))
		 * 続けてメソッドチェーンを利用して期待する値を宣言する（isEqualTo(期待する値)）
		 */
		assertThat(acutual.size()).isEqualTo(2);
	}

	@Test
	public void 検索_1件して結果がキーに紐づく1件だけ取得できること() throws Exception {
		DateFormula actual = sut.selectPK("Y01");

		assertThat(actual.getDateId()).isEqualTo("Y01");
		assertThat(actual.getDateName()).isEqualTo("翌年");
		assertThat(actual.getAdjustmentYear()).isEqualTo(1);
		assertThat(actual.getAdjustmentMonth()).isEqualTo(0);
		assertThat(actual.getAdjustmentDay()).isEqualTo(0);
	}

	@Test
	public void 存在しないデータを検索すると結果がNULLとなること() throws Exception {
		DateFormula actual = sut.selectPK("EmptyData");

		assertThat(actual).isNull();
	}

	@Test
	public void NULLで検索すると結果がNULLになること() throws Exception {
		DateFormula actual = sut.selectPK(null);

		assertThat(actual).isNull();
	}

	@Test
	public void キーに紐づく1件の更新ができること() throws Exception {
		DateFormula formula = createFormula("Y01", "翌々年", 2, 0, 0);

		sut.update(formula);
		DateFormula actual = sut.selectPK("Y01");

		assertThat(actual.getDateId()).isEqualTo("Y01");
		assertThat(actual.getDateName()).isEqualTo("翌々年");
		assertThat(actual.getAdjustmentYear()).isEqualTo(2);
		assertThat(actual.getAdjustmentMonth()).isEqualTo(0);
		assertThat(actual.getAdjustmentDay()).isEqualTo(0);
	}

	@Test
	public void キーに紐づく1件の削除ができること() throws Exception {
		sut.deletePK("Y01");
		List<DateFormula> actual = sut.select();

		assertThat(actual.size()).isEqualTo(1);
	}

	/**
	 * @param DuplicateKeyException
	 * データを挿入または更新しようとすると、主キーまたは一意制約に違反した場合にスローされる例外。
	 * assertThatThrownBy() で例外の検証ができる。
	 */

	@Test
	public void 既に存在するキーで登録しようとするとDuplicateKeyExceptionとなること() throws Exception {
		DateFormula formula = createFormula("Y01", "翌々年", 2, 0, 0);

		assertThatThrownBy(() -> {
			sut.insert(formula);
		}).isInstanceOf(DuplicateKeyException.class);
	}

	/**
	 * DataIntegrityViolationException
	 * データを挿入または更新しようとすると整合性制約に違反した場合にスローされる例外。
	 * これは純粋にリレーショナルな概念ではないことに注意してください。ほとんどのデータベースタイプでは、一意のプライマリキーが必要です。
	 */

	@Test
	public void NULLで登録しようとするとDataIntegrityViolationExceptionとなること() throws Exception {
		assertThatThrownBy(() -> {
			sut.insert(null);
		}).isInstanceOf(DataIntegrityViolationException.class);
	}

	// 日付計算式の値をセットする
	private DateFormula createFormula(String 日付ID, String 日付名, int 加減年, int 加減月, int 加減日) {
		DateFormula formula = new DateFormula();
		formula.setDateId(日付ID);
		formula.setDateName(日付名);
		formula.setAdjustmentYear(加減年);
		formula.setAdjustmentMonth(加減月);
		formula.setAdjustmentDay(加減日);
		return formula;
	}
}
