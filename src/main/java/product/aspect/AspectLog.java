/**package product.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectLog {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 *
	 * product内でメソッドが実行される前に
	 * クラスとメソッド名をログに出力する。
	 *
	 * @param jp JoinPoint -> 横断的な処理を埋め込むメソッドの情報
	 *
	 */
/**
	@Before(value = "execution(* product..*(..))")
	public void beforeLog(JoinPoint jp) {
		logger.info("[before aop]start" +
				jp.getTarget().getClass().toString() + "#" +
				jp.getSignature().getName());
	}

	/**
	 *
	 * com.example.demo内でメソッドが実行された後で
	 * 例外が発生しなければ
	 * クラスとメソッド名とをログに出力する。
	 *
	 * @param jp JoinPoint -> 横断的な処理を埋め込むメソッドの情報
	 * @param rtn JoinPoint処理後の戻り値
	 *
	 */
/**
	@AfterReturning(value = "execution(* product..*(..))", returning = "rtn")
	public void afterLog(JoinPoint jp, String rtn) {
		logger.info("[afterReturning aop]end" +
				jp.getTarget().getClass().toString() + "#" +
				jp.getSignature().getName() +
				"return :" + rtn);
	}

}
*/