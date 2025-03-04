<#list bindingFlags as flag>
INSERT INTO binding (status, event) VALUES (${flag.status}, ${flag.event});
</#list>