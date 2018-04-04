<div class="list">
    <form >
        <#if list??>
        <span><h1>${list.title}</span></h1><br>
        <span><h2></h2>${list.description}</h2></span>
        <table>
            <#list list.elements as element>
                <tr>
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
                </tr>
            </#list>
        </table>
        <input type="button" value="Ajouter"/>
        <input type="button" value="Supprimer"/>
        </#if>
    </form>
</div>