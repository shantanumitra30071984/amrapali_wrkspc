<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<script src="js/jquery1.7.2.min.js"></script>
<script type="text/javascript">

$( document ).ready(function() {
    $('#insertTable').find('tr').click( function(){
  
  $('#rowNumber').val(($(this).index()-1));
  var row=$('#rowNumber').val();
  $('#copyAmount').val($('#sqrFt'+row).val());
  
});
});



function getUserDetails(obj){
document.forms[0].action='AmrapaliUsersPaymentInsert.html';
document.forms[0].submit();

}

function saveUsersPayment(){
var block='';
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
                             xml += "\t<Year>" + $('#financialYear'+row).val() + "</Year>\n";
                               xml += "\t<Jan>" + $('#month0'+row).val() + "</Jan>\n";
                            xml += "\t<Feb>" + $('#month1'+row).val() + "</Feb>\n";
                             xml += "\t<Mar>" + $('#month2'+row).val() + "</Mar>\n";
                               xml += "\t<Apr>" + $('#month3'+row).val() + "</Apr>\n";
                                 xml += "\t<May>" + $('#month4'+row).val() + "</May>\n";
                                   xml += "\t<Jun>" + $('#month5'+row).val() + "</Jun>\n";
                                     xml += "\t<Jul>" + $('#month6'+row).val() + "</Jul>\n";
                                       xml += "\t<Aug>" + $('#month7'+row).val() + "</Aug>\n";
                                         xml += "\t<Sep>" + $('#month8'+row).val() + "</Sep>\n";
                                           xml += "\t<Oct>" + $('#month9'+row).val() + "</Oct>\n";
                                             xml += "\t<Nov>" + $('#month10'+row).val() + "</Nov>\n";
                                               xml += "\t<Dec>" + $('#month11'+row).val() + "</Dec>\n";
                                               xml += "\t<DueAmount>" + $('#amountToBePaid'+row).val() + "</DueAmount>\n";
                                               
                       xml +="</User>";
                   
                    }
                    row++;
                });
                xml += '</Root>';
              
                $('#xmlData').val(xml);
                
               document.forms[0].action='AmrapaliUsersPaymentInsert.html';
                document.forms[0].submit();
}

function copyAmountFunc(){

var month=['month0','month1','month2','month3','month4','month5','month6','month7','month8','month9','month10','month11'];
var fromMonth=$('#fromMonth').val();
var toMonth=$('#toMonth').val();
var rowNumber=$('#rowNumber').val();
var copyAmount=$('#copyAmount').val();

for(var i=fromMonth;i<=toMonth;i++){
$('#'+month[i]+rowNumber).val(copyAmount);

}
calculateDueAmount(rowNumber);
}
function calculateDueAmount(idx){
var currentMonth=$('#currentMonthNo').val();
var monthCount=0;
var colno='';

var size=$('#sqrFt'+idx).val();
for(var i=0;i<currentMonth;i++){
colno=i+''+idx;

if($('#month'+colno).val()=='0'){
monthCount++;
}

}
var dueAmount=parseInt(size)*parseInt(monthCount);
$('#amountToBePaid'+idx).val(dueAmount);
}
 function displayYear(){
 
 }
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body style="background-color:#EFFAF5; ">
<form:form action="/AmrapaliUsersPaymentInsert.html" method="POST" commandName="amrapaliBean">
<div style="margin:auto" >
<table style="width: 80%; margin: auto" id="">
<tr style="margin:auto"><td style="text-align:right"><form:select id="year" path="year" onchange="displayYear()">
<form:option value="Select" >Select</form:option>
<form:options items="${yearMap}" />
</form:select></td>
<td style="text-align:right">
<form:radiobutton path="block" id="blockA"  onclick="getUserDetails(this)" value="A"/>A
<form:radiobutton path="block" id="blockB"  onclick="getUserDetails(this)" value="B"/>B
<form:radiobutton path="block" id="blockC" value="C" onclick="getUserDetails(this)"/>C
<form:radiobutton path="block" id="blockD" value="D" onclick="getUserDetails(this)"/>D
<input type="hidden" name="currentMonthNo" id="currentMonthNo" value="${amrapaliBean.currentMonthNo}"/>
</td>

<td>Maintenance per square feet

<input type="text" name="feePerSqrFeet" id="feePerSqrFeet" value="${amrapaliBean.feePerSqrFeet}" size="1"><input type="hidden" name="rowNumber" id="rowNumber"/>

Amount<input type="text" name="copyAmount" id="copyAmount" style="text-align: right"  size="2" /></td>

<td>From<form:select path="fromMonth" id="fromMonth">
<form:option value="0">JAN</form:option>
<form:option value="1">FEB</form:option>
<form:option value="2">MAR</form:option>
<form:option value="3">APR</form:option>
<form:option value="4">MAY</form:option>
<form:option value="5">JUN</form:option>
<form:option value="6">JUL</form:option>
<form:option value="6">AUG</form:option>
<form:option value="8">SEP</form:option>
<form:option value="9">OCT</form:option>
<form:option value="10">NOV</form:option>
<form:option value="11">DEC</form:option>

</form:select>-To<form:select path="toMonth" id="toMonth">
<form:option value="0">JAN</form:option>
<form:option value="1">FEB</form:option>
<form:option value="2">MAR</form:option>
<form:option value="3">APR</form:option>
<form:option value="4">MAY</form:option>
<form:option value="5">JUN</form:option>
<form:option value="6">JUL</form:option>
<form:option value="6">AUG</form:option>
<form:option value="8">SEP</form:option>
<form:option value="9">OCT</form:option>
<form:option value="10">NOV</form:option>
<form:option value="11">DEC</form:option>

</form:select><input type="button" name="copyButton" id="copyButton" value="Copy" onclick="copyAmountFunc()"/></td>
</tr>
</table>
</div>
<div style="margin: auto;">
<table style="width: 50%;border: 1px;color: black;border-style: solid; margin: auto" id="insertTable">
<tr style="width: 100%;border-color: black">
<th style="border-style: solid">No</th>
<th style="border-style: solid">Flat No</th>

<th style="border-style: solid">Owner Name</th>

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
<th style="border-style: solid">Payment Due</th>
</tr>
<c:forEach var="listValue" items="${amrapaliuserPaymentBeanList}" varStatus="status">
				
			
<tr style="border-color: black;background-color:${listValue.rowColor}">
<td>${status.index +1}</td>
<td><font size="2"><input type="text" name="[amrapaliuserPaymentBeanList].flatNo " id="flatNo${status.index}" value="${listValue.flatNo }" style="text-align: center;border:none;background-color:${listValue.rowColor}" size="2"/></font></td>

<td><font size="2"><input type="text" name="[amrapaliuserPaymentBeanList].userName" id="userName${status.index}" value="${listValue.userName }" style="text-align: center;border:none;background-color:${listValue.rowColor}" size="35"/></font></td>
<td><input type="text" name="[amrapaliuserPaymentBeanList].sqrFt" id="sqrFt${status.index}" value="${listValue.sqrFt }" style="text-align: center;border:none;background-color:${listValue.rowColor}" size="4"/></td>
<td><input type="text" name="[amrapaliuserPaymentBeanList].financialYear" id="financialYear${status.index}" value="${listValue.financialYear }" style="text-align: center;border:none;background-color:${listValue.rowColor}" size="4"/></td>
<td><input type="text" name="[amrapaliuserPaymentBeanList].month0" id="month0${status.index}" value="${listValue.month0 }" style="text-align: right;border:none;background-color:${listValue.rowColor}" size="3"/></td>
<td><input type="text" name="[amrapaliuserPaymentBeanList].month1" id="month1${status.index}" value="${listValue.month1 }" style="text-align: right;border:none;background-color:${listValue.rowColor}" size="3"/></td>
<td><input type="text" name="[amrapaliuserPaymentBeanList].month2" id="month2${status.index}" value="${listValue.month2 }" style="text-align: right;border:none;background-color:${listValue.rowColor}" size="3"/></td>
<td><input type="text" name="[amrapaliuserPaymentBeanList].month3" id="month3${status.index}" value="${listValue.month3 }" style="text-align: right;border:none;background-color:${listValue.rowColor}" size="3"/></td>
<td><input type="text" name="[amrapaliuserPaymentBeanList].month4" id="month4${status.index}" value="${listValue.month4 }" style="text-align: right;border:none;background-color:${listValue.rowColor}" size="3"/></td>
<td><input type="text" name="[amrapaliuserPaymentBeanList].month5" id="month5${status.index}" value="${listValue.month5 }" style="text-align: right;border:none;background-color:${listValue.rowColor}" size="3"/></td>
<td><input type="text" name="[amrapaliuserPaymentBeanList].month6" id="month6${status.index}" value="${listValue.month6 }" style="text-align: right;border:none;background-color:${listValue.rowColor}" size="3"/></td>
<td><input type="text" name="[amrapaliuserPaymentBeanList].month7" id="month7${status.index}" value="${listValue.month7 }" style="text-align: right;border:none;background-color:${listValue.rowColor}" size="3"/></td>
<td><input type="text" name="[amrapaliuserPaymentBeanList].month8" id="month8${status.index}" value="${listValue.month8 }" style="text-align: right;border:none;background-color:${listValue.rowColor}" size="3"/></td>
<td><input type="text" name="[amrapaliuserPaymentBeanList].month9" id="month9${status.index}" value="${listValue.month9 }" style="text-align: right;border:none;background-color:${listValue.rowColor}" size="3"/></td>
<td><input type="text" name="[amrapaliuserPaymentBeanList].month10" id="month10${status.index}" value="${listValue.month10 }" style="text-align: right;border:none;background-color:${listValue.rowColor}" size="3"/></td>
<td><input type="text" name="[amrapaliuserPaymentBeanList].month11" id="month11${status.index}" value="${listValue.month11 }" style="text-align: right;border:none;background-color:${listValue.rowColor}" size="3"/></td>
<td><input type="text" name="[amrapaliuserPaymentBeanList].amountToBePaid" id="amountToBePaid${status.index}" value="${listValue.amountToBePaid }" style="text-align: right;border:none;background-color:${listValue.rowColor}" size="5"  onclick="calculateDueAmount(${status.index})" readonly="readonly"/></td>

</tr>
</c:forEach>

</table>
</div>
<div style="margin: auto">
<table style="width: 50%;margin:auto">
<tr style="margin:auto">
<td style="text-align:center">
<input type="submit" id="save" name="save" value="Save" onclick="saveUsersPayment()"><input type="hidden" name="xmlData" id="xmlData"/>
</td>
</tr>
</table>
</div>
</form:form>
</body>
</html>