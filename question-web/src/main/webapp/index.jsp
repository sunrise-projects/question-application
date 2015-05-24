<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath}/app/question" var="questionMvcUrl" />
<c:set value="${pageContext.request.contextPath}/resources" var="resourcesUrl" />
<!DOCTYPE html>
<html lang="en">
<body>
<br/><br/>
Please find below tools:
<ul>
<li><a href="${pageContext.request.contextPath}/app/question/welcome">Memorize</href>
<li><a href="${pageContext.request.contextPath}/app/quiz/welcome">Practice Question</href>
</ul>


 
</body>
</html>
