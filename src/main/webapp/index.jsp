<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="jsp/head.jsp" %>
</head>
<body>

<%@ include file="jsp/navigation.jsp"%>

<div class="container">
    <div class="row no-pad">
        <div class="col-xs-12 col-sm-3" id="sidebar">
            <div class="list-group">
                <a href="#" class="list-group-item active">Linux</a>
                <a href="#" class="list-group-item">Unix</a>
                <a href="#" class="list-group-item">Windows</a>

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
                        <button class="btn btn-default pull-right" type="button"><span class="glyphicon glyphicon-remove"></span> Clear</button>
                    </div><!-- /input-group -->
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        lxub-014
                        <a class="btn btn-default btn-xs pull-right" href="#" role="button">View details <span class="caret"></span></a>
                    </div>
                    <div class="panel-body hidden">
                        Panel content
                    </div>
                </div>
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        lxdb-023
                        <a class="btn btn-default btn-xs pull-right" href="#" role="button">View details <span class="caret"></span></a>
                    </div>
                    <div class="panel-body">
                        <div role="tabpanel">

                            <!-- Nav tabs -->
                            <ul class="nav nav-tabs" role="tablist">
                                <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Hardware</a></li>
                                <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">OS</a></li>
                                <li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">Applications</a></li>
                                <li role="presentation"><a href="#settings" aria-controls="settings" role="tab" data-toggle="tab">Others</a></li>
                            </ul>

                            <!-- Tab panes -->
                            <div class="tab-content">
                                <div role="tabpanel" class="tab-pane active" id="home">
                                    <table class="table table-striped">
                                        <tr>
                                            <th>Fact</th>
                                            <th>Value</th>
                                        </tr>
                                        <tr>
                                            <td>Kernel</td>
                                            <td>Linux</td>
                                        </tr>
                                        <tr>
                                            <td>OS</td>
                                            <td>Debian</td>
                                        </tr>
                                        <tr>
                                            <td>CPU</td>
                                            <td>12-core</td>
                                        </tr>
                                        <tr>
                                            <td>RAM</td>
                                            <td>32 GB</td>
                                        </tr>
                                        <tr>
                                            <td>HDD</td>
                                            <td>15 GB</td>
                                        </tr>
                                    </table>
                                </div>
                                <div role="tabpanel" class="tab-pane" id="profile">a</div>
                                <div role="tabpanel" class="tab-pane" id="messages">b</div>
                                <div role="tabpanel" class="tab-pane" id="settings">c</div>
                            </div>

                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        lxub-056
                        <a class="btn btn-default btn-xs pull-right" href="#" role="button">View details <span class="caret"></span></a>
                    </div>
                    <div class="panel-body">
                        <span class="glyphicon glyphicon-refresh"></span> Loading...
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        lxrh-123
                        <a class="btn btn-default btn-xs pull-right" href="#" role="button">View details <span class="caret"></span></a>
                    </div>
                    <div class="panel-body hidden">
                        Panel content
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        lxrh-186
                        <a class="btn btn-default btn-xs pull-right" href="#" role="button">View details <span class="caret"></span></a>
                    </div>
                    <div class="panel-body hidden">
                        Panel content
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        lxdb-196
                        <a class="btn btn-default btn-xs pull-right" href="#" role="button">View details <span class="caret"></span></a>
                    </div>
                    <div class="panel-body hidden">
                        Panel content
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        lxub-258
                        <a class="btn btn-default btn-xs pull-right" href="#" role="button">View details <span class="caret"></span></a>
                    </div>
                    <div class="panel-body hidden">
                        Panel content
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        lxdb-456
                        <a class="btn btn-default btn-xs pull-right" href="#" role="button">View details <span class="caret"></span></a>
                    </div>
                    <div class="panel-body hidden">
                        Panel content
                    </div>
                </div>
            </div>
            </div><!--/row-->
        </div><!--/.col-xs-12.col-sm-9-->
    </div><!--/row-->

    <jsp:include page="jsp/footer.jsp" />

</div><!--/.container-->


<%@ include file="jsp/foot.jsp"%>
</body>
</html>