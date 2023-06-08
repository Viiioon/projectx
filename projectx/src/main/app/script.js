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
  
  // Function to display the retrieved data in the container
  function displayData(data) {
    const dataContainer = document.getElementById('dataContainer');
    dataContainer.innerHTML = '';
  
    if (data && data.length > 0) {
      data.forEach(item => {
        const p = document.createElement('p');
        p.textContent = item.name;
        dataContainer.appendChild(p);
      });
    } else {
      const p = document.createElement('p');
      p.textContent = 'No data available';
      dataContainer.appendChild(p);
    }
  }
  
  // Add an event listener to the button
  const getDataButton = document.getElementById('getDataButton');
  getDataButton.addEventListener('click', getData);