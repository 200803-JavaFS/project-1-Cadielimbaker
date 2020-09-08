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
    
console.log(resp);
    if(resp.status===200){
        document.getElementById("login-row").innerText = "YOU HAVE LOGGED IN.";
        let data = await resp.json();  
        
        console.log(data); 
        let uRId = data;
        sessionStorage.setItem("uRId",uRId);
        
        if (uRId == 1) {
            window.location.href = "EmployeeHomePage.html";

        } else if (uRId == 2) {
            
            window.location.href = "FinanceManagerHomePage.html";
           
    
        }
}   }


