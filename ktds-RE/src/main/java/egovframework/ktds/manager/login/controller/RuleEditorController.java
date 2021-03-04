package egovframework.ktds.manager.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/ruleEditor")
@Controller
public class RuleEditorController {
	
	@RequestMapping("/main.do")
	public String main() {
		
		return "/ruleEditor/main";
	}

}
