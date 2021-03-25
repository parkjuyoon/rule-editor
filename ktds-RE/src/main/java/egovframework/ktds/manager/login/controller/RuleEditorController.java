package egovframework.ktds.manager.login.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.example.sample.web.DroolsMapExample;
import egovframework.ktds.drools.config.DroolsUtil;
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
	 * 룰 저장 (파일 및 DB) 
	 * @param custAccNo
	 * @param column_name
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "/ruleSave.do", method = RequestMethod.POST)
	public String ruleApply(@RequestBody HashMap<String, Object> map) {
		
		// 저장될 DRL 파일 컨텐츠
		String drl_contents = (String) map.get("drl_html");
		// DB에 저장하기 위한 세부 정보
		List<HashMap<String, Object>> applyRuleArr = (List<HashMap<String, Object>>) map.get("applyRuleArr");
			
		// 값 받아올 수 있게 변경 필요 -----------------------------------
		String service_id = "SER_01";
		String root_path = "C:\\work\\appdata";
		String package_nm = "rule";
		String package_comment = "룰패키지";
		String drl_file_nm = "customerRule.drl";
		String drl_file_comment = "고객룰DRL";
		String reg_user_id = "user0001";
		// 값 받아올 수 있게 변경 필요 -----------------------------------
		
		// DRL_INFO 테이블에 저장될 데이터
		HashMap<String, Object> drl_info = new HashMap<>();
		drl_info.put("schema", schema);
		drl_info.put("SERVICE_ID", service_id);
		drl_info.put("ROOT_PATH", root_path);
		drl_info.put("PACKAGE_NM", package_nm);
		drl_info.put("PACKAGE_COMMNET", package_comment);
		drl_info.put("DRL_FILE_NM", drl_file_nm);
		drl_info.put("DRL_FILE_COMMENT", drl_file_comment);
		drl_info.put("REG_USER_ID", reg_user_id);

		// RULE_INFO 테이블에 저장될 데이터
		List<HashMap<String, Object>> rules_info = new ArrayList<HashMap<String,Object>>();
		for(HashMap m : applyRuleArr) {
			HashMap<String, Object> rule_info = new HashMap<>();
			
			String rule_name = (String) m.get("ruleName");
			int rule_add_cnt = (Integer) m.get("ruleAddCnt");
			String no_loop = (String) m.get("opt1");
			String lock_on_active = (String) m.get("opt2");
			String agenda_group = "";
			int salience = Integer.parseInt((String) m.get("opt3"));
			String enabled = "";
			String duration = "";
			String contents = (String) m.get("whenMap_html");
			
			rule_info.put("RULE_NAME", rule_name);
			rule_info.put("RULE_ADD_CNT", rule_add_cnt);
			rule_info.put("NO_LOOP", no_loop);
			rule_info.put("LOCK_ON_ACTIVE", lock_on_active);
			rule_info.put("AGENDA_GROUP", null);
			rule_info.put("SALIANCE", salience);
			rule_info.put("ENABLED", null);
			rule_info.put("DURATION", null);
			rule_info.put("CONTENTS", contents);
			
			rules_info.add(rule_info);
		}
		
		// DB 저장
		HashMap<String, Object> param = new HashMap<>();
		param.put("schema", schema);
		param.put("RULES_INFO", rules_info);
		
		// DRL_INFO 저장
		ruleEditorService.insertDrlInfo(drl_info);
		param.put("DRL_INFO_SEQ", (Integer) drl_info.get("DRL_INFO_SEQ"));
		// RULE_INFO 저장
		ruleEditorService.insertRuleInfo(param);
		
		// DRL 파일 output
		DroolsUtil.outputDrl(root_path, package_nm, drl_file_nm, drl_contents);
		
		
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
