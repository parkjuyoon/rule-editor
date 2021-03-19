package egovframework.ktds.manager.login.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.ktds.manager.login.service.RuleManagerService;

@RequestMapping("/ruleManager")
@Controller
public class RuleManagerController {
	
	@Resource(name = "ruleManagerService")
	protected RuleManagerService ruleManagerService;

	@RequestMapping("/ruleListView.do")
	public String ruleListView (ModelMap model) {
		
		HashMap<String, Object> param = new HashMap<String, Object>();
		
		HashMap<String, Object> pageInfo = new HashMap<String, Object>();
		pageInfo.put("start", 0);
		pageInfo.put("end", 9);
		
		param.put("pageInfo", pageInfo);
		
		List<HashMap<String, Object>> ruleList = ruleManagerService.getRuleList(param);
		int totalCnt = ruleManagerService.getRuleListTotalCnt(param);
		
		model.put("ruleList", ruleList);
		model.put("totalCnt", totalCnt);
		
		return "/ruleList/listView";
	}
	
}
