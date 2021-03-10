/**
 * Rule Add 할 때 개별 Rule when문장 생성
 */
var header = "";
header += "package rule";
header += "<br>";
header += "import java.util.Map";

function whenGenerator(applyRuleObj) {
	var returnVal = "";
	
	$.ajax({
		url : "/template/manager/ruleEditor/whenTemplate.jsp",
		dataType : "html",
		type :"POST",
		data : applyRuleObj,
		async:false	// ajax 통신 완료 후 return 해주기 위해 비동기 false
		
	}).done(function(res) {
		returnVal = res;
		
	}).fail(function(jqXHR, textStatus, errorThrown) {
		console.log("에러")
		console.log(jqXHR)
		console.log(textStatus)
		console.log(errorThrown)
	});
	
	return returnVal;
}

function ruleGenerator(applyRuleObj) {
	var returnVal = "";
	
	$.ajax({
		url : "/template/manager/ruleEditor/ruleTemplate.jsp",
		dataType : "html",
		type :"POST",
		data : applyRuleObj,
		async:false	// ajax 통신 완료 후 return 해주기 위해 비동기 false
		
	}).done(function(res) {
		returnVal = res;
		
	}).fail(function(jqXHR, textStatus, errorThrown) {
		console.log("에러")
		console.log(jqXHR)
		console.log(textStatus)
		console.log(errorThrown)
	});
	
	return returnVal;
}

/**
 * Rule Generate 할 때 최종 DRL 파일 내용 생성
 */
function drlGenerator(applyRuleArr) {
	var returnVal = "";
	var drl_html = "";
	
	for(var i in applyRuleArr) {
		drl_html += applyRuleArr[i];
	}
	
	var paramObj = {
			header : header,
			drl_html : drl_html
	};
	
	$.ajax({
		url : "/template/manager/ruleEditor/drlTemplate.jsp",
		dataType : "html",
		type :"POST",
		data : paramObj,
		async:false	// ajax 통신 완료 후 return 해주기 위해 비동기 false
		
	}).done(function(res) {
		returnVal = res;
		
	}).fail(function(jqXHR, textStatus, errorThrown) {
		console.log("에러")
		console.log(jqXHR)
		console.log(textStatus)
		console.log(errorThrown)
	});
	
	return returnVal;
}