function onRowClick(event) {
    var form = document.getElementById("submit-room-form");
    var table = document.getElementById("table-rooms");
    var roomText = document.getElementById("room-id");
    
    var cell = event.target;
    var rowIndex = cell.parentNode.rowIndex;
    
    var roomId = table.rows[rowIndex].cells[0].innerText;
    roomText.value = roomId;
    
    var action = "./GettingInvoices";
    
    form.action = action;
    
    form.submit();
}