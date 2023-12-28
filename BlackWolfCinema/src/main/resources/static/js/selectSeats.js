// Event listener for when the DOM is fully loaded
document.addEventListener('DOMContentLoaded', async () => {
    try {
        const soldSeats = await fetchSoldSeats();
        loadCinemaRoom(soldSeats);
    } catch (error) {
        console.error(error);
    }
});


// Reference to the container of seats in the HTML
const seatContainer = document.querySelector('.asientos');

// Array representing the alphabet to label rows
const alphabet = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];

// Variables to track the total price and selected seats
let totalPrice = 0;
let selectedSeatArray = [];
let seatPriceArray = [];

// Function to load cinema room details
async function loadCinemaRoom(soldSeats) {
    try {
        const APIurl = '/api/cinemaRoom/' + cinemaRoomId;
        const response = await fetch(APIurl);
        const cinemaRoom = await response.json();
        clearSeatContainer(); // Clear the seat container before creating seats
        generateSeats(cinemaRoom, soldSeats);
        console.log('Asientos seleccionados:', selectedSeatArray);
        console.log('Precios de asientos:', seatPriceArray);
    } catch (error) {
        console.log(error);
    }
}

async function fetchSoldSeats() {
    try {
        const APIurl = '/getSaleTickets/' + showId;
        const response = await fetch(APIurl);
        let soldSeats = await response.json();

        if (!Array.isArray(soldSeats)) {
            soldSeats = [soldSeats];
        }

        return soldSeats;
    } catch (error) {
        throw error;
    }
}


// Function to clear the seat container
function clearSeatContainer() {
    while (seatContainer.firstChild) {
        seatContainer.removeChild(seatContainer.firstChild);
    }
}

// Function to generate an image element
function creatSeatImage(path) {
    if (!path) {
        console.error('La ruta de la imagen no es correcta');
        return null;
    }
    var imagenAsiento = document.createElement('img');
    imagenAsiento.setAttribute('src', path);
    imagenAsiento.setAttribute('width', '40px');
    imagenAsiento.setAttribute('height', '40px');
    return imagenAsiento;
}

// Function to create seats based on cinema room details
function generateSeats(cinemaRoom, seats) {
    let numeroFilas = cinemaRoom.rows;
    let numeroColumnas = cinemaRoom.columns;

    for (let i = 0; i < numeroFilas; i++) {
        const fila = document.createElement('ul');

        for (let j = 1; j <= numeroColumnas; j++) {
            const columna = document.createElement('li');
            const inputAsiento = document.createElement('input');
            inputAsiento.setAttribute('type', 'checkbox');
            inputAsiento.setAttribute('id', `${alphabet[i]}${j}`);
            const labelAsiento = document.createElement('label');
            labelAsiento.setAttribute('for', `${alphabet[i]}${j}`);
            labelAsiento.classList.add(`${alphabet[i]}${j}`);

            // console.log(seats.includes(inputAsiento.getAttribute('id')))
            // console.log(inputAsiento.getAttribute('id'));
            
            if(seats.includes(inputAsiento.getAttribute('id'))){
                inputAsiento.setAttribute('disabled', true)
                labelAsiento.appendChild(creatSeatImage('/img/asiento-ocupado.png'))
            }else{
                labelAsiento.appendChild(creatSeatImage('/img/asiento-tradicional.png'));
            }

            //Add click event listener to handle seat clicks
            inputAsiento.addEventListener('click', handleSeatClick(i, j, cinemaRoom, labelAsiento));

            columna.appendChild(inputAsiento);
            columna.appendChild(labelAsiento);
            fila.appendChild(columna);
        }

        seatContainer.appendChild(fila);
    }
}

function handleSeatClick(i, j, cinemaRoom, labelAsiento) {
    return () => {
        const asientoActual = `${alphabet[i]}${j}`;
        const precioAsiento = cinemaRoom.seatPrice;
        const indiceAsiento = selectedSeatArray.indexOf(asientoActual);

        var mytable = document.getElementById("mytable");

        if (indiceAsiento == -1) {
            // Select the seat
            var rows = "";
            selectedSeatArray.push(asientoActual);
            console.log(selectedSeatArray)
            totalPrice += precioAsiento;
            seatPriceArray.push(precioAsiento);

            // Change seat color to selected in the interface
            if (labelAsiento.firstChild !== null) {
                changeSeatToSelected(labelAsiento);
            } else {
                console.warn('Error, el asiento ya ha sido seleccionado');
            }

            // Log information to the console
            console.log(`Asiento ${asientoActual} seleccionado. Precio: ${precioAsiento}. Precio Total: ${totalPrice}`);

            // Create a row in the table showing the selected seat
            var tr = document.createElement("tr");
            // Assign a unique identifier to the row
            tr.id = `row_${asientoActual}`;
            rows = `<tr><td><input name='seat' id='seat' type='text' value='${asientoActual}'></td></tr>`;
            tr.innerHTML = rows;
            mytable.appendChild(tr);

            // Log the seat identifier to the console
            console.log(asientoActual);
            // Update the total price field in the form
            // document.getElementById("totalPrice").value = totalPrice;

        } else {
            // Deselect the seat
            selectedSeatArray.splice(indiceAsiento, 1);
            totalPrice -= precioAsiento;
            seatPriceArray.splice(indiceAsiento, 1);

            // Find and remove the row containing the deselected seat from the table
            var rowToRemove = document.getElementById(`row_${asientoActual}`);
            if (rowToRemove) {
                mytable.removeChild(rowToRemove);
            }

            // Change seat color to available in the interface
            if (labelAsiento.firstChild !== null) {
                changeSeatToAvailable(labelAsiento);
            } else {
                console.warn('Error, el asiento ya ha sido seleccionado');
            }

            // Log the information to the console
            console.log(`Asiento ${asientoActual} deseleccionado. Precio: ${precioAsiento}. Precio Total: ${totalPrice}`);

            // Update the total price field in the form
            // document.getElementById("totalPrice").value = totalPrice;
            
        }
    };
}



// Function to change seat color when it's available
function changeSeatToAvailable(labelAsiento) {
    labelAsiento.firstChild.replaceWith(creatSeatImage('/img/asiento-tradicional.png'));
}

// Function to change seat color when it's selected
function changeSeatToSelected(labelAsiento) {
    labelAsiento.firstChild.replaceWith(creatSeatImage('/img/asiento-seleccionado.png'));
}

