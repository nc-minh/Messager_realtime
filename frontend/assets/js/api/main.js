var api = 'http://localhost:3000/courses';

function GETDATA(data){
        
        fetch(api)
        .then(function (response) {
            return response.json();
        })
        .then(data)
        .catch(function(error) {
            console.log("đã xảy ra lỗi!")
            console.log(error);
        });
}
GETDATA(render);

function POSTDATA(){
    let POST = document.getElementById('Create');

    POST.addEventListener('click',()=>{
        var option = {
            method: 'POST',
            body: JSON.stringify({
                "name": document.getElementById('name').value,
                "description": document.getElementById('desc').value,
                // "id": document.getElementById('id').value,
            }),
            headers: {
                "content-type": "application/json; charset=UTF-8"
            }
        };
        
        fetch(api, option)
        .then(function (response) {
            if(response.status == 500){
                alert('trùng id');
            }
            return response.json();
        })
        .then(function (data) {
            return console.log(data);
        })
        .catch(function(error) {
            console.log("đã xảy ra lỗi!")
            console.log(error);
        });

    })
}
POSTDATA();

function UPDATE(){
    let UPDATE = document.getElementById('Update');

    UPDATE.addEventListener('click',()=>{
        var option = {
            method: 'PUT',
            body: JSON.stringify({
                "name": document.getElementById('name').value,
                "description": document.getElementById('desc').value,
                "id": document.getElementById('id').value,
            }),
            headers: {
                "content-type": "application/json; charset=UTF-8"
            }
        };
        
        fetch(api, option)
        .then(function (response) {
            return response.json();
        })
        .then(function (data) {
            return console.log(data);
        })
        .catch(function(error) {
            console.log("đã xảy ra lỗi!")
            console.log(error);
        });
    })
}
UPDATE();

function DEL(){
    let DEL = document.getElementById('Delete');
    

    DEL.addEventListener('click',()=>{
        var option = {
            method: 'DELETE'
        };
        let tet = document.getElementById('iddelete').value;
        apidel = api + "/" + tet;
        console.log(apidel);
        fetch(apidel, option)
        .then(function (response) {
            return response.json();
        })
        .then(function (data) {
            return console.log(data);
        })
        .catch(function(error) {
            console.log("đã xảy ra lỗi!")
            console.log(error);
        });
    })
}
DEL();


function render(data){
    let UI = document.getElementById('data');
    console.log(data);
    var htmls = data.map((element)=>{
        return `
        <li>
            <h5>${element.name}</h5>
            <h5>${element.description}</h5>
        </li>
        `;
    })
    console.log(htmls)
    UI.innerHTML = htmls.join('')
}