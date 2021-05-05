<%-- 
    Document   : index
    Created on : 04-may-2021, 4:14:06
    Author     : Zxbxl
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="com.emergentes.tarea.Tarea"%>
<%
    List<Tarea> lista = (List<Tarea>) request.getAttribute("lista");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tareas</title>
    </head>
    <body>
        <h1>Lista de Tareas</h1>
        <p><a href="MainController?op=nuevo">Nuevo</a></p>
        <table border="2">
            <tr>
                <th>Id</th>
                <th>Tarea</th>
                <th>Prioridad</th>
                <th>Completado</th>
                <th></th>
                <th></<th>
            </tr>
            <c:forEach var="item" items="${lista}">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.tarea}</td>
                    <td>
                        <c:if test="${item.prioridad == 1 }">Baja</c:if>
                        <c:if test="${item.prioridad == 2 }">Media</c:if>
                        <c:if test="${item.prioridad == 3 }">Alta</c:if>
                        </td>
                        <td>
                        <c:if test="${item.completado == 1 }"><input type="checkbox" checked onclick="javascript: return false;"></c:if>
                        <c:if test="${item.completado == 2 }"><input type="checkbox"  onclick="javascript: return false;"></c:if>
                        </td>
                        <td><a href="MainController?op=editar&id=${item.id}">Editar</a></td>
                    <td><a href="MainController?op=eliminar&id=${item.id}">Eliminar</a></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>