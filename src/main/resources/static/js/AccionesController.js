function obtenerAcciones(rango, nombreAccion) {
    axios.get('/acciones/time_series_' + rango + '/' + nombreAccion).then(function (response) {
        document.getElementById("Tabla").innerHTML = "";
        document.getElementById("Data").innerHTML = "";

        var data = response.data;
        var metaDataKeys = Object.keys(data)[0];
        var timeSeries = Object.keys(data)[1];

        var metaData = "{ ";
        for (i in data[metaDataKeys]) {
            metaData += i + ": " + data[metaDataKeys][i] + ",  -  ";
        }
        metaData += "}";

        var table = document.createElement("TABLE");
        table.setAttribute("class", "table");
        var thead = document.createElement("THEAD");
        thead.setAttribute("class", "thead-light");
        var tr = document.createElement("TR");

        var encabezados = ["Fecha", "Open", "Hight", "Low", "Close", "Volume"];
        for (e in encabezados) {
            var th = document.createElement("TH");
            th.setAttribute("scope", "col");
            th.innerHTML = encabezados[e];
            tr.appendChild(th);
        }

        thead.appendChild(tr);
        table.appendChild(thead);

        var tbody = document.createElement("TBODY");
        for (i in data[timeSeries]) {
            tr = document.createElement("TR");
            th = document.createElement("TH");
            th.innerHTML = i;
            tr.appendChild(th);
            for (j in data[timeSeries][i]) {
                td = document.createElement("TD");
                td.innerHTML = data[timeSeries][i][j];
                tr.appendChild(td);
            }
            tbody.appendChild(tr);
        }
        table.appendChild(tbody);
        document.getElementById("Tabla").appendChild(table);
    }).catch(function (error) {
        console.log(error);
        alert("This share does not exist, try again");
    });
}

function setAccion(nombreAccion) {
    document.getElementById("KeyWords").value = nombreAccion;
}