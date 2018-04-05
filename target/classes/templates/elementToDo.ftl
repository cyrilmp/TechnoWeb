<form id ="modifElement" name="modifElement">
    <input type="text" name="idList_element" class="${element.id}" value="${element.idList}" hidden/>
<table class="element" style="border-radius: 5px;border-style: solid;margin:30px;padding: 30px;">
    <tr >
    <td class="status" rowspan="3" height="50px" width="10px">
        <#if element.status==0>
            <input type="button" name="status_element" class="${element.id}" value="${element.status}" style="background-color: green;border-radius: 5px;margin:5px;"/>
        <#elseif element.status==1>
            <input type="button" name="status_element" class="${element.id}" value="${element.status}" style="background-color: orange;border-radius: 5px;margin:5px;"/>
        <#else>
            <input type="button" name="status_element" class="${element.id}" value="${element.status}" style="background-color: red;border-radius: 5px;margin:5px;"/>
        </#if>
    </td>
    <td class="title" >
        <div style="margin:5px;">Titre: <input type="text" name="title_element" class="${element.id}" value="${element.title}"/></div>
    </td>
    <td rowspan="1" class="description" >
        <div style="margin:5px;">Description: </div>
    </td>
    </tr>
    <tr>
        <td class="tag" >
            <div style="margin:5px;">Tag: <input type="text" name="tag_element" class="${element.id}" value="${element.tag}"/></div>
        </td>
        <td rowspan="2">
            <div style="margin:5px;"><textarea name="desc_element" class="${element.id}" rows="3">${element.description}</textarea></div>
        </td>
    </tr>
    <tr>
        <td>
            <div style="margin:5px;"><button class="modifElement" id="${element.id}"><img  style="width:10px;height: 10px;" src="/image/vvert.png"/></button>
                <button class="deletElement" id="${element.id}"><img  style="width:10px;height: 10px;" src="/image/cross.png"/></button></div>
        </td>
    </tr>
</table>
</form>