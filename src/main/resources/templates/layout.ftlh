<#import "spring.ftl" as spring>
<#macro layout>

    <!doctype html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <meta name="_csrf_header" content="${_csrf.headerName}">
        <meta name="_csrf_token" content="${_csrf.token}">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
              crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
                crossorigin="anonymous"></script>
        <title>Movie Review</title>
    </head>
    <body>

    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo01"
                    aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
                <a class="navbar-brand" href="/">Movie Review</a>
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="#"><@spring.message "layout.nav.home"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/movies"><@spring.message "layout.nav.movies"/></a>
                    </li>
                </ul>
                <form class="d-flex">
                    <#if _csrf??>
                        <input
                                type="hidden"
                                name="${(_csrf.parameterName)!'csrf-param-name'}"
                                value="${(_csrf.token)!'csrf-token'}"
                        >
                    </#if>

                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success"
                            type="submit"><@spring.message "layout.nav.search"/></button>
                </form>


                <#assign known = SPRING_SECURITY_CONTEXT?? >
                <#if known>
                    <#assign
                    user = SPRING_SECURITY_CONTEXT.authentication.principal
                    name = user.getUsername()
                    >
                    <#if user??>
                        <a href="/profile/${name}" class="text-light m-2 m-sm-0">
                            <@spring.message "layout.nav.hello"/> ${name}:

                            <#list user.authorities as auth>
                                <span class="text-danger">${auth}</span>
                                <#if auth?has_next>
                                    /
                                </#if>
                            </#list>
                        </a>
                        <form action="auth/logout" method="post">
                            <#if _csrf??>
                                <input
                                        type="hidden"
                                        name="${(_csrf.parameterName)!'csrf-param-name'}"
                                        value="${(_csrf.token)!'csrf-token'}"
                                >
                            </#if>
                            <button type="submit" class="btn alert-danger m-2"
                                    id="logout">
                                <@spring.message "layout.nav.logout"/>
                            </button>
                        </form>
                    </#if>
                <#else>
                    <a href="/auth/login"
                       class="btn alert-success m-2"><@spring.message "layout.nav.login"/></a>
                    <a href="/auth/register"
                       class="btn alert-info m-2"><@spring.message "layout.nav.register"/></a>
                </#if>

                <#--                <a href="auth/login" type="button" class="btn alert-success">Login</a>-->
                <#--                <a href="auth/logout" type="button" class="btn alert-danger">Logout</a>-->
                <div class="dropdown">
                    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton2"
                            data-bs-toggle="dropdown" aria-expanded="false">
                        <@spring.message "layout.nav.lang.change"/>
                    </button>
                    <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="dropdownMenuButton2">
                        <li><a class="dropdown-item" href="?lang=en"><@spring.message "layout.nav.lang_en"/></a></li>
                        <li><a class="dropdown-item" href="?lang=ru"><@spring.message "layout.nav.lang_ru"/></a></li>
                    </ul>
                </div>
            </div>
        </div>
    </nav>

    <div class="container py-5">
        <#nested spring>
    </div>

    <footer class="footer mt-auto py-3 bg-light">
        <div class="container">
            <span class="text-muted"><@spring.message "layout.footer.label"/></span>
        </div>
    </footer>

    </body>
</html>
</#macro>