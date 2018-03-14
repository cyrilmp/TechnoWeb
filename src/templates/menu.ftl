        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/index">
                <span class="glyphicon glyphicon-fire"></span>
                Logo
            </a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li>
                    <a href="/list-users">User</a>
                </li>
                <li>
                    <a href="#">List</a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <#if name??>
                    <li>Hello ${name}</li>
                    <li><a href="/sign-out">Sign out</a></li>
                <#else>
                    <li>
                        <a href="/sign-in">Sign In</a>
                    </li>
                    <li>
                        <a href="/sign-up">Sign Up</a>
                    </li>
                </#if>
            </ul>
        </div>
