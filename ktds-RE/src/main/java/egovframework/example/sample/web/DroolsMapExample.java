package egovframework.example.sample.web;

import java.util.HashMap;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class DroolsMapExample {

    public static void execute( KieContainer kc ) throws Exception{
        KieSession ksession = kc.newKieSession("MapExampleKS");

        HashMap<String, Object> user = new HashMap<String, Object>();
        
        user.put("CUST_ACC_NO", "1");
        user.put("CUST_CTG_TYPE_NM", "개인");
        user.put("SEX_TYPE_NM", "남");
        user.put("CUST_AGE", 40);
        user.put("CUST_CLAS_NM", "GOLD");
        
    	ksession.insert(user);
    	ksession.fireAllRules();
    	addScore(user);
        
        ksession.dispose();

    }

    private static void addScore(HashMap<String, Object> rtn){ 
    	System.out.println("--- 결과 ---");
        System.out.println("MATCH : " + (rtn.get("RETURN") == null ? "N" : "Y"));  
        System.out.println("RETURN VALUE: " + rtn.get("RETURN"));
    }  
}  