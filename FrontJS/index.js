const url = "http://localhost:8080/project1/"

document.getElementById("loginbtn").addEventListener("click", loginFunc);

//upon conferring with Nikki - global scoped variables
var usern;


async function loginFunc() {
    console.log("@loginFunc");

    usern = document.getElementById("username").value;
    sessionStorage.setItem("uname", usern);
    let userp = document.getElementById("password").value;

    let user = {
        username: usern,
        password: userp
    }

    //console.log(user);

    let resp = await fetch(url + "login", {
        method: 'POST',
        body: JSON.stringify(user),
        credentials: 'include'
    })

    console.log("feth api login response");
    console.log(resp);

    if (resp.status == 200) {
        document.getElementById("new-msg").innerText = "You have successfully logged in.";
        let data = await resp.json();
        console.log(resp);
        let uRoleId = data;
        sessionStorage.setItem("uRoleId", uRoleId);
        console.log("URoleId: " + uRoleId);
        if (uRoleId == 1) {
            console.log("to employee portal");
            window.location.href ="empportal.html";
    
        } else if (uRoleId == 2) {
            console.log("to finman portal");
            window.location.href = "finmanportal.html";
            
            
        } else { console.log("Holy Cow, Batman!"); }
    } else {
        resetLogin();
    }    

    function resetLogin() {
        console.log("@resetLogin");
        document.getElementById("new-msg").innerText = "Oops, something went wrong. Please try logging in again.";
        //location.reload();
    }
}
