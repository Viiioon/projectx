let originalData = [];

window.onload = async function () {
  try {
    // Load data from API
    const response = await fetch("http://localhost:8080/api/domains");
    const data = await response.json();

    // Save original data for searching
    originalData = data;
    displayData(data);
  } catch (error) {
    console.error("Error retrieving data:", error);
  }

  // Add event listeners for search fields
  document.getElementById("id-input").addEventListener("keyup", (event) => {
    searchAndUpdateDisplay(Number(event.target.value), "id", true);
  });

  document.getElementById("name-input").addEventListener("keyup", (event) => {
    searchAndUpdateDisplay(event.target.value, "name");
  });

  document.getElementById("area-input").addEventListener("keyup", (event) => {
    searchAndUpdateDisplay(event.target.value, "areaOfStudy");
  });
};

// Function to perform a search and update the display
function searchAndUpdateDisplay(query, field, isExactMatch = false) {
  const filteredData = originalData.filter((item) => {
    // If exact match is requested, return true only if field is exactly equal to query.
    if (isExactMatch) {
      return item[field] === query;
    } else {
      // Otherwise, return true if field contains query (case-insensitive).
      return item[field].toString().toLowerCase().includes(query.toLowerCase());
    }
  });

  displayData(filteredData);
}

// Function to display the retrieved data in the table
function displayData(data) {
  const dataTable = document.getElementById("data-table");
  const tbody = dataTable.querySelector("tbody");
  tbody.innerHTML = "";

  if (data && data.length > 0) {
    data.forEach((item) => {
      const row = document.createElement("tr");
      const idCell = document.createElement("td");
      idCell.textContent = item.id;
      const nameCell = document.createElement("td");
      nameCell.textContent = item.name;
      const areaCell = document.createElement("td");
      areaCell.textContent = item.areaOfStudy;
      row.appendChild(idCell);
      row.appendChild(nameCell);
      row.appendChild(areaCell);
      tbody.appendChild(row);
    });
  } else {
    const row = document.createElement("tr");
    const messageCell = document.createElement("td");
    messageCell.setAttribute("colspan", "3");
    messageCell.textContent = "No data available";
    row.appendChild(messageCell);
    tbody.appendChild(row);
  }
}
