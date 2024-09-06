function loadPartial(elementId, partialUrl) {
    fetch(partialUrl)
        .then(response => response.text())
        .then(data => document.getElementById(elementId).innerHTML = data)
        .catch(error => console.error('Error al cargar el partial:', error));
}

// Cargar el header y el footer usando la función
loadPartial('header', '../../partials/header.hbs');
loadPartial('footer', '../../partials/footer.hbs');
loadPartial('tipografia', '../../partials/tipografia.hbs');


