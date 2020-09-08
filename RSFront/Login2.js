const url = "http://localhost:8080/project1/";

document.getElementById("loginbtn").addEventListener("click", loginFunc);
var usern;

async function loginFunc(){
    console.log("in the login");
    usern = document.getElementById("userName").value;
    sessionStorage.setItem("userName", usern);
    let userp = document.getElementById("password").value;

    let user = {
        userName : usern,
        password : userp
    }
    
    let resp =await fetch(url+"login", {
        method: 'POST',
        body: JSON.stringify(user),
        credentials: "include"
    });

    if(resp.status===200){
        document.getElementById("login-row").innerText = "YOU HAVE LOGGED IN";
        
       let data = await resp.text();
        console.log(data);
        let uRId = data;
        sessionStorage.setItem("uRId",uRId);
        console.log("URId" + uRId);
        if (uRId == 'Employee') {
            console.log("employee page");
            window.location.href = 'EmployeeHomePage.html';

        } else if (uRId == 'Finance Manager') {
            console.log("finance manager page");
            window.location.href = 'FinanceManagerHomePage.html';
        }
    } else {
        document.getElementById("login-row").innerText = "Login Failed";
    }
    //hey
}

