const url = "http://localhost:8080/project1/";

let usern = sessionStorage.getItem("uname");

document.getElementById("findfuncbtn").addEventListener("click", findByEmployeeFunc);
document.getElementById("findfuncbtn1").addEventListener("click", findByStatusFunc);
document.getElementById("findfuncbtn2").addEventListener("click", addFunc);
document.getElementById("submitnewrequest").addEventListener("click", addFunc);
document.getElementById("findfuncbtn3").addEventListener("click", logoutFunc);




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
            cell2.innerHTML = reimbursement.reimbAmount;
            row.appendChild(cell2);
            let cell3 = document.createElement("td");
            cell3.innerHTML =reimbursement.reimbSubmitted;
            row.appendChild(cell3);
            if (reimbursement.reimbResolved != null){
                let cell4 = document.createElement("td");
                cell4.innerHTML = reimbursement.reimbResolved;
                row.appendChild(cell4);
            } else {
                let cell4 = document.createElement("td");
                row.appendChild(cell4);
            }
            let cell5 = document.createElement("td");
            cell5.innerHTML = reimbursement.reimbDescription;
            row.appendChild(cell5);
            let cell6 = document.createElement("td");
            cell6.innerHTML = reimbursement.reimbAuthor;
            row.appendChild(cell6);
            if (reimbursement.reimbResolver != null){
                let cell7 = document.createElement("td");
                cell7.innerHTML = reimbursement.reimbResolver;
                row.appendChild(cell7);
            } else {
                let cell7 = document.createElement("td");
                row.appendChild(cell7);
            }
            let cell8 = document.createElement("td");
                switch (reimbursement.reimbStatusId) {
                    case 1:
                        cell8innerText = "PENDING";
                        row.appendChild(cell8);
                        break;
                    case 2:
                        cell8.innerText = "APPROVED";
                        row.appendChild(cell8);
                        break;
                    case 3:
                        cell8.innerText = "DENIED";
                        row.appendChild(cell8);
                        break;
                }
                row.appendChild(cell8);          
            let cell9 = document.createElement("td");
                switch (reimbursement.reimbTypeId.reimbTypeId) {
                    case 1:
                        cell9.innerText = "LODGING";
                        break;
                    case 2:
                        cell9.innerText = "TRAVEL";
                        break;
                    case 3:
                        cell9.innerText = "FOOD";
                        break;
                    case 4:
                        cell9.innerText = "OTHER";
                    break;
                }
            row.appendChild(cell9);
            document.getElementById("rbody1").appendChild(row);
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
                cell2.innerHTML = reimbursement.reimbAmount;
                row.appendChild(cell2);
                let cell3 = document.createElement("td");
                cell3.innerHTML =reimbursement.reimbSubmitted;
                row.appendChild(cell3);
                if (reimbursement.reimbResolved != null){
                    let cell4 = document.createElement("td");
                    cell4.innerHTML = reimbursement.reimbResolved;
                    row.appendChild(cell4);
                } else {
                    let cell4 = document.createElement("td");
                    row.appendChild(cell4);
                }
                let cell5 = document.createElement("td");
                cell5.innerHTML = reimbursement.reimbDescription;
                row.appendChild(cell5);
                let cell6 = document.createElement("td");
                cell6.innerHTML = reimbursement.reimbAuthor.usersId;
                row.appendChild(cell6);
                if (reimbursement.reimbResolver != null){
                    let cell7 = document.createElement("td");
                    cell7.innerHTML = reimbursement.reimbResolver.usersId;
                    row.appendChild(cell7);
                } else {
                    let cell7 = document.createElement("td");
                    row.appendChild(cell7);
                }
                let cell8 = document.createElement("td");
                    switch (reimbursement.reimbStatusId.reimbStatusId) {
                        case 1:
                            cell8innerText = "PENDING";
                            row.appendChild(cell8);
                            break;
                        case 2:
                            cell8.innerText = "APPROVED";
                            row.appendChild(cell8);
                            break;
                        case 3:
                            cell8.innerText = "DENIED";
                            row.appendChild(cell8);
                            break;
                    }
                    row.appendChild(cell8);          
                let cell9 = document.createElement("td");
                    switch (reimbursement.reimbTypeId.reimbTypeId) {
                        case 1:
                            cell9.innerText = "LODGING";
                            break;
                        case 2:
                            cell9.innerText = "TRAVEL";
                            break;
                        case 3:
                            cell9.innerText = "FOOD";
                            break;
                        case 4:
                            cell9.innerText = "OTHER";
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
    let reimbAmount = document.getElementById("amount").value;
    let reimbDescription = document.getElementById("description").value;
   
    const types = document.querySelectorAll('input[name="type"]');
    
    let reimbTypeId;
    for (const t of types) {
        if (t.checked) {
            reimbTypeId = t.value;
            break;
        }
    }
    console.log(reimbTypeId);
    
    let rReq = {
        reimbAmount: reimbAmount,
        reimbDescription: reimbDescription,
        reimbTypeId: reimbTypeId,
        reimbStatusId: 1,
        reimbAuthor: usern,       
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
        console.log("Request was successfully submitted.")
    } else {
        console.log("Request was not submitted correctly.");
    }

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
