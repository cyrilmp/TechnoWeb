<form id ="modifElement" name="modifElement">
<table class="element" style="border-radius: 5px;border-style: solid;margin:30px;padding: 30px;">
    <tr >
    <td class="status" rowspan="3" height="50px" width="10px">
        <input type="button" name="status" class="list${list.id}" value="0" style="background-color: grey;border-radius: 5px;margin:5px;"/>
    </td>
    <td class="title" >
        <div style="margin:5px;">Titre: <input type="text" name="title_element" class="list${list.id}" value=""/></div>
    </td>
    <td rowspan="1" class="description" >
        <div style="margin:5px;">Description: </div>
    </td>
    </tr>
    <tr>
        <td class="tag" >
            <div style="margin:5px;">Tag: <input type="text" name="tag_element" class="list${list.id}" value=""/></div>
        </td>
        <td rowspan="2">
            <div style="margin:5px;"><textarea name="desc_element" class="list${list.id}" rows="3"></textarea></div>
        </td>
    </tr>
    <tr>
        <td>
            <div style="margin:5px;"><button class="addElement" id="${list.id}"><img  style="width:10px;height: 10px;" src="/image/vvert.png"/></button>
            </div>
        </td>
    </tr>
</table>
</form>