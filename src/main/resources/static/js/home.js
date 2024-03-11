let btnOpen = document.getElementById("btn-open");
let btnClose = document.getElementById("btn-close");

btnOpen.addEventListener("click", () => {
    document.getElementById("form-redactor").style.display = "flex";
})

btnClose.addEventListener("click", () => {
    document.getElementById("form-redactor").style.display = "none";
})