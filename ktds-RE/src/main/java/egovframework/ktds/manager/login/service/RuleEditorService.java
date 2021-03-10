package egovframework.ktds.manager.login.service;

import java.util.HashMap;
import java.util.List;

public interface RuleEditorService {

	List<HashMap<String, Object>> getSchemaInfo(String schema);

	List<String> getColumnByTable(HashMap<String, Object> param);

	List<String> detAttViewGetData(HashMap<String, Object> param);

}
