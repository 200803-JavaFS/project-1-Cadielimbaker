const url = "http://localhost:8080/project1/";

document.getElementById("loginbtn").addEventListener("click", loginFunc);

async function loginFunc(){
    
    let usern = document.getElementById("userName").value;
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
    
//console.log(resp);
    if(resp.status===200){
        document.getElementById("login-row").innerText = "YOU HAVE LOGGED IN.";
        let data = await resp.json();  
        //let data = await resp.text();
        console.log(data); 
        let uRId = data.userRoleId;
        sessionStorage.setItem("uRId",uRId);
        //let uRole = data.userRoleId.userRoleId;
        //let uRole = data.userRoleId;
        if (uRId == 1) {
            console.log("employee page");
            window.location.href = 'EmployeeHomePage.html'; 

        } else if (uRId == 2) {
            console.log("finance manager page");
            window.location.href = 'FinanceManagerHomePage.html'; 
        }
       
    } else {
        document.getElementById("login-row").innerText = "LOGIN FAILED.";
    }

}