<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<title>permission manager</title>
<jsp:include page="_menu.jsp" />
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
    <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
</head>
<body>
	<sec:authentication property="principal.authorities"
						var="authorities" />
					<c:forEach items="${authorities}" var="authority" varStatus="vs">

						<c:if test="${authority.authority ne  '1'}">
							<c:redirect url = "${pageContext.request.contextPath}/403"/>
						</c:if>
					</c:forEach>
	<div class="container">
	
		<div >
		
			<div style="float: left;">
				<table width="250px">
				 
					<tr>
						<td height="37px"></td>
					</tr>
					<tr>
						<td height="37px">OverView</td>
					</tr>
					<tr>
						<td height="37px">Project detail</td>
					</tr>
					<tr>
						<td height="37px">Add project	</td>
					</tr>
					<tr>
						<td height="37px">Setting - Update project info</td>
					</tr>
					<tr>
						<td height="37px">Setting - resource</td>
					</tr>
					<tr>
						<td height="37px">Create issue</td>
					</tr>
					<tr>
						<td height="37px">Update issue</td>
					</tr>
					<tr>
						<td height="37px">Setting - Register milestone</td>
					</tr>
					<tr>
						<td height="37px">Effort management</td>
					</tr>
					<tr>
						<td height="37px">Effort canculate</td>
					</tr>
					<tr>
						<td height="37px">Q&A register-update</td>
					</tr>
					<tr>
						<td height="37px">Timesheet register-update</td>
					</tr>
					<tr>
						<td height="37px">User register</td>
					</tr>
					<tr>
						<td height="37px">Issue list</td>
					</tr>
					<tr>
						<td height="37px">Timesheet list</td>
					</tr>
					<tr>
						<td height="37px">Q&A list</td>
					</tr>
					
				</table>
				
				
			</div>
			<c:forEach var="per" items="${listPer}" varStatus="status">
				<div style="display:inline-block">
					
					<table>
				 
					<tr>
						<td height="37px" width="70px">
							<c:if test="${per.ROLE_ID==2}">Manager</c:if>
							<c:if test="${per.ROLE_ID==3}">Developer</c:if>
							<c:if test="${per.ROLE_ID==4}">Tester</c:if>
							<c:if test="${per.ROLE_ID==5}">Reporter</c:if>
							<c:if test="${per.ROLE_ID==6}">Customer</c:if>
						</td>
					</tr>
					<tr>
						<td height="37px">
						<input type="checkbox" name="over_view"  <c:if test="${per.over_view==true}">checked="checked" </c:if>  <c:if test="${per.ROLE_ID==6}">style="display:none" </c:if> />
						</td>
					</tr>
					<tr>
						<td height="37px">
						<input type="checkbox" name="pro_detail"   <c:if test="${per.pro_detail==true}">checked="checked" </c:if> <c:if test="${per.ROLE_ID==6}">style="display:none" </c:if> />
						</td>
					</tr>
					<tr>
						<td height="37px"><input type="checkbox" name="add_pro" <c:if test="${per.add_pro==true}">checked="checked" </c:if> <c:if test="${per.ROLE_ID==3||per.ROLE_ID==4||per.ROLE_ID==5||per.ROLE_ID==6}">style="display:none" </c:if> />
					</tr>
					<tr>
						<td height="37px"><input type="checkbox" name="set_upd"  <c:if test="${per.set_upd==true}">checked="checked" </c:if> <c:if test="${per.ROLE_ID==3||per.ROLE_ID==4||per.ROLE_ID==5||per.ROLE_ID==6}">style="display:none" </c:if> />
					</tr>
					<tr>
						<td height="37px"><input type="checkbox" name="set_res" <c:if test="${per.set_res==true}">checked="checked" </c:if> <c:if test="${per.ROLE_ID==3||per.ROLE_ID==4||per.ROLE_ID==5||per.ROLE_ID==6}">style="display:none" </c:if> />
					</tr>
					<tr>
						<td height="37px">
						<input type="checkbox" name="cre_iss" <c:if test="${per.cre_iss==true}">checked="checked" </c:if> <c:if test="${per.ROLE_ID==6}">style="display:none" </c:if> />

						
						</td>
					</tr>
					<tr>
						<td height="37px">
						<input type="checkbox" name="upd_iss" <c:if test="${per.upd_iss==true}">checked="checked" </c:if> <c:if test="${per.ROLE_ID==6}">style="display:none"</c:if> />
						
						</td>
					</tr>
					<tr>
						<td height="37px"><input type="checkbox" name="set_reg" <c:if test="${per.set_reg==true}">checked="checked" </c:if> <c:if test="${per.ROLE_ID==3||per.ROLE_ID==4||per.ROLE_ID==5||per.ROLE_ID==6}">style="display:none"</c:if> />
					</tr>
					<tr>
						<td height="37px"><input type="checkbox" name="eff_mana" <c:if test="${per.eff_mana==true}">checked="checked" </c:if> <c:if test="${per.ROLE_ID==3||per.ROLE_ID==4||per.ROLE_ID==5||per.ROLE_ID==6}">style="display:none"</c:if> />
					</tr>
					<tr>
						<td height="37px"><input type="checkbox" name="eff_can" <c:if test="${per.eff_can==true}">checked="checked" </c:if> <c:if test="${per.ROLE_ID==3||per.ROLE_ID==4||per.ROLE_ID==5||per.ROLE_ID==6}">style="display:none"</c:if> />
					</tr>
					<tr>
						<td height="37px">
						<input type="checkbox" name="qva_upd" <c:if test="${per.qva_upd==true}">checked="checked"</c:if>/>

						</td>
					</tr>
					<tr>
						<td height="37px">
						<input type="checkbox" name="tms_re" <c:if test="${per.tms_re==true}">checked="checked" </c:if>  <c:if test="${per.ROLE_ID==6}">style="display:none"</c:if> />
						</td>
					</tr>
					<tr>
						<td height="37px"><input type="checkbox" name="user_reg" <c:if test="${per.user_reg==true}">checked="checked" </c:if> <c:if test="${per.ROLE_ID==3||per.ROLE_ID==4||per.ROLE_ID==5||per.ROLE_ID==6}">style="display:none"</c:if> />
					</tr>
					<tr>
						<td height="37px">
						<input type="checkbox" name="iss_list" <c:if test="${per.iss_list==true}">checked="checked" </c:if> <c:if test="${per.ROLE_ID==6}">style="display:none"</c:if> />
						</td>
					</tr>
					<tr>
						<td height="37px">
						<input type="checkbox" name="tms_list" <c:if test="${per.tms_list==true}">checked="checked" </c:if><c:if test="${per.ROLE_ID==3||per.ROLE_ID==4||per.ROLE_ID==5||per.ROLE_ID==6}">style="display:none"</c:if> />
					</tr>
					<tr>
						<td height="37px">
						<input type="checkbox" name="qva_list" <c:if test="${per.qva_list==true}">checked="checked" </c:if> />
					</tr>
				</table>	
				</div>
			</c:forEach>
			<div style="display:inline-block"></div>
			</div>
		
		<div style="text-align: right;">
		
		<button type="button" style="background-color: green; color: white; width: 130px;" onclick="myFunction()">Save</button> </div>

		</div>
		
		<script type="text/javascript">
		
		
		
		
		function myFunction() {
			
			var over_view = [];
			var pro_detail = [];
			var add_pro = [];
			var set_upd = [];
			var set_res = [];
			var cre_iss = [];
			var upd_iss = [];
			var set_reg = [];
			var eff_mana = [];
			var eff_can = [];
			var qva_upd = [];
			var tms_re = [];
			var user_reg = [];
			var iss_list = [];
			var tms_list = [];
			var qva_list = [];
			
			
			$('input:checkbox[name=over_view]').each(function() {
				  if (this.checked) {
					  over_view.push(true);
					  } else {
						over_view.push(false);
					  }  
				  
		});
		$('input:checkbox[name=pro_detail]').each(function() {
			  if (this.checked) {
				  pro_detail.push(true);
				  } else {
				  pro_detail.push(false);
				  }  
			  
	});
		$('input:checkbox[name=add_pro]').each(function() {
			  if (this.checked) {
				  add_pro.push(true);
				  } else {
					  add_pro.push(false);
				  }  
			  
	});
		$('input:checkbox[name=set_upd]').each(function() {
			  if (this.checked) {
				  set_upd.push(true);
				  } else {
					  set_upd.push(false);
				  }  
			  
	});
		$('input:checkbox[name=set_res]').each(function() {
			  if (this.checked) {
				  set_res.push(true);
				  } else {
					  set_res.push(false);
				  }  
			  
	});
		$('input:checkbox[name=cre_iss]').each(function() {
			  if (this.checked) {
				  cre_iss.push(true);
				  } else {
					  cre_iss.push(false);
				  }  
			  
	});
		$('input:checkbox[name=upd_iss]').each(function() {
			  if (this.checked) {
				  upd_iss.push(true);
				  } else {
					  upd_iss.push(false);
				  }  
			  
	});
		$('input:checkbox[name=set_reg]').each(function() {
			  if (this.checked) {
				  set_reg.push(true);
				  } else {
					  set_reg.push(false);
				  }  
			  
	});
		$('input:checkbox[name=eff_mana]').each(function() {
			  if (this.checked) {
				  eff_mana.push(true);
				  } else {
					  eff_mana.push(false);
				  }  
			  
	});
		$('input:checkbox[name=eff_can]').each(function() {
			  if (this.checked) {
				  eff_can.push(true);
				  } else {
					  eff_can.push(false);
				  }  
			  
	});
		$('input:checkbox[name=qva_upd]').each(function() {
			  if (this.checked) {
				  qva_upd.push(true);
				  } else {
					  qva_upd.push(false);
				  }  
			  
	});
		$('input:checkbox[name=tms_re]').each(function() {
			  if (this.checked) {
				  tms_re.push(true);
				  } else {
					  tms_re.push(false);
				  }  
			  
	});
		$('input:checkbox[name=user_reg]').each(function() {
			  if (this.checked) {
				  user_reg.push(true);
				  } else {
					  user_reg.push(false);
				  }  
			  
	});
		$('input:checkbox[name=iss_list]').each(function() {
			  if (this.checked) {
				  iss_list.push(true);
				  } else {
					  iss_list.push(false);
				  }  
			  
	});
		$('input:checkbox[name=tms_list]').each(function() {
			  if (this.checked) {
				  tms_list.push(true);
				  } else {
					  tms_list.push(false);
				  }  
			  
	});
		$('input:checkbox[name=qva_list]').each(function() {
			  if (this.checked) {
				  qva_list.push(true);
				  } else {
					  qva_list.push(false);
				  }  
			  
	});
			
		console.log(over_view);	
			
			
			var perrr = [];
			perrr.push({per_id:1, over_view: over_view[0], pro_detail: pro_detail[0],add_pro:add_pro[0],set_upd:set_upd[0],
            	set_res:set_res[0],cre_iss:cre_iss[0],upd_iss:upd_iss[0],set_reg:set_reg[0],eff_mana:eff_mana[0],eff_can:eff_can[0],qva_upd:qva_upd[0],
            	tms_re:tms_re[0],user_reg:user_reg[0],iss_list:iss_list[0],tms_list:tms_list[0],qva_list:qva_list[0]},
            	
            	{per_id:2, over_view: over_view[1], pro_detail: pro_detail[1],add_pro:add_pro[1],set_upd:set_upd[1],
                	set_res:set_res[1],cre_iss:cre_iss[1],upd_iss:upd_iss[1],set_reg:set_reg[1],eff_mana:eff_mana[1],eff_can:eff_can[1],qva_upd:qva_upd[1],
                	tms_re:tms_re[1],user_reg:user_reg[1],iss_list:iss_list[1],tms_list:tms_list[1],qva_list:qva_list[1]},
                	
                	{per_id:3, over_view: over_view[2], pro_detail: pro_detail[2],add_pro:add_pro[2],set_upd:set_upd[2],
                    	set_res:set_res[2],cre_iss:cre_iss[2],upd_iss:upd_iss[2],set_reg:set_reg[2],eff_mana:eff_mana[2],eff_can:eff_can[2],qva_upd:qva_upd[2],
                    	tms_re:tms_re[2],user_reg:user_reg[2],iss_list:iss_list[2],tms_list:tms_list[2],qva_list:qva_list[2]},
                    	
                    	{per_id:4, over_view: over_view[3], pro_detail: pro_detail[3],add_pro:add_pro[3],set_upd:set_upd[3],
                        	set_res:set_res[3],cre_iss:cre_iss[3],upd_iss:upd_iss[3],set_reg:set_reg[3],eff_mana:eff_mana[3],eff_can:eff_can[3],qva_upd:qva_upd[3],
                        	tms_re:tms_re[3],user_reg:user_reg[3],iss_list:iss_list[3],tms_list:tms_list[3],qva_list:qva_list[3]},
                        	{per_id:5, over_view: over_view[4], pro_detail: pro_detail[4],add_pro:add_pro[4],set_upd:set_upd[4],
                            	set_res:set_res[4],cre_iss:cre_iss[4],upd_iss:upd_iss[4],set_reg:set_reg[4],eff_mana:eff_mana[4],eff_can:eff_can[4],qva_upd:qva_upd[4],
                            	tms_re:tms_re[4],user_reg:user_reg[4],iss_list:iss_list[4],tms_list:tms_list[4],qva_list:qva_list[4]});
			
	
			
			
			
			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");
			$(document).ajaxSend(function(e, xhr, options) {
				xhr.setRequestHeader(header, token);
			});
			
			

			

				
			
			
			
			
			$.ajax({
			      type: "POST",
			      contentType : 'application/json; charset=utf-8',
			      
			      url: "${pageContext.request.contextPath}/updatePer",
			      data:JSON.stringify(perrr
			      ),                                                                                                                               
			      success :function(result) {
			    	  alert("saved");
			     }, error : function(e) {
				    
				     alert("error")
				    },
			  });
			
		}
	</script>
	
	
	
	
	
	
</body>
</html>
