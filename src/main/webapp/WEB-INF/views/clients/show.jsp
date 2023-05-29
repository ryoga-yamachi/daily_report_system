<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.ForwardConst" %>

<c:set var="actCli" value="${ForwardConst.ACT_CLI.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commEdt" value="${ForwardConst.CMD_EDIT.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

    <h2>顧客 詳細ページ</h2>

    <table>
            <tbody>
                <tr>
                    <th>企業名（担当）</th>
                    <td><c:out value="${client.company}" /></td>
                </tr>
                <tr>
                    <th>内容</th>
                    <td><c:out value="${client.title}" /></td>
                </tr>
                <tr>
                     <th>状況</th>
                     <td><c:choose>
                            <c:when test="${client.progress == 0}">交渉中</c:when>
                            <c:when test="${client.progress == 1}">進行中</c:when>
                            <c:otherwise>終了</c:otherwise>
                        </c:choose></td>
                 </tr>
                  <tr>
                    <th>内容</th>
                    <td><pre><c:out value="${client.content}" /></pre></td>
                </tr>
                <tr>
                    <th>登録日時</th>
                    <fmt:parseDate value="${client.createdAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="createDay" type="date" />
                    <td><fmt:formatDate value="${createDay}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
                <tr>
                    <th>更新日時</th>
                    <fmt:parseDate value="${client.updatedAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="updateDay" type="date" />
                    <td><fmt:formatDate value="${updateDay}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
                 <tr>
                    <th>担当責任者</th>
                    <td><c:out value="${client.employee}" /></td>
                </tr>
            </tbody>
        </table>

        <p>
            <a href="<c:url value='?action=${actCli}&command=${commEdt}&id=${client.id}' />">この顧客情報を編集する</a>
        </p>

        <p>
            <a href="<c:url value='?action=${actCli}&command=${commIdx}' />">一覧に戻る</a>
        </p>
    </c:param>
</c:import>