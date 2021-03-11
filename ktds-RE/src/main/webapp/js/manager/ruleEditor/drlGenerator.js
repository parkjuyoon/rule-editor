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
	
	var whenMapArr_html = applyRuleObj.whenMapAttr_html;
	var whenMap_html = "";
	
	for(var i in whenMapArr_html) {
		whenMap_html += whenMapArr_html[i];
	}
	
	applyRuleObj.whenMap_html = whenMap_html;
	
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
function drlGenerator(applyRuleObj) {
	var ruleAttrArr = applyRuleObj.ruleAttrArr;
	var ruleAttr = "";
	
	for(var i in ruleAttrArr) {
		ruleAttr += ruleAttrArr[i];
	}
	
	var returnVal = "";
	
	var paramObj = {
			header : header,
			ruleAttr : ruleAttr
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