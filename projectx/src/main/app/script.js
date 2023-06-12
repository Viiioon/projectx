let originalData = [];

window.onload = async function () {
  try {
    // Load data from API
    const response = await fetch("http://localhost:8080/api/domains");
    const data = await response.json();

    originalData = data;
    displayData(data);
  } catch (error) {
    console.error("Error retrieving data:", error);
  }

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

function searchAndUpdateDisplay(query, field, isExactMatch = false) {
  const filteredData = originalData.filter((item) => {
    if (isExactMatch) {
      return item[field] === query;
    } else {
      return item[field].toString().toLowerCase().includes(query.toLowerCase());
    }
  });

  displayData(filteredData);
}

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

      // Create delete button
      const deleteCell = document.createElement("td");
      const deleteButton = document.createElement("button");
      deleteButton.textContent = "Delete";
      deleteButton.classList.add("btn", "btn-danger");
      deleteButton.onclick = function () {
        deleteDomain(item.id);
      };
      deleteCell.appendChild(deleteButton);

      // Create delete button
      const updateCell = document.createElement("td");
      const updateButton = document.createElement("button");
      updateButton.textContent = "Update";
      updateButton.classList.add("btn", "btn-info");
      updateButton.onclick = function () {
        updateDomain(item.id);
      };
      updateCell.appendChild(updateButton);

      row.appendChild(idCell);
      row.appendChild(nameCell);
      row.appendChild(areaCell);
      row.appendChild(deleteCell);
      row.appendChild(updateCell)
      tbody.appendChild(row);
    });
  } else {
    const row = document.createElement("tr");
    const messageCell = document.createElement("td");
    messageCell.setAttribute("colspan", "4");
    messageCell.textContent = "No data available";
    row.appendChild(messageCell);
    tbody.appendChild(row);
  }
}

async function deleteDomain(id) {
  try {
    const response = await fetch(`http://localhost:8080/api/domains/${id}`, {
      method: "DELETE",
    });

    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    } else {
      // Reload data
      const response = await fetch("http://localhost:8080/api/domains");
      const data = await response.json();
      originalData = data;
      displayData(data);
    }
  } catch (error) {
    console.error("Error:", error);
    alert("There was a problem with the request.");
  }
}

async function updateDomain(id) {
  const name = prompt("Enter the new name");
  const areaOfStudy = prompt("Enter the new area of study");

  try {
    const response = await fetch(`http://localhost:8080/api/domains/${id}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ name, areaOfStudy }),
    });

    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    } else {
      // Reload data
      const response = await fetch("http://localhost:8080/api/domains");
      const data = await response.json();
      originalData = data;
      displayData(data);
    }
  } catch (error) {
    console.error("Error:", error);
    alert("There was a problem with the request.");
  }
}
