package egovframework.example.sample.web;

import java.util.HashMap;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class DroolsMapExample {

    public static void execute( KieContainer kc ) throws Exception{
        KieSession ksession = kc.newKieSession("MapExampleKS");

        
        HashMap<String, Object> order = new HashMap<String, Object>();
        HashMap<String, Object> user = new HashMap<String, Object>();
        
        user.put("name", "Name1");
        order.put("amout", "250");
        order.put("score", "111");
        order.put("user", user);
        
    	ksession.insert(order);
    	ksession.fireAllRules();
    	addScore(order);
        
        ksession.dispose();

    }

    private static void addScore(HashMap<String, Object> o){  
//        System.out.println("name : " + o.getUser().getName() + " score : " + o.getScore());  
        System.out.println("name : " + ((HashMap<String, Object>)o.get("user")).get("name") + " score : " + o.get("score"));  
    }  
}  