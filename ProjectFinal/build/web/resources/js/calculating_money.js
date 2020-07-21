function onKeyUp(event) {
    var debt = parseFloat(document.getElementById("price").value);
    var preDebt = parseFloat(document.getElementById("pre-debt").value);
    
    var debtInput = document.getElementById("debt");
    var debtText = document.getElementById("debt-text");
    
    var excessCashInput = document.getElementById("excess-cash");
    var excessCashText = document.getElementById("excess-cash-text");
    
    var proceed = parseFloat(event.target.value);
    
    debt = (debt + preDebt) - proceed;
    
    debtText.innerText = debt < 0 ? "0" : debt.toLocaleString("en-US") + " VND";
    debtInput.value = debt < 0 ? 0 : debt;
    
    excessCashText.innerText = debt < 0 ? (-debt).toLocaleString("en-US") : "0" + " VND";
    excessCashInput.value = debt < 0 ? (-debt) : 0;
}