<div class="list" style="border-radius: 5px;border-style: solid;margin:30px;padding: 30px;">
    <form >
        <#if list??>
        <h3>${list.title}</h3>
        <p>${list.description}</p>
            <#list list.elements as element>
                <#--<tr>
                    <#if element.status == 0>
                        <td><input type="checkbox" name="status" id="${element.id}" value="${element.status}" class="check_element"></td>
                        <td colspan="2"><input type="text" class="val_element" id="${element.id}" value="${element.title}"/></td></tr>
                        <tr><td colspan="3"><input type="text" class="desc_element" id="${element.id}" value="${element.description}"/></td></tr>
                        <tr><td><button class="modify_element" id="${element.id}"><img  style="width:10px;height: 10px;" src="/image/vvert.png"/></button></td>
                        <td><button class="deleteelement" id="${element.id}"><img  style="width:10px;height: 10px;" src="/image/cross.png"/></button></td>
                    <#elseif element.status == 1>
                        <td><input type="checkbox" name="status" id="${element.id}" value="${element.status}" class="check_element" checked></td>
                        <td>${element.title}</td>
                        <td><button class="deleteelement" id="${element.id}"><img  style="width:10px;height: 10px;" src="/image/cross.png"/></button></td>
                    <#elseif element.status == 2>
                        <td><input type="checkbox" name="status" id="${element.id}" value="${element.status}" class="check_element" checked></td>
                        <td style="text-decoration: line-through">${element.title}</td>
                        <td><button class="deleteelement" id="${element.id}"><img  style="width:10px;height: 10px;" src="/image/cross.png"/></button></td>
                    </#if>
                </tr>-->
                <#include "/elementToDo.ftl">
            </#list>
            <div id="elementVide${list.id}" hidden>
                <#include "/elementToDoEmpty.ftl">
            </div>
        <input type="button" value="Ajouter un element" onclick="$('#elementVide${list.id}').show();"/>
        <input type="button" id="${list.id}" class="deleteListToDo" value="Supprimer"/>
        </#if>
    </form>
</div>