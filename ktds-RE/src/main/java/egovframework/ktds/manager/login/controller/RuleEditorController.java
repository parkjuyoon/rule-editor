package egovframework.ktds.manager.login.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.example.sample.web.DroolsMapExample;
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
	@RequestMapping(value = "/ruleApply.do", method = RequestMethod.POST)
	public void ruleApply(@RequestParam("drl_data") String drl_data) throws Exception {
		
		HashMap<String, Object> user = null;
		
		String filepath = "/ktds-RE/src/main/resources/rules/MapExample.drl";
		String fileName = "";
		
//		File file = new File(filepath + File.separator + fileName);
		
		File file = new File("0.txt");
		
		String path = file.getAbsolutePath();
		
		System.out.println(path);
		
		
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
		
		// KieServices is the factory for all KIE services
		KieServices ks = KieServices.Factory.get();
		
		// From the kie services, a container is created from the classpath
		KieContainer kc = ks.getKieClasspathContainer();
		
		return dse.execute( kc );
	}

}
