/**
 * 
 */

$(document).ready(function() {
		
		var applyRuleArr = new Array();	// Rule 속성에 정의된 목록
		var applyRuleObj = {};	// Rule 속성에 추가된 한개의 rule
		var whenMapAttr_html = new Array();
		var ruleAttrArr = new Array();
		var ruleAddCnt = 0; // 룰 추가한 순서를 저장
			
		$('.leftmenutrigger').on('click', function(e) {
			$('.side-nav').toggleClass("open");
			e.preventDefault();
		});
		
/*       
        //[1] 리스트의 기본 모양 지정 : <ul>를 자식으로 가지지 않는 li의 블릿기호는 기본 모양
        $('.attViewTree li:not(:has(ul))').css({ cursor: 'default', 'list-style-image':'url(/common/images/plus.gif)'});
        
        //[2] 자식 요소를 갖는 li에 대해서는 블릿이미지를 plus.gif를 지정
        $('.attViewTree li:has(ul)') //자식 요소(ul)를 갖는 요소(li)에 대해서
            .css({cursor: 'pointer', 'list-style-image':'url(/common/images/plus.gif)'})//+기호로 설정
            .children().hide(); //자식요소 숨기기
        //[3] +로 설정된 항목에 대해서 click이벤트 적용
        $('.attViewTree li:has(ul)').click(function(event){
                       
            //this == event.target으로 현재 선택된 개체에 대해서 처리
            if(this == event.target){
                //숨겨진 상태면 보이고 -기호로 설정 그렇지 않으면 숨기고 + 기호로 설정
                  if ($(this).children().is(':hidden')) {
                    // 보이기
                    $(this).css('list-style-image', 'url(/common/images/minus.gif)').children().slideDown();
                }
                else {
                    // 숨기기
                    $(this).css('list-style-image', 'url(/common/images/plus.gif)').children().slideUp();
                }

            }
            return false;          
        });
*/          

		//[1] 리스트의 기본 모양 지정 : <ul>를 자식으로 가지지 않는 li의 블릿기호는 기본 모양
		$('.attViewTree li:not(:has(ul))').css({ cursor: 'default', 'list-style-image':'url(/common/images/folder-closed.gif)'});
		
		// 속성 VIEW 테이블 클릭이벤트
		$('.attViewTree li').click(function(event){
			var $this = $(this);
			// global변수 값 세팅
			applyRuleObj.table_name = $this.data("table_name");
			applyRuleObj.table_comment = $this.text();
			
			if($this.children().is("ul")) {
				if(this == event.target){
	                //숨겨진 상태면 보이고 -기호로 설정 그렇지 않으면 숨기고 + 기호로 설정
                  	if($this.children().is(':hidden')) {
	                    // 보이기
	                    $this.css('list-style-image', 'url(/common/images/folder.gif)').children().show();
	                }
	                else {
	                    // 숨기기
	                    $this.css('list-style-image', 'url(/common/images/folder-closed.gif)').children().hide();
	                }
	            }
				
				return;
			}
			
			var params = {
				table_name : applyRuleObj.table_name,
				table_comment : applyRuleObj.table_comment
			};
			
			$.ajax({
				url:"/ruleEditor/attViewGetColumn.do",
				type:"POST",
				dataType:"json",
				data:params,
				success:function(res) {
					var html = "";
					html += "<ul data-table_name='"+ params.table_name +"' data-table_comment='"+ params.table_comment +"'>";
					
					for(var i in res.columnList) {
						html += "	<li>";
						html += "		<span data-column_name='"+ res.columnList[i].COLUMN_NAME +"'>";
						html += 			res.columnList[i].COLUMN_COMMENT;
						html += "		</span>";
						html += "	</li>";
					}
					
					html += "</ul>";
					
					$this.append(html);
					$this.css({ cursor: 'default', 'list-style-image':'url(/common/images/folder.gif)'})
					$this.find("li").css({ cursor: 'default', 'list-style-image':'url(/common/images/file.gif)'})
				}
			});
			
		});
		
		// 속성 VIEW 컬럼 클릭 이벤트
		$(document).on("click", ".attViewTree li span", function(){
			var table_name = $(this).closest("ul").data("table_name");
			var table_comment = $(this).closest("ul").data("table_comment");
			var column_name = $(this).data("column_name");
			var column_comment = $(this).text();
			
			applyRuleObj.table_name = table_name;
			applyRuleObj.table_comment = table_comment;
			applyRuleObj.column_name = column_name;
			applyRuleObj.column_comment = column_comment;
					
			$.ajax({
				url:"/ruleEditor/detAttViewGetData.do",
				type:"POST",
				dataType:"json",
				data:applyRuleObj,
				success:function(res) {
					var dataList = res.dataList;
					
					var html = "";
					
					for(var i in dataList) {
						html += "<label class='wd100p'>";
						html += "	<input type='checkbox' name='detAttrChk' value='"+ dataList[i] +"' data-column_data_type='"+ res.column_data_type +"'> ";
						html += 		dataList[i];
						html += "	</input>";
						html += "</label>";
					}
					
					$("#detAttrData").html(html);
				}
			});

			$("#detAttrTable").text(table_comment + " > ");
			$("#detAttrTable").attr("data-table_name", table_name);
			$("#detAttrColumn").text(column_comment);
			$("#detAttrColumn").attr("data-column_name", column_name);
		});
		
		// 속성추가 버튼 클릭 이벤트
		$("#attrAddBtn").click(function() {
			var logical = $("input[name='logicalRadios']:checked").val();
			var logical_txt = $("input[name='logicalRadios']:checked").siblings("span").text();
			var relation = $("input[name='relationRadios']:checked").val();
			var relation_txt = $("input[name='relationRadios']:checked").siblings("span").text();
			
			var detAttrChk = $("input[name='detAttrChk']:checked");
			var column_dataType = detAttrChk.data("column_data_type");
				
			// 관계연산 NONE 뒤에 추가 할 수 없음.
			var relationChk = whenMapAttr_html[whenMapAttr_html.length-1];
			
			if(typeof(relationChk) != "undefined") {
				if(!relationChk.endsWith("&&") && !relationChk.endsWith("||")) {
					alert("관계연산이 끝난 Rule 속성 이후 추가 할 수 없습니다.");
					return;
				}
			}
			
			
			// 논리연산 IN, NOT IN 선택
			var detAttrChk_txt = "";
			
			if(logical == 'logical6' || logical == 'logical7') {
				detAttrChk_txt += "("
					
				for(var i=0; i<detAttrChk.length; i++) {
					detAttrChk_txt += (column_dataType == 'int' ? detAttrChk.eq(i).val() : "\""+ detAttrChk.eq(i).val() +"\"") 
										+ (i+1 == detAttrChk.length ? "" : ", ");
				}
				
				detAttrChk_txt += ")";
			
			// 논리연산 IN, NOT IN 이 아닌 값을 선택시
			} else {
				if(detAttrChk.length > 1) {
					alert("상세 속성을 한 가지만 선택하세요.");
					return;
				}
				
				detAttrChk_txt = (column_dataType == 'int' ? detAttrChk.val() : "\""+ detAttrChk.val() +"\"");
			}
			
			if(relation == 'relation3') {
				relation_txt = "";
			}
			
			var ruleAttr_txt = "["+ applyRuleObj.table_comment + " : " + applyRuleObj.column_comment + "] " + logical_txt + detAttrChk_txt + " " + relation_txt;			
			
			var html = "";
			html += "<label class='wd100p'>";
			html += "	<img src='/common/images/minus.gif' alt='' class='_ruleAttrMinus'>&nbsp";
			html += "	<span>";
			html += 		ruleAttr_txt;
			html += "	</span>";
			html += "</label>";
			
			$("#ruleAttrData").append(html);
			
			// ---------------- applyRuleObj 값 저장 ----------------
			applyRuleObj.logical = logical;
			applyRuleObj.relation = relation;
			applyRuleObj.logical_txt = logical_txt;
			
			if(relation == 'relation1') {
				applyRuleObj.relation_txt = "&&"
			} else if(relation == 'relation2') {
				applyRuleObj.relation_txt = "||"
			} else {
				applyRuleObj.relation_txt = "";
			}
			
			var detAttrChkArr = new Array();
			
			detAttrChk.each(function() {
				detAttrChkArr.push($(this).val());
			});
			
			applyRuleObj.detAttrChkArr = detAttrChkArr;
			applyRuleObj.detAttrChk_txt = detAttrChk_txt;
			
			// when(Map Object) 구문 생성
			var whenMap_html = whenGenerator(applyRuleObj);
			whenMapAttr_html.push(whenMap_html);
			applyRuleObj.whenMapAttr_html = whenMapAttr_html;
			// ---------------- applyRuleObj 값 저장 ----------------
		});
		
		// Rule 속성 minus 버튼 클릭 이벤트
		$(document).on("click", "._ruleAttrMinus", function() {
			var delIdx = $("._ruleAttrMinus").index(this);
		
			whenMapAttr_html.splice(delIdx, 1);
			
			$(this).closest("label").remove();
		});
		
		// Rule 추가 버튼 이벤트
		$("#ruleAddBtn").click(function() {
			var ruleName = $("#ruleName").val();
			
			if(ruleName == '') {
				alert("Rule Name을 입력하세요.");
				return;
			}
			
			applyRuleObj.ruleName = $("#ruleName").val();
			
			// Rule 옵션 추가
			var opt1 = $("input[name='opt1']:checked").val();
			var opt2 = $("input[name='opt2']:checked").val();
			var opt3 = $("input[name='opt3']").val() == "" ? 1 : $("input[name='opt3']").val();
			
			applyRuleObj.opt1 = opt1;
			applyRuleObj.opt2 = opt2;
			applyRuleObj.opt3 = opt3;
			applyRuleObj.ruleAddCnt = ++ruleAddCnt; // 룰 추가한 순서 증가
			
			// rule drl 생성
			var ruleGenStr = ruleGenerator(applyRuleObj);

			if("-1" == ruleGenStr) {
				alert("룰 속성 정의가 올바르지 않습니다.\n마지막 속성은 NONE으로 설정하세요.");
				--ruleAddCnt; // 실패시 룰 추가한 순서 감소
				return;
				
			} else if("" == ruleGenStr) {
				alert("룰 속성을 먼저 추가 하세요.");
				--ruleAddCnt; // 실패시 룰 추가한 순서 감소
				return;
			}
			
			ruleAttrArr.push(ruleGenStr); // (/js/manager/ruleEditor/drlGenerator.js)
			$("#ruleAttCnt").text(ruleAttrArr.length);
			
			initObj();
			
		});
		
		// generate 버튼 클릭 이벤트
		$("#drlGenBtn").click(function() {
			applyRuleObj.ruleAttrArr = ruleAttrArr;
			
			var drl_html = drlGenerator(applyRuleObj);	// (/js/manager/ruleEditor/drlGenerator.js)
			
			$("#drlGenData").html(drl_html);
		});
		
		function initObj() {
			$("#ruleAttrData").html("");
			$("#ruleName").val("");
			$("#detAttrData").html("");
			$("#detAttrTable").text("속성을 선택하세요.");
			$("#detAttrColumn").text("");
			applyRuleObj =  {};
			whenMapAttr_html = [];
		}
	});
