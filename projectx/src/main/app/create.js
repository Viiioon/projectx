document.getElementById('create-category-form').addEventListener('submit', async function(event) {
    event.preventDefault();
  
    const name = document.getElementById('name-input').value;
    const areaOfStudy = document.getElementById('area-input').value;
  
    const newCategory = {
      name,
      areaOfStudy
    };
  
    try {
      const response = await fetch('http://localhost:8080/api/domains', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(newCategory),
      });
  
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      } else {
        alert('New category created successfully!');
      }
    } catch (error) {
      console.error('Error:', error);
      alert('There was a problem with the request.');
    }
  });
  