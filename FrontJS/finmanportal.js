
document.getElementById("findfuncbtn4").addEventListener("click", findAllFunc);
document.getElementById("findfuncbtn5").addEventListener("click", findByStatusFunc);
document.getElementById("findfuncbtn6").addEventListener("click", findByEmployeeFunc);
document.getElementById("findfuncbtn7").addEventListener("click", updateFunc);
document.getElementById("findfuncbtn8").addEventListener("click", updateFunc);

async function findAllFunc() {
    console.log("@findAllFunc");

    document.getElementById("rbody").innerText = "";

    let resp = await fetch(url+"reimbursement" , {
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
                    default:
                        cell8innerText = "Pending";
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

    async function findByEmployeeFunc(){
        console.log("@findEmployeeFunc");

    document.getElementById("rbody2").innerText = "";

    let employee = document.getElementById("empIdInput");
    let empId = employee.value;
    console.log("empId = " + empId);

    let resp = await fetch(url+"reimbursementsbyemployee" + "/" + empId  , {
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
                document.getElementById("rbody2").appendChild(row);
            }
        }
    }

        async function updateFunc(){

            let rID = document.getElementById("reimbIdInput");
            let rId = rID.value;
        
            const rSArr = document.querySelectorAll('input[name="status"]');
            let sId;
            for (const sId of rSArr) {
                if (sId.checked) {
                    sId = rSArr.value;
                    break;
                }
            }
        
            let rStatus = {
                reimbId: rId,
                statusId: sId
            }
        
            let resp = await fetch(url+"changestatus" + "/" + rId, {
                method: 'POST',
                body: JSON.stringify(rStatus),
                credentials: "include"
            });
        
            if(resp.status===202) {
                document.getElementById("updateSuccess").innerHTML = "Reimbursement update successful";
            } else {
                document.getElementById("updateSuccess").innerHTML = "Reimbursement update failure";
            }
        }
    
    