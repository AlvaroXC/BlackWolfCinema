document.addEventListener('DOMContentLoaded', () => {
    cargarSala();
});


const contenedorAsientos = document.querySelector('.asientos');
const abecedario = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];

let precioTotal = 0;
let asientosSeleccionados = [];
let arrayPrecioAsientos = [];
async function cargarSala() {
    try {
        const APIurl = '/cinemaRoom/'+cinemaRoomId;
        const response = await fetch(APIurl);
        const cinemaRoom = await response.json();
        console.log(cinemaRoomId);
        console.log(contenedorAsientos);
        limpiarContenedorAsientos(); // Limpiar el contenedor antes de crear los asientos
        crearAsientos(cinemaRoom);
        console.log('Asientos seleccionados:', asientosSeleccionados);
        console.log('Precios de asientos:', arrayPrecioAsientos);
    } catch (error) {
        console.log(error);
    }
}

function limpiarContenedorAsientos() {
    // Limpiar el contenedor de asientos
    while (contenedorAsientos.firstChild) {
        contenedorAsientos.removeChild(contenedorAsientos.firstChild);
    }
}

function generarImagen(path) {
    if (!path) {
        console.error('La función generate image no tiene una ruta válida.');
        return null;
    }
    var imagenAsiento = document.createElement('img');
    imagenAsiento.setAttribute('src', path);
    imagenAsiento.setAttribute('width', '40px');
    imagenAsiento.setAttribute('height', '40px');
    return imagenAsiento;
}

function crearAsientos(cinemaRoom) {
    let numeroFilas = cinemaRoom.rows;
    let numeroColumnas = cinemaRoom.columns;

    for (let i = 0; i < numeroFilas; i++) {
        const fila = document.createElement('ul');

        for (let j = 1; j <= numeroColumnas; j++) {
            const columna = document.createElement('li');
            const inputAsiento = document.createElement('input');
            inputAsiento.setAttribute('type', 'checkbox');
            inputAsiento.setAttribute('id', `${abecedario[i]}${j}`);
            const labelAsiento = document.createElement('label');
            labelAsiento.setAttribute('for', `${abecedario[i]}${j}`);
            labelAsiento.classList.add(`${abecedario[i]}${j}`);

            labelAsiento.appendChild(generarImagen('/img/availableSeat.png'));

            inputAsiento.addEventListener('click', manejarClickAsiento(i, j, cinemaRoom, labelAsiento));

            columna.appendChild(inputAsiento);
            columna.appendChild(labelAsiento);
            fila.appendChild(columna);
        }

        contenedorAsientos.appendChild(fila);
    }

}

function manejarClickAsiento(i, j, cinemaRoom, labelAsiento) {
    return () => {
        const asientoActual = `${abecedario[i]}${j}`;
        const precioAsiento = cinemaRoom.seatPrice;

        const indiceAsiento = asientosSeleccionados.indexOf(asientoActual);

        if (indiceAsiento !== -1) {
            var asientoid = asientoActual;


            asientosSeleccionados.splice(indiceAsiento, 1);
            precioTotal -= precioAsiento;
            arrayPrecioAsientos.splice(indiceAsiento, 1); // Eliminar el precio del asiento deseleccionado (manejar asiento, primer if)
// Obtener la referencia a la tabla
    const mytable = document.getElementById("mytable");

    // Buscar y eliminar la fila que contiene el asiento deseleccionado
    const rows = mytable.getElementsByTagName("tr");
    for (let i = 0; i < rows.length; i++) {
        const cells = rows[i].getElementsByTagName("td");
        if (cells.length > 0 && cells[0].innerText === asientoActual) {
            mytable.deleteRow(i);
            break;
        }
    }
            if (labelAsiento.firstChild !== null) {
                changeSeatColorWhenIsAvailable(labelAsiento);

            } else {
                console.warn('El nodo hijo de seatLabel es nulo.');
            }
            console.log(`Asiento ${asientoActual} deseleccionado. Precio: ${precioAsiento}. Precio Total: ${precioTotal}`);
            document.getElementById("totalprice").value=precioTotal;
        } else {
            var mytable=document.getElementById("mytable");
            var rows="";
            asientosSeleccionados.push(asientoActual);
            precioTotal += precioAsiento;
            arrayPrecioAsientos.push(precioAsiento); // Almacenar el precio del asiento seleccionado (manejar click asiento, segundo else)
            if (labelAsiento.firstChild !== null) {
                changeSeatColorWhenIsSelected(labelAsiento);
            } else {
                console.warn('El nodo hijo de seatLabel es nulo.');
            }
            console.log(`Asiento ${asientoActual} seleccionado. Precio: ${precioAsiento}. Precio Total: ${precioTotal}`);

            var asientoid = asientoActual;
            var tr=document.createElement("tr");
            rows="<tr></tr><td><input name='seat' id='seat' type='text' value="+asientoid+"></td></tr>"
            tr.innerHTML=rows;
            mytable.appendChild(tr);
            console.log(asientoid);
            document.getElementById("totalprice").value=precioTotal;

        }
    };
}

function changeSeatColorWhenIsAvailable(labelAsiento) {
    labelAsiento.firstChild.replaceWith(generarImagen('/img/availableSeat.png'));
}

function changeSeatColorWhenIsSelected(labelAsiento) {
    labelAsiento.firstChild.replaceWith(generarImagen('/img/selectedSeat.png'));
}

function cargarShow(show){
console.log(showId);

}