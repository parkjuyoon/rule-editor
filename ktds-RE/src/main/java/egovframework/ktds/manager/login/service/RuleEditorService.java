package egovframework.ktds.manager.login.service;

import java.util.HashMap;
import java.util.List;

public interface RuleEditorService {

	List<HashMap<String, Object>> getSchemaInfo(String schema);

	List<String> getColumnByTable(HashMap<String, Object> param);

	List<String> detAttViewGetData(HashMap<String, Object> param);

	String getColumnType(HashMap<String, Object> param);

	HashMap<String, Object> test(String custAccNo);

	void insertRuleInfo(HashMap<String, Object> param);

	void insertDrlInfo(HashMap<String, Object> drl_info);

	int gerLastRuleAddCnt(HashMap<String, Object> param);

	int getRuleNameCheck(HashMap<String, Object> param);

}
