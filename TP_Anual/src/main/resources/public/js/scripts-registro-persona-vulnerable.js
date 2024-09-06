document.getElementById('menoresACargo').addEventListener('change', function() {
    var cantidadMenoresContainer = document.getElementById('cantidadMenoresContainer');
    if (this.value === 'si') {
        cantidadMenoresContainer.style.display = 'block';
    } else {
        cantidadMenoresContainer.style.display = 'none';
        document.getElementById('cantidadMenores').value = ''; // Limpia el campo si se oculta
    }
});
