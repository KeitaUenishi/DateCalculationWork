package product.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import product.service.CalculationService;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class SimulationControllerTest {

	private MockMvc sut;

	@MockBean
	private CalculationService service;

	@Autowired
	private SimulationController target;

	@Before
	public void setUp() throws Exception {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("classpath:templates/");
		viewResolver.setSuffix(".html");

		sut = MockMvcBuilders.standaloneSetup(target).setViewResolvers(viewResolver).build();
	}

	/**
	 * ・mockMvc(sut)のperformを使ってリクエストを実行する
	 * mockMvc.perform(メソッド名("指定のurl"))
	 *
	 * ・続いてandExceptメソッドでレスポンスのテストを行う（ステータスコード200は status().isOk でテストできる
	 * .andExpect(テスト項目)
	 *
	 * ・指定のViewを返すか？
	 * .andExpect(view().name(テンプレート名))
	 *
	 *
	 */

	@Test
	public void シミュレーションページのリクエスト結果が正常となりViewとしてsimulationが返ること() throws Exception {
		sut.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(view().name("simulation"));
	}

	@Test
	public void シミュレーションページで計算基準日を入力して計算実行を押すと計算サービスが呼ばれていること() throws Exception {
		// param("simulation.htmlから渡されるbaseDate", "入力された計算基準日")
		sut.perform(post("/").param("baseDate", "20181201"))
				.andExpect(status().isOk())
				.andExpect(view().name("simulation"));

		System.out.println("service.search()は1回呼ばれました");
		verify(service, times(1)).search();
	}

	@Test
	public void シミュレーションページで削除処理を行うとサービスで処理されて同一画面に遷移されること() throws Exception {
		sut.perform(post("/{dateId}", "Y01"))
				.andExpect(status().isOk())
				.andExpect(view().name("simulation"));
		/**
		 * ・Mock化したメソッドの呼び出し回数を確認する
		 * verify((Mock化したオブジェクト名), times(呼び出し回数)).呼出メソッド名(呼び出しメソッドの引数)
		 *
		 * テスト対象メソッドを実行した結果、service.delete("Y01")が1回呼ばれたことを確認
		 */
		verify(service, times(1)).delete("Y01");
		System.out.println("servise.delete(Y01)は1回呼ばれました");
	}

	@Test
	public void シミュレーションページで計算基準日をNULLにして計算実行を押すと例外情報が入った状態で画面が返ること() throws Exception {
		sut.perform(post("/"))
				.andExpect(status().isOk())
				.andExpect(model().hasErrors())
				.andExpect(view().name("simulation"));
	}

	@Test
	public void シミュレーションページで計算基準日を空にして計算実行を押すと例外情報が入った状態で画面が返ること() throws Exception {
		sut.perform(post("/").param("basedate", ""))
				.andExpect(status().isOk())
				.andExpect(model().hasErrors())
				.andExpect(view().name("simulation"));
	}

	@Test
	public void シミュレーションページで計算基準日に空白を入れて計算実行を押すと例外情報が入った状態で画面が返ること() throws Exception {
		sut.perform(post("/").param("basedate", " "))
				.andExpect(status().isOk())
				.andExpect(model().hasErrors())
				.andExpect(view().name("simulation"));
	}

	@Test
	public void シミュレーションページで計算基準日に不正な値を入れて計算実行を押すと例外情報が入った状態で画面が返ること() throws Exception {
		sut.perform(post("/").param("basedate", "2018/12/01"))
				.andExpect(status().isOk())
				.andExpect(model().hasErrors())
				.andExpect(view().name("simulation"));
	}

}