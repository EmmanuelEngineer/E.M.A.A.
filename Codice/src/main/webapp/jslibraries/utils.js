function createToast(title,message){
    let toastHtml;
    toastHtml="<div id=\"toast\" class=\"toast-box\">\n" +
        "    <div class=\"toast-header\">" +
        "<button onclick=\"closeToast()\" style=\"padding: ;margin: 6px;\" class=\"btn-danger btn\">X</button>" +
        "<h3 id=\"toastTitle\">"+title+"</h3></div>" +
        "    <div class=\"toast-body\"><span id=\"toastMessage\">"+message+"</span></div>\n" +
        "</div>";
    $("body").append(toastHtml);
    setTimeout(closeToast,50000)
}
function closeToast(){
    $("#toast").remove();
}