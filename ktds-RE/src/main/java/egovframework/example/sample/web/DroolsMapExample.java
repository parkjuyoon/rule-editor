package egovframework.example.sample.web;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class DroolsMapExample {

    public static void execute( KieContainer kc ) throws Exception{
        KieSession ksession = kc.newKieSession("MapExampleKS");

        HashMap<String, Object> user = new HashMap<String, Object>();
        
        user.put("CUST_ACC_NO", "5CF4DNCR2HN");
        user.put("CUST_CTG_TYPE_NM", "개인");
        user.put("SEX_TYPE_NM", "여자");
        user.put("CUST_AGE", 24);
        user.put("CUST_CLAS_NM", "Gold");
        user.put("SMPH_USE_YN", "Y");
        user.put("NPAY_YN", "N");
        
    	ksession.insert(user);
    	ksession.fireAllRules();
    	print(user);
        
        ksession.dispose();

    }

    private static void print(HashMap<String, Object> rtn){ 
    	System.out.println("--- 결과(Map) ---");
    	System.out.println(rtn);
    	
    	Set<String> set = rtn.keySet();
    	Iterator<String> iter = set.iterator();
    	
    	while(iter.hasNext()) {
    		String key = iter.next();
    		Object value = rtn.get(key);

    		if(key.contains("RuleName_")) {
    			String strKey = key.replace("RuleName_", "");
    			System.out.println(strKey + " : " + value);
    		}
    	}
    }  
}  