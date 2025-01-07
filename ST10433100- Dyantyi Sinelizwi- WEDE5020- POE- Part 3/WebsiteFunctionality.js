function submitForm() {
        alert("Submission Recieved! Please allow us 2 Business Days to get back to you! Happy Healing!");
        return false;
}

document.addEventListener("DOMContentLoaded", function() {
        let popup = document.getElementById ("salePopup");
        let closeBtn = document.getElementById ("Close");

        popup.style.display = "flex";                    //this makes the popup show when the page loads

        closeBtn.addEventListener("click", function() {
            popup.style.display = "none";
        });
});

document.getElementById("contactForm");

form.addEventListener('submit'), function(event) {
    event.preventDefault();

    const nameInput= document,getElementById('Name').value;
     if (nameInput.trim() === '') {
        alert ("Please enter your Name!");
     } else {
        form.submit();
     }

}


function closePopup() {
    document.getElementById("popup").style.display = "none";
}