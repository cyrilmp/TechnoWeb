<div class="list">
    <form >
        <#if list??>
        <span>${list.title}</span>
        <table>
            <tr>
                <td><input type="checkbox" name="element"></td>
                <td>element</td>
            </tr>
        </table>
        <input type="button" value="Valider"/>
        <input type="button" value="Annuler"/>
        <input type="button" value="Supprimer"/>
        </#if>
    </form>
</div>