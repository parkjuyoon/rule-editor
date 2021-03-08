package egovframework.ktds.manager.login.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.ktds.manager.login.service.RuleEditorService;

@RequestMapping("/ruleEditor")
@Controller
public class RuleEditorController {
	
	final static String schema = "rule_editor";
	
	@Resource(name = "ruleEditorService")
	protected RuleEditorService ruleEditorService;
	
	@RequestMapping(value = "/main.do")
	public String main(ModelMap model) {
		
		List<HashMap<String, Object>> tableList = ruleEditorService.getSchemaInfo(schema);
		
		model.addAttribute("tableList", tableList);
		
		return "/ruleEditor/main";
	}
	
	@ResponseBody
	@RequestMapping(value = "/attViewGetColumn.do", method = RequestMethod.POST)
	public HashMap<String, Object> attViewGetColumn(@RequestParam("table_name") String table_name) {
		
		HashMap<String, Object> columnInfo = new HashMap<String, Object>();
		
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("table_name", table_name);
		param.put("schema", schema);

		List<String> columnList = ruleEditorService.getColumnByTable(param);
		
		columnInfo.put("columnList", columnList);
		
		return columnInfo;
	}

}
