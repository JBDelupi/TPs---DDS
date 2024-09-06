// scripts.js

// Función para cargar un partial en un elemento dado
function loadPartial(elementId, partialUrl) {
    fetch(partialUrl)
        .then(response => response.text())
        .then(data => document.getElementById(elementId).innerHTML = data)
        .catch(error => console.error('Error al cargar el partial:', error));
}

// Cargar el header y el footer usando la función
loadPartial('testimonial', 'testimonial.hbs');
loadPartial('header', '../partials/header.hbs');
loadPartial('footer', '../partials/footer.hbs');
loadPartial('tipografia', '../partials/tipografia.hbs');
loadPartial('about', 'about.hbs');
loadPartial('services', 'services.hbs');
loadPartial('team', 'team.hbs');