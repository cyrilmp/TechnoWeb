<div class="list">
    <form >
        <#if list??>
        <span>${list.title}</span>
        <table>
            <#list list.elements as element>
                <tr>
                    <td><input type="checkbox" name="element"></td>
                    <td>${element.title}</td>
                </tr>
            </#list>
        </table>
        <input type="button" value="Valider"/>
        <input type="button" value="Annuler"/>
        <input type="button" value="Supprimer"/>
        </#if>
    </form>
</div>