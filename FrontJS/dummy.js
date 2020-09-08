const url = "http://localhost:8080/project1/"

document.getElementById("employeeportal").style.display = "none";
document.getElementById("newreqform").style.display = "none";
document.getElementById("showreimbursement").style.display = "none";
document.getElementById("loginbtn").addEventListener("click", loginFunc);

//upon conferring with Nikki - global scoped variables
var usern;
var userRole;
var reimbursement;

async function loginFunc() {
    console.log("@loginFunc");

    usern = document.getElementById("username").value;
    let userp = document.getElementById("password").value;

    let user = {
        username: usern,
        password: userp
    }

    //console.log(user);

    let resp = await fetch(url + "login", {
        method: 'POST',
        body: JSON.stringify(user),
        credentials: 'include'
    })

    console.log("feth api login response");
    console.log(resp);

    if (resp.status == 200) {
        document.getElementById("new-msg").innerText = "You have successfully logged in.";
        let data = await resp.json();
        console.log(resp);
        uRoleId = data;
        sessionStorage.setItem("uRoleId", uRoleId);
        //console.log(uRoleId);
        if (uRoleId == 1) {
            showEmployeePage();
    
        } else if (uRoleId == 2) {
            showFinanceManPage();
            
        } else { console.log("Holy Cow, Batman!"); }
    } else {
        resetLogin();
    }
}

function showEmployeePage(){
    document.getElementById("login").style.display = "none";
    document.getElementById("employeeportal").style.display = "none";
    document.getElementById("showreimbursement").style.display = "none";
    //document.getElementById("newreqsubmit").addEventListener("click", getEmpAxnFunc);
}

function getEmpAxnFunc(){
    const axns = document.querySelectorAll('input[name="empaxn"]');
    let axn;
    for (const a of axns) {
        if (a.checked) {
            axn = a.value;
            break;
        }
    }
    console.log("axn = " + axn);

    switch (axn) {
        case "1":
            console.log("@switch case 1 in getEmplAxnFunc");
            document.getElementById("employeeportal").style.display = "none";
            document.getElementById("newreqform").style.display = "block";
            document.getElementById("submitnewrequest").addEventListener("click", addFunc);
            getRByUserFunc();
            //document.getElementById("newreqform").style.display = "none";
            break;
        case "2":
            viewPendingFunc()
            break;
        case "3":
            //addFuxn();
            break;
        case "4":
            logoutFuxn();
            break;
    }
}

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
        showRFunc();
    } else {
        console.log("Request was not successfully submitted.");
    }
}

//let uId = sessionStorage.getItem("uId");

document.getElementById("getReimbsByUserBtn").addEventListener("click", getRByUserFunc);

document.getElementById("addReimbBtn").addEventListener("click", addReimbFunc);

async function getRByUserFunc() {

    document.getElementById("rbody").innerText = "";
    let uId = sessionStorage.getItem("uId");
    console.log(uId);

    let resp = await fetch(url+"reimbursementsbyauthor"+uId, {
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
            cell2.innerHTML = reimbursement.amoumt;
            row.appendChild(cell2);
            let cell3 = document.createElement("td");
            let stime = new Date(reimbursement.submitted);
            cell3.innerHTML = stime.toLocaleDateString();
            row.appendChild(cell3);
            if (reimbursement.resolved != null){
                let cell4 = document.createElement("td");
                let rtime = new Date(reimbursement.resolved);
                cell4.innerHTML = rtime.toLocaleDateString();
                row.appendChild(cell4);
            } else {
                let cell4 = document.createElement("td");
                row.appendChild(cell4);
            }
            let cell5 = document.createElement("td");
            cell5.innerHTML = reimbursement.description;
            row.appendChild(cell5);
            let cell6 = document.createElement("td");
            cell6.innerHTML = reimbursement.reimbAuthorId.userId;
            row.appendChild(cell6);
            if (reimbursement.reimbResolverId != null){
                let cell7 = document.createElement("td");
                cell7.innerHTML = reimbursement.reimbResolverId.userId;
                row.appendChild(cell7);
            } else {
                let cell7 = document.createElement("td");
                row.appendChild(cell7);
            }
            let cell8 = document.createElement("td");
            cell8.innerHTML = reimbursement.reimbStatusId.statusId;
            row.appendChild(cell8);
            let cell9 = document.createElement("td");
            cell9.innerHTML = reimbursement.reimbTypeId.typeId;
            row.appendChild(cell9);
            document.getElementById("reimbbody").appendChild(row);
        }
    }
}

async function addReimbFunc(){

    let rAmt = document.getElementById("reimbamt").value;
    let rDesc = document.getElementById("reimbdesc").value;

    const rTypes = document.querySelectorAll('input[name="gridRadios"]');
    let rTypeChoice;
    for (const rType of rTypes) {
        if (rType.checked) {
            rTypeChoice = rType.value;
            break;
        }
    }

    let reimb = {
        amt: rAmt,
        description: rDesc,
        rAuthorId: uId,
        rType: rTypeChoice
    }

    let resp = await fetch(url+"reimbursements", {
        method: 'POST',
        body: JSON.stringify(reimb),
        credentials: "include"
    });

    if(resp.status===201) {
        getReimbsByUserFunc();
    } else {
        document.getElementById("table-row").innerHTML = "Reimbursement couldn't be added";
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
function resetLogin() {
    console.log("@resetLogin");
    document.getElementById("new-msg").innerText = "Oops, something went wrong. Please try logging in again.";
    //location.reload();
}