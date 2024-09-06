// scripts.js

// main.js

// Función para cargar un partial en un elemento dado
function loadPartial(elementId, partialPath) {
    fetch(partialPath)
        .then(response => response.text())
        .then(data => {
            document.getElementById(elementId).innerHTML = data;
        })
        .catch(error => console.error('Error al cargar el partial:', error));
}

// Cargar el header y el footer usando la función
document.addEventListener('DOMContentLoaded', function() {
    loadPartial('header', '/../partials/header.hbs');
    loadPartial('footer', '/../partials/footer.hbs');
});
