package egovframework.ktds.manager.login.controller;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.example.sample.web.DroolsScoreExample;

@Controller
public class LoginController {

	@RequestMapping("/login.do")
	public String login() throws Exception {
		
		DroolsScoreExample dse = new DroolsScoreExample();
		
		// KieServices is the factory for all KIE services
        KieServices ks = KieServices.Factory.get();

        // From the kie services, a container is created from the classpath
        KieContainer kc = ks.getKieClasspathContainer();

        dse.execute( kc );
		
		return "login";
	}
}
