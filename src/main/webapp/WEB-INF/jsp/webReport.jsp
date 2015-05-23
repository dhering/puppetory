<html>
<jsp:include page="components/head.jsp"/>
    <body>
        <jsp:include page="components/header.jsp"/>

        <div class="container">
            <div class="row no-pad">
                <div class="col-xs-12 col-sm-3" id="sidebar">
                    <div class="list-group">
                        <a href="/report/linuxServerOverviewWebReport" class="list-group-item">Linux</a>
                        <a href="/report/unixServerOverviewWebReport" class="list-group-item">Unix</a>
                        <a href="/report/windowsServerOverviewWebReport" class="list-group-item">Windows</a>

                    </div>
                </div><!--/.sidebar-->
                <div class="col-xs-12 col-sm-9">
                    <div class="row search-row">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                            <div class="input-group">
                                <input type="text" class="form-control" placeholder="Filter for...">
                                <span class="input-group-btn">
                                    <button class="btn btn-default" type="button">Go!</button>
                                </span>
                                <button class="btn btn-default pull-right" type="button">
                                        <span class="glyphicon glyphicon-remove"></span> Clear
                                </button>
                            </div><!-- /input-group -->
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