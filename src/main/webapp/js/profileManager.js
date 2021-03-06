window.onload = () =>{
    document.getElementById("username").innerHTML = sessionStorage.getItem("employeeFirstName") + " " +sessionStorage.getItem("employeeLastName");
    document.getElementById("firstName").innerHTML = "First Name: " + sessionStorage.getItem("employeeFirstName");
    document.getElementById("lastName").innerHTML = "Last Name: " + sessionStorage.getItem("employeeLastName");
    document.getElementById("userName").innerHTML = "Username: " + sessionStorage.getItem("employeeUsername");
    document.getElementById("email").innerHTML = "Email: " + sessionStorage.getItem("email");

document.getElementById("submit").addEventListener("click", () => {
    let firstname = document.getElementById("firstNameText").value;
    let lastname = document.getElementById("lastNameText").value;
    let username = document.getElementById("usernameText").value;
    let email = document.getElementById("emailText").value;

    // Ajax
    let xhr =  new XMLHttpRequest();

    xhr.onreadystatechange = () => {
        if(xhr.onreadystatechange === XMLHttpRequest.DONE && xhr.status === 200) {
            let data = JSON.parse(xhr.responseText);
            submit();
        }
    };

    xhr.open("POST", `profileUpdate.do?firstname=${firstname}&lastname=${lastname}&username=${username}&email=${email}`)
    xhr.send();

    document.getElementById("loginMessage").innerHTML = '<span class="label label-success label-center">Profile Change Successful</span>';
    sessionStorage.setItem("employeeFirstName", document.getElementById("firstNameText").value);
    sessionStorage.setItem("employeeLastName", document.getElementById("lastNameText").value);
    sessionStorage.setItem("employeeUsername", document.getElementById("usernameText").value);
    sessionStorage.setItem("email",document.getElementById("emailText").value);
    setTimeout(() =>{ window.location.replace("profileManager.do");}, 2000);

})
}
