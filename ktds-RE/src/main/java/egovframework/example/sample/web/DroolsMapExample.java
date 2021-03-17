package egovframework.example.sample.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
    
    @SuppressWarnings("rawtypes")
	private static void print(HashMap<String, Object> rtn){ 
    	Iterator<String> iter = rtn.keySet().iterator();
    	HashMap<String, Object> resultMap = new HashMap<>();
    	
    	while(iter.hasNext()) {
    		String key = iter.next();
    		Object value = rtn.get(key);

    		if(key.contains("RuleName_")) {
    			resultMap.put(key, value);
    		}
    	}
    	
    	Iterator it = sortByValue(resultMap).iterator();
    	
    	while(it.hasNext()) {
    		String key = (String) it.next();
            System.out.println(key.replace("RuleName_", "") + " = Y");
    	}
    }  
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static List sortByValue(final Map map) {
        List<String> list = new ArrayList();
        list.addAll(map.keySet());

        Collections.sort(list,new Comparator() {
            public int compare(Object o1,Object o2) {
                Object v1 = map.get(o1);
                Object v2 = map.get(o2);

                return ((Comparable) v2).compareTo(v1);

            }
        });

        Collections.reverse(list); // 주석시 오름차순

        return list;
    }
}  