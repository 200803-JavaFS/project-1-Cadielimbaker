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

//             //find all reimbursement botton show
//          let button = document.createElement('button');
//             button.className = "btn btn-success";
//             button.id = "viewallreimbursementbtn";
//             button.innerText = "View All Reimbursements";
//             button.onclick=findAllFunc;
//             document.getElementById("table-row").appendChild(button);

                //viewbyreimbursementstatus botton show
//          let button1 = document.createElement('button');
//             button.className = "btn btn-success";
//             button.id = "viewbyreimbursementstatusbtn";
//             button.innerText = "View Reimbursements By Status";
//             button.onclick=viewByReimbursementStatusFunc;
//             document.getElementById("table-row").appendChild(button1);

                 //viewbyreimbursementtype botton show
//          let button3 = document.createElement('button');
//             button.className = "btn btn-success";
//             button.id = "viewbyreimbursementtypebtn";
//             button.innerText = "View Reimbursements By Type";
//             button.onclick=viewByReimbursementStatusFunc;
//             document.getElementById("table-row").appendChild(button3);

                //viewbyreimbid botton show
//          let button4 = document.createElement('button');
//             button.className = "btn btn-success";
//             button.id = "viewbyreimbidbtn";
//             button.innerText = "View By Reimbursement Id";
//             button.onclick=viewByReimbIdFunc;
//             document.getElementById("table-row").appendChild(button4);

    //updateReimbursement button show
//         let button5 = document.createElement('button');
//         button2.className = "btn btn-success";
//         button2.id = "updatereimbursementBtn";
//         button2.innerText = "Update A Reimbursement";
//         button2.onclick = UpdateFunc;
//         document.getElementById("formbtn").appendChild(button5);




//             //addReimbursement button show
//         let button2 = document.createElement('button');
//         button2.className = "btn btn-success";
//         button2.id = "addreimbursementBtn";
//         button2.innerText = "Add A Reimbursement";
//         button2.onclick = AddFunc;
//         document.getElementById("formbtn").appendChild(button2);


//         }else{
//             document.getElementById("login-row").innerText = "LOGIN FAILED!"; 
//         }
// }

// async function findAllFunc() {

//     document.getElementById("Reimbursementbody").innerText ="";

//     let resp = await fetch(url + "reimbursement", {
//         credentials: "include",
//     });

//     if (resp.status === 200) {
//         let data = await resp.json();
//         for (let reimbursement of data) {
//             console.log(reimbursement);
//             let row = document.createElement("tr");
//             let cell = document.createElement("td");
//             cell.innerHTML = reimbursement.reimbId;
//             row.appendChild(cell);
//             let cell2 = document.createElement("td");
//             cell2.innerHTML = reimbursement.reimbAmount;
//             row.appendChild(cell2);
//             let cell3 = document.createElement("td");
//             cell3.innerHTML = reimbursement.reimbSubmitted;
//             row.appendChild(cell3);
//             let cell4 = document.createElement("td");
//             cell4.innerHTML = reimburesment.reimbResolved;
//             row.appendChild(cell4);
//             let cell5 = document.createElement("td");
//             cell5.innerHTML = reimbursement.reimbDescription;
//             row.appendChild(cell5);
//             let cell6 = document.createElement("td");
//             cell6.innerHTML = reimbursement.reimbAuthor;
//             row.appendChild(cell6);
//             let cell7 = document.createElement("td");
//             cell7.innerHTML = reimbursement.reimbResolver;
//             row.appendChild(cell7);
//             let cell8 = document.createElement("td");
//             cell8.innerHTML = reimbursement.reimbStatusId;
//             row.appendChild(cell8);
//             let cell9 = document.createElement("td");
//             cell9.innerHTML = reimbursement.reimbTypeId;
//             row.appendChild(cell9);
//            
//             document.getElementById("rbody").appendChild(row);
//         }
//     }
// }

// async function AddFunc(){

//     let sName = document.getElementById("supName").value;
//     let sPower = document.getElementById("supPower").value;
//     let fName = document.getElementById("fName").value;
//     let lName = document.getElementById("lName").value;
//     let pLevel = document.getElementById("pLevel").value;
//     let home = document.getElementById("home").value;

//     let avenger = {
//         supName : sName,
//         supPower : sPower,
//         firstName : fName,
//         lastName : lName,
//         powerLevel : pLevel,
//         homeString : home 
//     }

//     console.log(reimbursement)

//     let resp = await fetch(url + "reimbursement", {
//         method: 'POST',
//         body: JSON.stringify(reimbursement),
//         credentials: "include"
//     })

//     if(resp.status === 201){
//         findAllFunc()
//     } else {
//         document.getElementById("login-row").innerText = "Avenger could not be added.";
//     }
//}