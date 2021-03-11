package egovframework.ktds.manager.login.serviceImpl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.ktds.manager.login.mapper.RuleEditorMapper;
import egovframework.ktds.manager.login.service.RuleEditorService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("ruleEditorService")
public class RuleEditorServiceImpl extends EgovAbstractServiceImpl implements RuleEditorService {

	@Resource(name = "ruleEditorMapper")
	private RuleEditorMapper dao;

	@Override
	public List<HashMap<String, Object>> getSchemaInfo(String schema) {
		return dao.getSchemaInfo(schema);
	}

	@Override
	public List<String> getColumnByTable(HashMap<String, Object> param) {
		return dao.getColumnByTable(param);
	}

	@Override
	public List<String> detAttViewGetData(HashMap<String, Object> param) {
		return dao.detAttViewGetData(param);
	}

	@Override
	public String getColumnType(HashMap<String, Object> param) {
		return dao.getColumnType(param);
	}

}
