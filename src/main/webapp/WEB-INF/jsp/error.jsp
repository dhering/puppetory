<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<jsp:include page="components/head.jsp"/>
    <body>
        <jsp:include page="components/header.jsp"/>

        <div class="container">

            <div class="row no-pad">
                <div class="col-xs-12 col-sm-3" id="sidebar">
                </div><!--/.sidebar-->
                <div class="col-xs-12 col-sm-10">
                    <div class="row">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <div class="jumbotron">
                                <h2><c:out value='${title}' /></h2>
                                <p><c:out value='${msg}' /></p>
                            </div></div>
                    </div><!--/row-->
                </div><!--/.col-xs-12.col-sm-9-->

            <jsp:include page="components/footer.jsp"/>
        </div>

        <jsp:include page="components/foot.jsp"/>
    </body>
</html>