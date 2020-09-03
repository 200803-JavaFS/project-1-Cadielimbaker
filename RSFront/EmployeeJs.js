const url = "http://localhost:8080/project1/";

document.getElementById("loginbtn").addEventListener("click", loginFunc);

async function loginFunc(){
    
    let usern = document.getElementById("userName").value;
    let userp = document.getElementById("password").value;

    let user = {
        userName : usern,
        password : userp
    }
    console.log(user);
    let resp =await fetch(url+"login", {
        method: 'POST',
        body: JSON.stringify(user),
        credentials: "include"
    })

        if(resp.status===200){
            console.log(resp)
            document.getElementById("login-row").innerText = "THANK YOU FOR LOGGING IN!";
            
            let button = document.createElement('button');
            button.className = "btn btn-success";
            button.id = "findAllBtn";
            button.innerText = "Find All Employees";
            button.onclick=findAllFunc;
            document.getElementById("table-row").appendChild(button);

            //addusers button show
        let button2 = document.createElement('button');
        button2.className = "btn btn-success";
        button2.id = "addUsersBtn";
        button2.innerText = "Add a Users";
        button2.onclick = AddFunc;
        document.getElementById("formbtn").appendChild(button2);
        }else{
            document.getElementById("login-row").innerText = "LOGIN FAILED!"; 
        }
}

async function findAllFunc() {

    document.getElementById("Usersbody").innerText ="";

    let resp = await fetch(url + "Users", {
        credentials: "include",
    });

    if (resp.status === 200) {
        let data = await resp.json();
        for (let avenger of data) {
            console.log(avenger);
            let row = document.createElement("tr");
            let cell = document.createElement("td");
            cell.innerHTML = avenger.supId;
            row.appendChild(cell);
            let cell2 = document.createElement("td");
            cell2.innerHTML = avenger.supName;
            row.appendChild(cell2);
            let cell3 = document.createElement("td");
            cell3.innerHTML = avenger.supPower;
            row.appendChild(cell3);
            let cell4 = document.createElement("td");
            cell4.innerHTML = avenger.firstName;
            row.appendChild(cell4);
            let cell5 = document.createElement("td");
            cell5.innerHTML = avenger.lastName;
            row.appendChild(cell5);
            let cell6 = document.createElement("td");
            cell6.innerHTML = avenger.powerLevel;
            row.appendChild(cell6);
            if (avenger.homeBase != null) {
                let cell7 = document.createElement("td");
                cell7.innerHTML = avenger.homeBase.homeBase;
                row.appendChild(cell7);
            } else {
                let cell7 = document.createElement("td");
                row.appendChild(cell7);
            }
            document.getElementById("avbody").appendChild(row);
        }
    }
}

async function AddFunc(){

    let sName = document.getElementById("supName").value;
    let sPower = document.getElementById("supPower").value;
    let fName = document.getElementById("fName").value;
    let lName = document.getElementById("lName").value;
    let pLevel = document.getElementById("pLevel").value;
    let home = document.getElementById("home").value;

    let avenger = {
        supName : sName,
        supPower : sPower,
        firstName : fName,
        lastName : lName,
        powerLevel : pLevel,
        homeString : home 
    }

    console.log(avenger)

    let resp = await fetch(url + "avenger", {
        method: 'POST',
        body: JSON.stringify(avenger),
        credentials: "include"
    })

    if(resp.status === 201){
        findAllFunc()
    } else {
        document.getElementById("login-row").innerText = "Avenger could not be added.";
    }
}