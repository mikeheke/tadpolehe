<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script type="text/javascript">
	$(function(){
		<s:iterator id="tips" value="#request.tipsList">
		if("<s:property value="#tips[0]"/>"!=""){
			$("*[name='<s:property value="#tips[0]"/>']").each(function(){
			$(this).parent().children().last().after("<div style='color: red'><s:property value="#tips[1]"/></div>");
			});
		}
		</s:iterator>
	});
</script>
	