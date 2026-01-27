<#import "layout.ftlh" as layout>
<@layout.layout>
    <h2>Hello ${world}!</h2>

    <#if authorities??>
        <h3>Authorities</h3>
        <ul>
            <#list authorities as item>
                <li>${item}</li>
            </#list>
        </ul>
    </#if>
</@layout.layout>

