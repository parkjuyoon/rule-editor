/**
 * 
 */

function paging(totalCnt, curPage) {	// totalCnt:데이터 총 개수, curPage:선택된 페이지
	var perPage = 10; // 한화면에 나타날 페이지 단위
	var pageCnt = Math.ceil(totalCnt / perPage); // 개수에 따른 실제 페이지 개수
	var first = 0;
	var last = 0;
	
	var pageInfo = {};
	pageInfo.pageCnt = pageCnt;
	pageInfo.curPage = curPage;
	
	$.ajax({
		url : "/template/manager/pageTemplate.jsp",
		dataType : "html",
		type :"POST",
		data : pageInfo,
		async:false	// ajax 통신 완료 후 return 해주기 위해 비동기 false
		
	}).done(function(res) {
		$("#paging").html(res);
		
	}).fail(function(jqXHR, textStatus, errorThrown) {
		console.log("에러")
		console.log(jqXHR)
		console.log(textStatus)
		console.log(errorThrown)
	});
}