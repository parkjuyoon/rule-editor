package egovframework.ktds.manager.login.mapper;

import java.util.HashMap;
import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("ruleEditorMapper")
public interface RuleEditorMapper {

	public List<HashMap<String, Object>> getSchemaInfo(String schema);

	public List<String> getColumnByTable(HashMap<String, Object> param);

}
