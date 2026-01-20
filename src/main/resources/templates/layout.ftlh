<#import "spring.ftl" as spring>
<#macro layout>

    <!doctype html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
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
                <a class="navbar-brand" href="/">Hidden brand</a>
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="#">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/movies">Movies</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
                    </li>
                </ul>
                <form class="d-flex">
                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                </form>


                <#assign known = SPRING_SECURITY_CONTEXT?? >
                <#if known>
                    <#assign
                    user = SPRING_SECURITY_CONTEXT.authentication.principal
                    name = user.getUsername()
                    >
                    <#if user??>
                        <a href="/profile/${name}" class="text-light m-2 m-sm-0">
                            Hello ${name}:

                            <#list user.authorities as auth>
                                <span class="text-danger">${auth}</span>
                                <#if auth?has_next>
                                    /
                                </#if>
                            </#list>
                        </a>
                        <form action="auth/logout" method="post">
                            <button type="submit" class="btn alert-danger m-2"
                                    id="logout">
                                Logout
                            </button>
                        </form>
                    </#if>
                <#else>
                    <a href="/auth/login"
                       class="btn alert-success m-2">Login</a>
                    <a href="/auth/register"
                       class="btn alert-info m-2">Register</a>
                </#if>

                <#--                <a href="auth/login" type="button" class="btn alert-success">Login</a>-->
                <#--                <a href="auth/logout" type="button" class="btn alert-danger">Logout</a>-->
            </div>
        </div>
    </nav>

    <div class="container py-5">
        <#nested spring>
    </div>

    <footer class="footer mt-auto py-3 bg-light">
        <div class="container">
            <span class="text-muted">Movie Review</span>
        </div>
    </footer>

    </body>
    </html>
</#macro>