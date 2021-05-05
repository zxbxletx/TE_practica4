<%-- 
    Document   : nuevo
    Created on : 04-may-2021, 23:35:05
    Author     : Zxbxl
--%>
  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.emergentes.tarea.Tarea"%>
<%
    Tarea tarea = (Tarea) request.getAttribute("tarea");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tareas</title>
    </head>
    <body>
        <c:if  test="${tarea.id == 0}"><h1>Nueva tarea</h1></c:if>
        <c:if test="${tarea.id !=0}"><h1>Editar tarea</h1></c:if>
            <form action="MainController" method="post">
                <table>
                    <input type="hidden" name="id" value="${tarea.id}">
                <tr>
                    <td>Tarea:  </td>
                    <td><input type="text" name="tarea" value="${tarea.tarea}"></td>
                </tr>
                <tr>

                    <td>Prioridad:  </td>
                    <td>
                        <select name="prioridad">
                            <option value="0" selected readonly> </option>
                            <option value="1" <c:if test="${tarea.prioridad == 1}">selected</c:if>>Baja</option>
                            <option value="2" <c:if test="${tarea.prioridad == 2}">selected</c:if>>Media</option>
                            <option value="3" <c:if test="${tarea.prioridad == 3}">selected</c:if>>Alta</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Completado:  </td>
                        <td>
                            <select name="completado">
                                <option value="0" selected readonly> </option>
                                <option value="1" <c:if test="${tarea.completado == 1}">selected</c:if>>Concluido</option>
                            <option value="2" <c:if test="${tarea.completado == 2}">selected</c:if>>Pendiente</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="ENVIAR"></td>
                </tr>
            </table>
        </form>
</body>
</html>
