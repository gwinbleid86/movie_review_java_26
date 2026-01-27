<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="card" style="width: 50rem;">
        <h5 class="card-title">${movie.name}</h5>
        <h6 class="card-subtitle mb-2 text-muted">${movie.year?c}</h6>
        <h6 class="card-subtitle mb-2 text-muted">${movie.director.fullName}</h6>
        <img src="/images/${movie.id}" class="card-img-top" alt="${movie.name}">
        <div class="card-body">
            <ul>
                <#if movie.castList??>
                    <#list movie.castList as cast>
                        <li>
                            <p>${cast.fullName} <b>as:</b> ${cast.role}</p>
                        </li>
                    </#list>
                </#if>
            </ul>
            <br>
            <p>Description:</p>
            <p class="card-text">${movie.description}</p>
        </div>
    </div>
</@main.layout>