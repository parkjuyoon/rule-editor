<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
rule "${param.ruleName }"
    no-loop true
    lock-on-active true
    salience 1
    when
        $map : Map(${param.whenMap_html })
    then
        $map.put("RuleName_${param.ruleName }", "Y");
end
