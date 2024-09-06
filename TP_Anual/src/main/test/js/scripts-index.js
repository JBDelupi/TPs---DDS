// scripts.js

// Función para cargar un partial en un elemento dado
function loadPartial(elementId, partialUrl) {
    fetch(partialUrl)
        .then(response => response.text())
        .then(data => document.getElementById(elementId).innerHTML = data)
        .catch(error => console.error('Error al cargar el partial:', error));
}

// Cargar el header y el footer usando la función
loadPartial('testimonial', 'testimonial.html');
loadPartial('header', '/../partials/header.html');
loadPartial('footer', '/../partials/footer.html');
loadPartial('tipografia', '/../partials/tipografia.html');
loadPartial('about', 'about.html');
loadPartial('services', 'services.html');
loadPartial('team', 'team.html');