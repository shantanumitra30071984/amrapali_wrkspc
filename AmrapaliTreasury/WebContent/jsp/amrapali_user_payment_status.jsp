<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="js/jquery1.7.2.min.js"></script>
    <script type="text/javascript">
       var block='';
     function submitInsertForm(){
      if ($('#blockA').attr("checked") == "checked") {
            block='A';
      }else if ($('#blockB').attr("checked") == "checked") {
            block='B';
      }else if ($('#blockC').attr("checked") == "checked") {
            block='C';
      }else if ($('#blockD').attr("checked") == "checked") {
            block='D';
      }

      var xml = '<?xml version="1.0"?><Root>\n';
                var row=0;
                $("#insertTable tr").each(function () {
                   
                       if(($('#userName'+row).val()!=null && $('#userName'+row).val()!='')|| ($('#flatNo'+row).val()!=null && $('#flatNo'+row).val()!='') || ($('#block'+row).val()!=null && $('#block'+row).val()!='') || ($('#sqrFt'+row).val()!=null && $('#sqrFt'+row).val()!=''))
                          {
                          xml +="<User>";
                            xml += "\t<UserName>" + $('#userName'+row).val() + "</UserName>\n";
                            xml += "\t<FlatNo>" + $('#flatNo'+row).val() + "</FlatNo>\n";
                            xml += "\t<Block>" + block + "</Block>\n";
                            xml += "\t<SquareFt>" + $('#sqrFt'+row).val() + "</SquareFt>\n";
                       xml +="</User>";
                        }
                    
                    row++;
                });
                xml += '</Root>';
              //  window.alert(xml);
                $('#xmlData').val(xml);
               // document.forms[0].action="AmrapaliCastleUsersCreationPost.html";
                //document.forms[0].submit();
     }
    </script>
</head>
<body>
<form:form method="POST" action="AmrapaliUsersPaymentStatus.html" commandName="amrapaliBean" id="searchForm">
<b>Amrapali Users Payment Status</b><br>
<div style="margin:auto" >
<table style="width: 50%; margin: auto">
<tr style="text-align:right"><td style="text-align:right">
<input type="radio" name="block" id="blockA" value="A">A</input>
<input type="radio" name="block" id="blockB" value="B">B</input>
<input type="radio" name="block" id="blockC" value="C">C</input>
<input type="radio" name="block" id="blockD" value="D">D</input>
</td></tr>
</table>
</div>


<div style="margin:auto" >
<fieldset>
<legend>Search By</legend>
<table style="width: 50%; margin: auto">
<tr style="text-align:right"><td style="text-align:right" colspan="2">
UserName:<input type="text" id="userName" name="userName">
</td>

</tr>

<tr style="text-align:right"><td style="text-align:right" colspan="2">
Payment Due:<input type="text" id="paymentDueFlag" name="paymentDueFlag">
</td>

</tr>
</table>
</fieldset>
</div>
<div style="margin: auto">
<table style="margin: auto">
<tr style="margin: auto"><td style="text-align: center">
<input type="submit" name="search" value="Search" id="search" onclick="submitInsertForm()"/><input type="hidden" name="xmlData" id="xmlData"/></td></tr>
</table>

</div>
<c:if test="${amrapaliBean.config=='SEARCH'}">
<div style="margin: auto">
<table style="width: 50%;border: 1px;color: black;border-style: solid; margin: auto" id="insertTable">
<tr style="width: 100%;border-color: black">
<th style="border-style: solid">Block</th>
<th style="border-style: solid">Flat No</th>

<th style="border-style: solid">User Name</th>
<th style="border-style: solid">MF/SQRFT</th>
<th style="border-style: solid">Flat Size</th>
<th style="border-style: solid">Year</th>
<th style="border-style: solid">JAN</th>
<th style="border-style: solid">FEB</th>
<th style="border-style: solid">MAR</th>
<th style="border-style: solid">APR</th>
<th style="border-style: solid">MAY</th>
<th style="border-style: solid">JUN</th>
<th style="border-style: solid">JUL</th>
<th style="border-style: solid">AUG</th>
<th style="border-style: solid">SEP</th>
<th style="border-style: solid">OCT</th>
<th style="border-style: solid">NOV</th>
<th style="border-style: solid">DEC</th>
</tr>
<c:forEach var="listValue" items="${paymentStatusList}">
				
			
<tr style="border-color: black;">
<td><input type="text" name="${listValue.block}" id="userName${listValue.status}"/></td>
<td><input type="text" name="${listValue.flatNo }" id="flatNo${listValue.status}"/></td>

<td><input type="text" name="${listValue.userName}" id="block${listValue.status}"/></td>

<td><input type="text" name="${listValue.jan}" id="jan${listValue.status}"/></td>
<td><input type="text" name="${listValue.feb}" id="feb${listValue.status}"/></td>
<td><input type="text" name="${listValue.mar}" id="mar${listValue.status}"/></td>
<td><input type="text" name="${listValue.apr}" id="apr${listValue.status}"/></td>
<td><input type="text" name="${listValue.may}" id="may${listValue.status}"/></td>
<td><input type="text" name="${listValue.jun}" id="jun${listValue.status}"/></td>
<td><input type="text" name="${listValue.jul}" id="jul${listValue.status}"/></td>
<td><input type="text" name="${listValue.aug}" id="aug${listValue.status}"/></td>
<td><input type="text" name="${listValue.sep}" id="sep${listValue.status}"/></td>
<td><input type="text" name="${listValue.oct}" id="oct${listValue.status}"/></td>
<td><input type="text" name="${listValue.nov}" id="nov${listValue.status}"/></td>
<td><input type="text" name="${listValue.dec}" id="dec${listValue.status}"/></td>

</tr>
</c:forEach>

</table>
</div>
</c:if>
</form:form>

</body>

</html>