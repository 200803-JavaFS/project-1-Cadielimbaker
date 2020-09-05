const url = "http://localhost:8080/project1/"

document.getElementById("findallreimbursementstatus").addEventListener("click", findallreimbursementstatusFunc);


    
    // //get all reimbursements by status button
    // let button = document.createElement('button');
    // button.className = "btn btn-success";
    // button.id = "findallreimbursementstatusBtn";
    // button.innerText = "Find All Reimbursements By Status";
    // button.onclick = findallreimbursementstatusFunc;
    // document.getElementById("table-row").appendChild(button);
    


async function findallreimbursementstatusFunc() {

    document.getElementById("rsbody").innerText ="";

    let resp = await fetch(url + "reimbursement" + "findallreimbursementstatus" + reimbStatusId, {
        method: 'GET',
        credentials: 'include'
    });
    
    let data = await resp.json();
    console.log(data);
    var reimbStatus;
    reimbStatus = data.reimbStatusId;

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
           
            document.getElementById("rsbody").appendChild(row);
        }
    }
}
