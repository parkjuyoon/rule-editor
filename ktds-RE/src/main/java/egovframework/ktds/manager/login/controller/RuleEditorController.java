package egovframework.ktds.manager.login.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.json.Json;

import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.example.sample.web.DroolsMapExample;
import egovframework.ktds.manager.login.service.RuleEditorService;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
	
	@ResponseBody
	@RequestMapping(value = "/detAttViewGetData.do", method = RequestMethod.POST)
	public HashMap<String, Object> detAttViewGetData(@RequestParam("table_name") String table_name,
													 @RequestParam("column_name") String column_name) {
		
		HashMap<String, Object> dataInfo = new HashMap<String, Object>();
		
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("table_name", table_name);
		param.put("column_name", column_name);
		param.put("schema", schema);
		
		List<String> dataList = ruleEditorService.detAttViewGetData(param);
		String column_data_type = ruleEditorService.getColumnType(param);
		
		dataInfo.put("dataList", dataList);
		dataInfo.put("column_data_type", column_data_type);
		
		return dataInfo;
	}
	
	/**
	 * 룰 적용 
	 * @param custAccNo
	 * @param column_name
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "/ruleSave.do", method = RequestMethod.POST)
	public String ruleApply(@RequestBody List<HashMap<String, Object>> applyRuleArr) {
		
		System.out.println(applyRuleArr);
		
//		
//		String filePath = "C:\\work\\appdata\\drl";
//		String fileName = "MapExample.drl";
//		DroolsConfig config = new DroolsConfig();
//		
//		String drlToString = config.getDrlToString(filePath, fileName);
//		
//		System.out.println(drlToString);
//		
//		HashMap<String, Object> user = ruleEditorService.test("AKBL6Y2HQZY");
////		---------------------------
//		
//        KieSession kieSession = new DroolsConfig().getKieSession(filePath + File.separator + fileName);
//        
//        kieSession.insert(user);
//        kieSession.fireAllRules();
//        kieSession.dispose();
//		
//        System.out.println(user);
		
		return "true";
	}
	
	/**
	 * 테스트 url
	 * @param custAccNo
	 * @param column_name
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "/test.do", method = RequestMethod.GET)
	public List<String> test(@RequestParam("CUST_ACC_NO") String custAccNo) throws Exception {
		
		HashMap<String, Object> user = ruleEditorService.test(custAccNo);
		
		DroolsMapExample dse = new DroolsMapExample(user);
		
		return dse.execute();
	}

}
