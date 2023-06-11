window.onload = function() {
  // Function to handle the loading event
  fetch('http://localhost:8080/api/domains')
    .then(response => response.json())
    .then(data => {
      // Handle the retrieved data
      displayData(data);
    })
    .catch(error => {
      console.error('Error retrieving data:', error);
    });
};

// Function to display the retrieved data in the table
function displayData(data) {
  const dataTable = document.getElementById('data-table');
  const tbody = dataTable.querySelector('tbody');
  tbody.innerHTML = '';

  if (data && data.length > 0) {
    data.forEach(item => {
      const row = document.createElement('tr');
      const idCell = document.createElement('td');
      idCell.textContent = item.id;
      const nameCell = document.createElement('td');
      nameCell.textContent = item.name;
      const areaCell = document.createElement('td');
      areaCell.textContent = item.areaOfStudy;
      row.appendChild(idCell);
      row.appendChild(nameCell);
      row.appendChild(areaCell);
      tbody.appendChild(row);
    });
  } else {
    const row = document.createElement('tr');
    const messageCell = document.createElement('td');
    messageCell.setAttribute('colspan', '3');
    messageCell.textContent = 'No data available';
    row.appendChild(messageCell);
    tbody.appendChild(row);
  }
}
