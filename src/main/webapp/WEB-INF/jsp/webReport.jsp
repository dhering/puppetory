<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<jsp:include page="components/head.jsp"/>
    <body>
        <jsp:include page="components/header.jsp"/>

        <div class="container">
            <div class="row no-pad">
                <div class="col-xs-12 col-sm-3" id="sidebar">
                    <div class="list-group">
                        <a href='<c:url value="/report/linuxServerOverviewWebReport" />' class="list-group-item">Linux</a>
                        <a href='<c:url value="/report/unixServerOverviewWebReport" />' class="list-group-item">Unix</a>
                        <a href='<c:url value="/report/windowsServerOverviewWebReport" />' class="list-group-item">Windows</a>
                    </div>
                </div><!--/.sidebar-->
                <div class="col-xs-12 col-sm-9">
                    <div class="row search-row">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <c:if test="${not empty searchMsg}">
                            <div class="alert alert-warning" role="alert">
                                Invalid search query: '<c:out value="${searchFilter}" />'. <br />
                                Try to use a format like: '{name: "searchquery"}'
                            </div>
                            </c:if>
                            <form class="input-group" action="<c:url value='${reportUrl}' />" method="GET">
                                <input type="text" value="<c:out value='${searchFilter}' />" name="searchFilter" class="form-control" placeholder="Filter for...">
                                <span class="input-group-btn">
                                    <button class="btn btn-default" type="submit">Go!</button>
                                </span>
                                <a href='<c:url value="${reportUrl}" />' class="btn btn-default pull-right" type="submit">
                                        <span class="glyphicon glyphicon-remove"></span> Clear
                                </a>
                            </form><!-- /input-group -->
                        </div>
                    </div>
                    <div class="row">
                    ${report}
                    </div>
                </div>
            </div>


            <jsp:include page="components/footer.jsp"/>
        </div>

        <jsp:include page="components/foot.jsp"/>
    </body>
</html>