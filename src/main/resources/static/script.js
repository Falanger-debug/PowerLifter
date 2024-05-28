const apiUrl = 'http://localhost:8080/elevator'; // Zaktualizuj URL, jeśli jest inny

async function initializeElevators() {
    const numberOfElevators = document.getElementById('numElevators').value;
    try {
        await fetch(`${apiUrl}/initialize?numberOfElevators=${numberOfElevators}`, { method: 'POST' });
        alert('Elevators initialized successfully');
        await fetchStatus();
    } catch (error) {
        alert('Failed to initialize elevators: ' + error.message);
    }
}

async function fetchStatus() {
    try {
        const response = await fetch(`${apiUrl}/status`);
        const status = await response.json();
        displayStatus(status);
    } catch (error) {
        alert('Failed to fetch status: ' + error.message);
    }
}

function displayStatus(status) {
    const elevatorStatusDiv = document.getElementById('elevatorStatus');
    elevatorStatusDiv.innerHTML = '<h2>Elevator Status</h2>';
    status.forEach(([id, currentFloor, destinationFloors]) => {
        const elevatorDiv = document.createElement('div');
        elevatorDiv.className = 'elevator';
        elevatorDiv.innerHTML = `
            <p>Elevator ${id}</p>
            <p>Current Floor: ${currentFloor}</p>
            <p>Destination Floors: ${destinationFloors.join(', ')}</p>
        `;
        elevatorStatusDiv.appendChild(elevatorDiv);
    });
}

async function step() {
    try {
        await fetch(`${apiUrl}/step`, { method: 'POST' });
        await fetchStatus();
    } catch (error) {
        alert('Failed to step: ' + error.message);
    }
}

// Fetch initial status when the page loads
document.addEventListener('DOMContentLoaded', fetchStatus);
