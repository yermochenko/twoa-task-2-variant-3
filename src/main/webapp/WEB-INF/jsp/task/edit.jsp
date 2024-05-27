<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@taglib prefix="u" tagdir="/WEB-INF/tags"%>
<%--@elvariable id="task" type="by.vsu.twoa.domain.Task"--%>
<c:choose>
	<c:when test="${not empty task}">
		<c:set var="title" value="Редактирование задачи ${task.name}"/>
	</c:when>
	<c:otherwise>
		<c:set var="title" value="Добавление новой задачи"/>
	</c:otherwise>
</c:choose>
<u:page title="${title}">
	<c:if test="${not empty param['error']}">
		<div class="w3-panel w3-pale-red w3-leftbar w3-border-red">
			<span onclick="this.parentElement.style.display='none'" class="w3-button w3-right w3-hover-red">&times;</span>
			<h3>Ошибка</h3>
			<p>${param['error']}</p>
		</div>
	</c:if>
	<c:if test="${not empty param['info']}">
		<div class="w3-panel w3-pale-green w3-leftbar w3-border-green">
			<span onclick="this.parentElement.style.display='none'" class="w3-button w3-right w3-hover-green">&times;</span>
			<h3>Информация</h3>
			<p>${param['info']}</p>
		</div>
	</c:if>
	<c:url var="url_task_save_html" value="/task/save.html"/>
	<form action="${url_task_save_html}" method="post" class="w3-container form">
		<c:if test="${not empty task}">
			<input type="hidden" name="id" value="${task.id}"/>
		</c:if>
		<div class="form-element">
			<label for="name">Название*:</label>
			<input type="text" name="name" value="${task.name}" id="name" class="w3-input w3-border">
		</div>
		<c:if test="${not empty task}">
			<div class="form-element">
				<fmt:formatDate var="task_created" value="${task.created}" pattern="dd.MM.yyyy"/>
				<label for="created">Дата создания:</label>
				<input type="text" value="${task_created}" id="created" class="w3-input w3-border" disabled>
			</div>
		</c:if>
		<h3>Исходные данные для задачи</h3>
		<div class="form-element">
			<table>
				<tr>
					<th colspan="2">Точка №1</th>
					<th class="line"></th>
					<th colspan="2">Точка №2</th>
				</tr>
				<tr>
					<td><label for="x1"><span class="math"><i>x</i><sub>1</sub></span>:</label></td>
					<td><input type="text" name="x1" value="${task.point1.x}" id="x1" class="w3-input w3-border"></td>
					<td class="line"></td>
					<td><label for="x2"><span class="math"><i>x</i><sub>2</sub></span>:</label></td>
					<td><input type="text" name="x2" value="${task.point2.x}" id="x2" class="w3-input w3-border"></td>
				</tr>
				<tr>
					<td><label for="y1"><span class="math"><i>y</i><sub>1</sub></span>:</label></td>
					<td><input type="text" name="y1" value="${task.point1.y}" id="y1" class="w3-input w3-border"></td>
					<td class="line"></td>
					<td><label for="y2"><span class="math"><i>y</i><sub>2</sub></span>:</label></td>
					<td><input type="text" name="y2" value="${task.point2.y}" id="y2" class="w3-input w3-border"></td>
				</tr>
				<tr>
					<th colspan="2">Радиус</th>
					<td colspan="3" rowspan="2"></td>
				</tr>
				<tr>
					<td><label for="r"><span class="math"><i>R</i></span>:</label></td>
					<td><input type="text" name="r" value="${task.radius}" id="r" class="w3-input w3-border"></td>
				</tr>
			</table>
		</div>
		<button type="submit" class="w3-button w3-green w3-hover-teal">Сохранить</button>
		<c:url var="url_task_list_html" value="/task/list.html"/>
		<a href="${url_task_list_html}" class="w3-button w3-blue w3-hover-indigo">Вернуться</a>
		<button type="button" class="w3-button w3-red w3-hover-pink">Удалить</button>
	</form>
	<c:if test="${not empty task and not empty task.circleVariant1 and not empty task.circleVariant2}">
		<h3>Решение задачи</h3>
		<p><strong>Вариант 1</strong>: окружность с центром в точке <span class="math"><i>O</i><sub>1</sub>(<span id="circle_1_center_x">${task.circleVariant1.center.x}</span>,&nbsp;<span id="circle_1_center_y">${task.circleVariant1.center.x}</span>)</span> и радиусом <span class="math"><i>R</i><sub>1</sub>&nbsp;=&nbsp;<span id="circle_1_radius">${task.circleVariant1.radius}</span></span></p>
		<p><strong>Вариант 2</strong>: окружность с центром в точке <span class="math"><i>O</i><sub>2</sub>(<span id="circle_2_center_x">${task.circleVariant2.center.y}</span>,&nbsp;<span id="circle_2_center_y">${task.circleVariant2.center.y}</span>)</span> и радиусом <span class="math"><i>R</i><sub>2</sub>&nbsp;=&nbsp;<span id="circle_2_radius">${task.circleVariant2.radius}</span></span></p>
	</c:if>
</u:page>