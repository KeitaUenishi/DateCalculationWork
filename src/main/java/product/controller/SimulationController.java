package product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SimulationController {

	@GetMapping("/")
	public String index() {
		return "simulation";
	}

}
