<!-- Fixed navbar -->
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

            <span class="navbar-brand glyphicon glyphicon-link"> Puppetory</span>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="dropdown active">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Server<span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="/report/linuxServerOverviewWebReport">Linux</a></li>
                        <li><a href="/report/unixServerOverviewWebReport">Unix</a></li>
                        <li><a href="/report/windowsServerOverviewWebReport">Windows</a></li>
                    </ul>
                </li>
                <li><a href="#clients">Clients</a></li>
                <li><a href="#infrastructure">Infrastructure</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#logout"><span class="glyphicon glyphicon-off" aria-hidden="true"></span> Logout</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>