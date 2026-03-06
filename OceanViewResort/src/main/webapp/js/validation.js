function validateForm(){
    let checkIn = document.getElementsByName("checkIn")[0].value;
    let checkOut = document.getElementsByName("checkOut")[0].value;

    if(checkIn >= checkOut){
        alert("Check-out must be after Check-in");
        return false;
    }
    return true;
}