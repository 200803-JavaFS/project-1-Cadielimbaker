const url = "http://localhost:8080/project1/"

let usern = sessionStorage.getItem("uname");

document.getElementById("findfuncbtn").addEventListener("click", findByEmployeeFunc);
document.getElementById("findfuncbtn1").addEventListener("click", findByStatusFunc);
document.getElementById("findfuncbtn2").addEventListener("click", addFunc);
document.getElementById("submitnewrequest").addEventListener("click", addFunc);
document.getElementById("findfuncbtn4").addEventListener("click", logoutFunc);


//All Reimbursements by Employee
async function findByEmployeeFunc(){
    console.log("@findByEmployeeFunc");

    document.getElementById("rbody").innerText = "";

    let resp = await fetch(url+"empreimbursementsearch", {
        method: "GET",
        credentials: "include"
}   );

    console.log("resp from findByEmployee = " + resp);

    if(resp.status===200){
        let data = await resp.json();
        console.log(data);
        for (let reimbursement of data){
            console.log(reimbursement);
            let row = document.createElement("tr");
            let cell = document.createElement("td");
            cell.innerHTML = reimbursement.reimbId;
            row.appendChild(cell);
            let cell2 = document.createElement("td");
            cell2.innerHTML = reimbursement.amount;
            row.appendChild(cell2);
            let cell3 = document.createElement("td");
            cell3.innerHTML =reimbursement.submitted;
            row.appendChild(cell3);
            if (reimbursement.resolved != null){
                let cell4 = document.createElement("td");
                cell4.innerHTML = reimbursement.resolved;
                row.appendChild(cell4);
            } else {
                let cell4 = document.createElement("td");
                row.appendChild(cell4);
            }
            let cell5 = document.createElement("td");
            cell5.innerHTML = reimbursement.description;
            row.appendChild(cell5);
            let cell6 = document.createElement("td");
            cell6.innerHTML = reimbursement.author;
            row.appendChild(cell6);
            if (reimbursement.resolver != null){
                let cell7 = document.createElement("td");
                cell7.innerHTML = reimbursement.resolver;
                row.appendChild(cell7);
            } else {
                let cell7 = document.createElement("td");
                row.appendChild(cell7);
            }
            let cell8 = document.createElement("td");
                switch (reimbursement.statusId) {
                    case 1:
                        cell8innerText = "Pending";
                        row.appendChild(cell8);
                        break;
                    case 2:
                        cell8.innerText = "Approved";
                        row.appendChild(cell8);
                        break;
                    case 3:
                        cell8.innerText = "Denied";
                        row.appendChild(cell8);
                        break;
                }
                row.appendChild(cell8);          
            let cell9 = document.createElement("td");
                switch (reimbursement.typeId.typeId) {
                    case 1:
                        cell9.innerText = "Lodging";
                        break;
                    case 2:
                        cell9.innerText = "Travel";
                        break;
                    case 3:
                        cell9.innerText = "Food";
                        break;
                    case 4:
                        cell9.innerText = "Other";
                    break;
                }
            row.appendChild(cell9);
            document.getElementById("rbody").appendChild(row);
        }
    }
}

//View By Status

async function findByStatusFunc(){
    console.log("@findByStatusFunc");

    document.getElementById("rbody1").innerText = "";

    let rStatus = document.getElementById("statusIdInput");
    let rsId = rStatus.value;
    console.log("rsId = " + rsId);

    let resp = await fetch(url+"reimbursementsbystatus" + "/" + rsId , {
        credentials: 'include'
    
    });

        if(resp.status===200){
            let data = await resp.json();
            for (let reimbursement of data){
                console.log(reimbursement);
                let row = document.createElement("tr");
                let cell = document.createElement("td");
                cell.innerHTML = reimbursement.reimbId;
                row.appendChild(cell);
                let cell2 = document.createElement("td");
                cell2.innerHTML = reimbursement.amount;
                row.appendChild(cell2);
                let cell3 = document.createElement("td");
                cell3.innerHTML =reimbursement.submitted;
                row.appendChild(cell3);
                if (reimbursement.resolved != null){
                    let cell4 = document.createElement("td");
                    cell4.innerHTML = reimbursement.resolved;
                    row.appendChild(cell4);
                } else {
                    let cell4 = document.createElement("td");
                    row.appendChild(cell4);
                }
                let cell5 = document.createElement("td");
                cell5.innerHTML = reimbursement.description;
                row.appendChild(cell5);
                let cell6 = document.createElement("td");
                cell6.innerHTML = reimbursement.author.userId;
                row.appendChild(cell6);
                if (reimbursement.resolver != null){
                    let cell7 = document.createElement("td");
                    cell7.innerHTML = reimbursement.resolver.userId;
                    row.appendChild(cell7);
                } else {
                    let cell7 = document.createElement("td");
                    row.appendChild(cell7);
                }
                let cell8 = document.createElement("td");
                    switch (reimbursement.statusId.statusId) {
                        case 1:
                            cell8innerText = "Pending";
                            row.appendChild(cell8);
                            break;
                        case 2:
                            cell8.innerText = "Approved";
                            row.appendChild(cell8);
                            break;
                        case 3:
                            cell8.innerText = "Denied";
                            row.appendChild(cell8);
                            break;
                    }
                    row.appendChild(cell8);          
                let cell9 = document.createElement("td");
                    switch (reimbursement.typeId.typeId) {
                        case 1:
                            cell9.innerText = "Lodging";
                            break;
                        case 2:
                            cell9.innerText = "Travel";
                            break;
                        case 3:
                            cell9.innerText = "Food";
                            break;
                        case 4:
                            cell9.innerText = "Other";
                        break;
                    }
                row.appendChild(cell9);
                document.getElementById("rbody1").appendChild(row);
            }
        }
    }

   //Add Reimbursement 
   async function addFunc() {
    console.log("@addFunc");
    let amount = document.getElementById("amount").value;
    let description = document.getElementById("description").value;
   
    const types = document.querySelectorAll('input[name="type"]');
    //document.getElementById("employeeportal").style.display = "none";
    let typeId;
    for (const t of types) {
        if (t.checked) {
            typeId = t.value;
            break;
        }
    }
    console.log(typeId);
    
    let rReq = {
        amount: amount,
        description: description,
        typeId: typeId,
        statusId: 1,
        author: usern,       
    }

    console.log(rReq)

    let resp = await fetch(url + "addR", {
        method: 'POST',
        body: JSON.stringify(rReq),
        credentials: "include"
    })

    console.log(resp);

    if (resp.status === 200) {
        alert('Your request was successfully submitted');
        console.log("Request was successgully submitted.")
    } else {
        console.log("Request was not successfully submitted.");
    }
    
    async function logoutFunc() {
        let resp = await fetch(url + "logout", {
            method: "POST",
            credentials: "include"
        })
        if (resp.status === 200) {
            location.reload();
        } else {
            document.getElementById("new-msg").innerText = "Oops, something went wrong. Please try logging out again.";
        }
    }
}