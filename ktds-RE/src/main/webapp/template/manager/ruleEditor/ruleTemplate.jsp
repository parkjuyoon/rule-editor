<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
rule ${param.ruleName }
    no-loop true
    lock-on-active true
    salience 1
    when
        $map : Map(this["${param.column_name }"]${param.logical_txt }${param.detAttrChkArr }${param.relation_txt })
    then
        System.out.println( 'Y' );
end

