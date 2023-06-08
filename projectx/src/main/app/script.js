// Function to handle the button click event
function getData() {
    // Make an AJAX request to retrieve the data from the backend
    fetch('http://localhost:8080/api/domains')
      .then(response => response.json())
      .then(data => {
        // Handle the retrieved data
        displayData(data);
      })
      .catch(error => {
        console.error('Error retrieving data:', error);
      });
  }
  
  // Function to display the retrieved data in the table
  function displayData(data) {
    const dataTable = document.getElementById('data-table');
    const tbody = dataTable.querySelector('tbody');
    tbody.innerHTML = '';
  
    if (data && data.length > 0) {
      const idFilter = document.getElementById('idFilter').value.trim();
      const areaFilter = document.getElementById('areaFilter').value.trim();
  
      data.forEach(item => {
        if ((idFilter && !item.id.toString().includes(idFilter))
          || (areaFilter && !item.areaOfStudy.toLowerCase().includes(areaFilter.toLowerCase()))) {
          return; // Skip if ID or Area of Study does not match the filters
        }
  
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
  
  
  
  
  
  function filterData() {
    const idFilter = document.getElementById('idFilter').value.toLowerCase();
    const nameFilter = document.getElementById('nameFilter').value.toLowerCase();
    const areaFilter = document.getElementById('areaFilter').value.toLowerCase();
    const tableBody = document.getElementById('data-table').getElementsByTagName('tbody')[0];
    const rows = tableBody.getElementsByTagName('tr');
  
    for (let i = 0; i < rows.length; i++) {
      const idCell = rows[i].getElementsByTagName('td')[0];
      const nameCell = rows[i].getElementsByTagName('td')[1];
      const areaOfStudyCell = rows[i].getElementsByTagName('td')[2];
  
      const idValue = idCell.textContent || idCell.innerText;
      const nameValue = nameCell.textContent || nameCell.innerText;
      const areaOfStudyValue = areaOfStudyCell.textContent || areaOfStudyCell.innerText;

  
      if (idValue.toLowerCase().indexOf(idFilter) > -1 && nameValue.toLowerCase().indexOf(nameFilter) > -1 && areaOfStudyValue.toLowerCase().indexOf(areaFilter)) {
        rows[i].style.display = '';
      } else {
        rows[i].style.display = 'none';
      }
    }
  }
  
  // Add event listeners for the filter inputs
  document.getElementById('idFilter').addEventListener('input', filterData);
  document.getElementById('nameFilter').addEventListener('input', filterData);
  document.getElementById('areaFilter').addEventListener('input', filterData);

  
  
  // Add an event listener to the button
  const getDataButton = document.getElementById('getDataButton');
  getDataButton.addEventListener('click', getData);
  