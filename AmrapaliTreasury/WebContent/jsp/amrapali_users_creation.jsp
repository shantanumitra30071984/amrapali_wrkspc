<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
<form:form method="POST" action="AmrapaliCastleUsersCreation.html" commandName="amrapaliBean" id="insertForm">
<b>Amrapali Users Creation Page</b><br>
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
<div style="margin: auto">
<table style="width: 50%;border: 1px;color: black;border-style: solid; margin: auto" id="insertTable">
<tr style="width: 100%;border-color: black">
<th style="border-style: solid">User Name</th>
<th style="border-style: solid">Flat No</th>

<th style="border-style: solid">Square Ft</th>
</tr>
<tr style="border-color: black;">
<td><input type="text" name="userName" id="userName0"/></td>
<td><input type="text" name="flatNo" id="flatNo0"/></td>

<td><input type="text" name="sqrFt" id="sqrFt0"/></td>
</tr>
<tr style="border-color: black;">
<td><input type="text" name="userName" id="userName1"/></td>
<td><input type="text" name="flatNo" id="flatNo1"/></td>

<td><input type="text" name="sqrFt" id="sqrFt1"/></td>
</tr><tr style="border-color: black;">
<td><input type="text" name="userName" id="userName2"/></td>
<td><input type="text" name="flatNo" id="flatNo2"/></td>

<td><input type="text" name="sqrFt" id="sqrFt2"/></td>
</tr>
<tr style="border-color: black;">
<td><input type="text" name="userName" id="userName3"/></td>
<td><input type="text" name="flatNo" id="flatNo3"/></td>

<td><input type="text" name="sqrFt" id="sqrFt3"/></td>
</tr>
<tr style="border-color: black;">
<td><input type="text" name="userName" id="userName4"/></td>
<td><input type="text" name="flatNo" id="flatNo4"/></td>

<td><input type="text" name="sqrFt" id="sqrFt4"/></td>
</tr>
<tr style="border-color: black;">
<td><input type="text" name="userName" id="userName5"/></td>
<td><input type="text" name="flatNo" id="flatNo5"/></td>

<td><input type="text" name="sqrFt" id="sqrFt5"/></td>
</tr>
<tr style="border-color: black;">
<td><input type="text" name="userName" id="userName6"/></td>
<td><input type="text" name="flatNo" id="flatNo6"/></td>

<td><input type="text" name="sqrFt" id="sqrFt6"/></td>
</tr>

</table>
</div>
<div style="margin: auto">
<table style="margin: auto">
<tr style="margin: auto"><td style="text-align: center">
<input type="submit" name="save" value="Save" id="save" onclick="submitInsertForm()"/><input type="hidden" name="xmlData" id="xmlData"/></td></tr>
</table>
</div>
</form:form>

</body>

</html>