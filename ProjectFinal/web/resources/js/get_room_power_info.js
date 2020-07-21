const DAY_PATTERN1 = "dd/MM/yyyy";
const DAY_PATTERN2 = "yyyy-MM-dd";
function onRowClick(event) {
    var table = document.getElementById("table-info");
    var cell = event.target;
    var rowIndex = cell.parentNode.rowIndex;

    var roomId = document.getElementById("room-id");
    var roomName = document.getElementById("room-name");
    var fromDate = document.getElementById("from-date");
    var toDate = document.getElementById("to-date");
    var toDateValue = document.getElementById("to-date-value");
    
    var oldElectric = document.getElementById("old-electric");
    var oldWater = document.getElementById("old-water");

    roomId.value = table.rows[rowIndex].cells[5].innerText;
    roomName.innerText = table.rows[rowIndex].cells[1].innerText;
    
    fromDate.innerText = table.rows[rowIndex].cells[2].innerText;

    var date = convertStringDate(fromDate.innerText);
    date.setMonth(date.getMonth() + 1);

    var dateStr = convertDateString(DAY_PATTERN1, date);
    toDate.innerText = dateStr;
    toDateValue.value = dateStr;
    
    oldElectric.value = parseInt(table.rows[rowIndex].cells[3].innerText);
    oldWater.value = parseInt(table.rows[rowIndex].cells[4].innerText);
}

/**
 * format date theo định dạng cho trước
 * @param {any} format có 2 dạng là dd/MM/yyyy và yyyy-MM-dd
 * @param {any} date date cần format
 */
function convertDateString(format, date) {
    var day = date.getDate();
    var month = date.getMonth() + 1;
    var year = date.getFullYear();

    switch (format) {
        case DAY_PATTERN1:
            return pad(day) + "/" + pad(month) + "/" + year;
        case DAY_PATTERN2:
            return year + "-" + pad(month) + "-" + pad(day);
    }
}

/**
 * convert string (dd/MM/yyyy) to date
 * @param {type} dateString
 * @returns {Date}
 */
function convertStringDate(dateString) {
    var dateParts = dateString.split("/");
    console.log(dateParts);
    var date = parseInt(dateParts[0]);
    var month = parseInt(dateParts[1]) - 1; //base month = 0
    var year = parseInt(dateParts[2]);

    return new Date(year, month, date);
}

function pad(number) {
    if (number < 10) {
        return "0" + number;
    } else
        return number;
}
