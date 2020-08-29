const url = "http://localhost:8080/project1/";

document.getElementById("loginbtn").addEventListener("click", login);

async function loginFunc(){

    let usern = document.getElementById("UserName").value;
    let userp = document.getElementById("password").value;

    let user = {
        UserName : usern,
        password : userp
    }
    let resp =await fetch(url+"login", {
        method: 'POST',
        body: JSON.stringify(user)
    })

        if(resp.status===200){
            document.getElementById("login-row").innerText = "THANK YOU FOR LOGGING IN!";
            let button = document.createElement('button');
            button.className = "btn btn-success";
            button.id = "findAllBtn";
            button.innerText = "Find All Employees";
            button.onclick=findAllFunc;
            document.getElementById("table-row").appendChild(button);
        }else{
            document.getElementById("login-row").innerText = "LOGIN FAILED!"; 
        }
}

async function findAllFunc() {

    let resp = await fetch(url+"Users");

if(resp.status===200){
    let data = await resp.json;
    console.log(data);
}
}