/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

export function func(){
    console.log("func");
    funcAsync("data")
    .then(function(answer){
        console.log(answer);
    });
}

async function funcAsync(data){
    let response = await fetch("http://localhost:8080/GenTree/APP/register", {method: "POST", headers: {'Content-Type': 'text/plain;charset=utf-8'}, body: data});
    return response.text();
}