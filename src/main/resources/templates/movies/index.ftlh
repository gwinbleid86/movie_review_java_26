<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="row">
        <#list movies as movie>
            <div class="card col-3 m-3" style="width: 18rem;">
                <img src="api/images/${movie.id}" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">${movie.name}</h5>
                    <p class="card-text">${movie.year?c}</p>
                    <a href="/movies/${movie.id}" class="btn btn-primary">Info</a>
                </div>
            </div>
        </#list>
    </div>
</@main.layout>