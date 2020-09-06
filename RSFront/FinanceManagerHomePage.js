const url = "http://localhost:8080/project1/";

var reimbstatusid = document.getElementById("reimbstatus").value;
console.log(reimbstatusid);
document.getElementById("rtablebtn").addEventListener("click", findallreimbursementstatusFunc);


async function findallreimbursementstatusFunc() {

    document.getElementById("rbody").innerText ="";

    let resp = await fetch(url + "reimbursement/" + "findallreimbursementstatus/" + reimbstatusid, {
        method: 'GET',
        credentials: 'include'
    });
    console.log(resp);
    let data = await resp.json();
    console.log(data);
    //reimbstatusid = data.reimbStatusId;

    if (resp.status === 200) {
        //let data = await resp.json();
        for (let reimbursement of data) {
            console.log(reimbursement);
            let row = document.createElement("tr");
            let cell1 = document.createElement("td");
            cell.innerHTML = reimbursement.reimbId;
            row.appendChild(cell1);
            let cell2 = document.createElement("td");
            cell2.innerHTML = reimbursement.reimbAmount;
            row.appendChild(cell2);
            let cell3 = document.createElement("td");
            cell3.innerHTML = reimbursement.reimbDescription;
            row.appendChild(cell3);
            let cell4 = document.createElement("td");
            cell4.innerHTML = reimbursement.reimbAuthor;
            row.appendChild(cell4);
            let cell5 = document.createElement("td");
            cell5.innerHTML = reimbursement.reimbResolver;
            row.appendChild(cell5);
            let cell6 = document.createElement("td");
            switch (reimbursement.reimbStatusId) {
                case 1:
                    cell4.innerHTML = "PENDING";
                    break;
                case 2:
                    cell4.innerHTML = "APPROVED";
                    break;
                case 3:
                    cell4.innerHTML = "DENIED";
                    break;
            }
            row.appendChild(cell6);
            let cell7 = document.createElement("td");
            switch (reimbursement.reimbTypeId) {
                case 1:
                    cell4.innerHTML = "LODGING";
                    break;
                case 2:
                    cell4.innerHTML = "TRAVEL";
                    break;
                case 3:
                    cell4.innerHTML = "FOOD";
                    break;
                case 4:
                    cell4.innerHTML = "OTHER";
                    break;
            }
            row.appendChild(cell7);
           
            document.getElementById("rbody").appendChild(row);
        }
    }
    
    document.getElementById("rtablebtn").addEventListener("click", findAllFunc);
    async function findAllFunc() {

    document.getElementById("rbody").innerText ="";

    let resp = await fetch(url + "reimbursement", {
        credentials: "include",
    });

    console.log(reimbursement);
    let row = document.createElement("tr");
    let cell1 = document.createElement("td");
    cell.innerHTML = reimbursement.reimbId;
    row.appendChild(cell1);
    let cell2 = document.createElement("td");
    cell2.innerHTML = reimbursement.reimbAmount;
    row.appendChild(cell2);
    let cell3 = document.createElement("td");
    cell3.innerHTML = reimbursement.reimbDescription;
    row.appendChild(cell3);
    let cell4 = document.createElement("td");
    cell4.innerHTML = reimbursement.reimbAuthor;
    row.appendChild(cell4);
    let cell5 = document.createElement("td");
    cell5.innerHTML = reimbursement.reimbResolver;
    row.appendChild(cell5);
    let cell6 = document.createElement("td");
    switch (reimbursement.reimbStatusId) {
        case 1:
            cell4.innerHTML = "PENDING";
            break;
        case 2:
            cell4.innerHTML = "APPROVED";
            break;
        case 3:
            cell4.innerHTML = "DENIED";
            break;
    }
    row.appendChild(cell6);
    let cell7 = document.createElement("td");
    switch (reimbursement.reimbTypeId) {
        case 1:
            cell4.innerHTML = "LODGING";
            break;
        case 2:
            cell4.innerHTML = "TRAVEL";
            break;
        case 3:
            cell4.innerHTML = "FOOD";
            break;
        case 4:
            cell4.innerHTML = "OTHER";
            break;
    }
    row.appendChild(cell7);
   
    document.getElementById("rbody").appendChild(row);
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

    console.log(reimbursement)

    let resp = await fetch(url + "reimbursement", {
        method: 'POST',
        body: JSON.stringify(reimbursement),
        credentials: "include"
    })

    if(resp.status === 201){
        findAllFunc()
    } else {
        document.getElementById("login-row").innerText = "Avenger could not be added.";
    }
}

