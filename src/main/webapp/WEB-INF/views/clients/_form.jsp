<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="constants.AttributeConst" %>

<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>
<label for="${AttributeConst.CLI_COMPANY.getValue()}">企業名（担当）</label><br />
<input type="text" name="${AttributeConst.CLI_COMPANY.getValue()}" id="${AttributeConst.CLI_COMPANY.getValue()}" value="${client.company}" />
<br /><br />

<label for="${AttributeConst.CLI_TITLE.getValue()}">内容</label><br />
<input type="text" name="${AttributeConst.CLI_TITLE.getValue()}" id="${AttributeConst.CLI_TITLE.getValue()}" value="${client.title}" />
<br /><br />

<label for="${AttributeConst.CLI_PROGRESS.getValue()}">状況</label><br />
<select name="${AttributeConst.CLI_PROGRESS.getValue()}" id="${AttributeConst.CLI_PROGRESS.getIntegerValue()}">
    <option value="${AttributeConst.PRO_NEGOTIATION.getIntegerValue()}"<c:if test="${client.progress == AttributeConst.PRO_NEGOTIATION.getIntegerValue()}"> selected</c:if>>交渉中</option>
    <option value="${AttributeConst.PRO_WIP.getIntegerValue()}"<c:if test="${client.progress == AttributeConst.PRO_WIP.getIntegerValue()}"> selected</c:if>>進行中</option>
    <option value="${AttributeConst.PRO_END.getIntegerValue()}"<c:if test="${client.progress == AttributeConst.PRO_END.getIntegerValue()}"> selected</c:if>>終了</option>
</select>
<br /><br />

<label for="${AttributeConst.CLI_CONTENT.getValue()}">経過</label><br />
<textarea  name="${AttributeConst.CLI_CONTENT.getValue()}" id="${AttributeConst.CLI_CONTENT.getValue()}" rows="10" cols="50">${client.content}</textarea>
<br /><br />

<label for="${AttributeConst.CLI_EMPLOYEE.getValue()}">担当責任者</label><br />
<input type="text" name="${AttributeConst.CLI_EMPLOYEE.getValue()}" id="${AttributeConst.CLI_EMPLOYEE.getValue()}" value="${client.employee}" />
<br /><br />
<input type="hidden" name="${AttributeConst.CLI_ID.getValue()}" value="${client.id}" />
<input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
<button type="submit">投稿</button>