/**
 * 
 */

$(document).ready(function() {
	var pageNum = 1;
	
	getRuleList(pageNum);
	
	$(document).on("click", "#paging .pagination a", function() {
		var pageNum = $(this).attr("data-pageNum");
		
		getRuleList(pageNum);
		
	});
	
});

// 룰 리스트 조회
function getRuleList(pageNum) {
	var start = pageNum*10 - 10;
	var limit = 10;
	
	var pageInfo = {};
	pageInfo.start = start;
	pageInfo.limit = limit;
	
	$.ajax({
		url : "/ruleManager/getRuleList.do",
		dataType : "json",
		type :"POST",
		data : pageInfo,
		async:false	// ajax 통신 완료 후 return 해주기 위해 비동기 false
		
	}).done(function(res) {
		var ruleList = res.ruleList;
		var totalCnt = res.totalCnt;
		
		var html = "";
		for(var i in ruleList) {
			html += "<tr>";
			html += "	<td>"+ ruleList[i].CUST_ACC_NO +"</td>";
			html += "	<td>"+ ruleList[i].SEX_TYPE_NM +"</td>";
			html += "	<td>"+ ruleList[i].CUST_CTG_TYPE_NM +"</td>";
			html += "	<td>"+ ruleList[i].INDV_INFO_COLEC_USE_AGREE_YN +"</td>";
			html += "	<td>"+ ruleList[i].SMPH_USE_YN +"</td>";
			html += "	<td>"+ ruleList[i].CUST_CLAS_NM +"</td>";
			html += "	<td>"+ ruleList[i].EC_CUST_YN +"</td>";
			html += "</tr>";
		}
		
		$("#ruleList").html(html);
		paging(totalCnt, pageNum);
		
	}).fail(function(jqXHR, textStatus, errorThrown) {
		console.log("에러")
		console.log(jqXHR)
		console.log(textStatus)
		console.log(errorThrown)
	});
}
