let btnCloseDeleteForm = document.getElementById("btn-close-form-delete");

btnCloseDeleteForm.addEventListener("click", () => {
    document.getElementById("delete-form").style.display = "none";
})

function confirmationDelete(userId) {
    document.getElementById("delete-form").style.display = "block";
    document.getElementById("user-id").value = userId;
}
