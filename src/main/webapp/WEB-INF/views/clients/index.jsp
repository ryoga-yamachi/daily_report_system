<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="actCli" value="${ForwardConst.ACT_CLI.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commShow" value="${ForwardConst.CMD_SHOW.getValue()}" />
<c:set var="commNew" value="${ForwardConst.CMD_NEW.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>顧客　一覧</h2>
        <table id="client_list">
             <tbody>
                <tr>
                    <th class="client_company">企業名（担当）</th>
                    <th class="client_title">内容</th>
                    <th class="client_progress">状況</th>
                    <th>経過</th>
                    <th class="client_employee">担当責任者</th>
                </tr>
                <c:forEach var="client" items="${clients}" varStatus="status">

                    <tr class="row${status.count % 2}">
                        <td class="client_company">${client.company}</td>
                        <td class="client_title">${client.title}</td>
                        <td class="client_progress">
                            <c:choose>
                                <c:when test="${client.progress == 0}">交渉中</c:when>
                                <c:when test="${client.progress == 1}">進行中</c:when>
                                <c:otherwise>終了</c:otherwise>
                            </c:choose></td>
                        <td> <a href=" <c:url value='?action=${actCli}&command=${commShow}&id=${client.id}' />">詳細を見る</a></td>
                        <td class="client_employee"><c:out value="${client.employee}" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

         <div id="pagination">
            （全 ${clients_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((clients_count - 1) / maxRow) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='?action=${actCli}&command=${commIdx}&page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='?action=${actCli}&command=${commNew}' />">新規顧客の登録</a></p>

    </c:param>
</c:import>