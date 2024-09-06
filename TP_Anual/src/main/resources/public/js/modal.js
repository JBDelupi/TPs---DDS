
/* REVISAR PORQUE NO CREA EL MENSAJE DE EXITO*/

function mostrarModalConfirmacion() {
    // Crear la estructura del modal dinámicamente si no existe
    if (!document.getElementById('myModal')) {
        const modalHTML = `
            <div id="myModal" class="modal fade">
                <div class="modal-dialog modal-confirm">
                    <div class="modal-content">
                        <div class="modal-header">
                            <div class="icon-box">
                                <i class="bi bi-check-lg"></i>
                            </div>
                        </div>
                        <div class="modal-body text-center">
                            <h4>Operación Exitosa!</h4>
                            <br>   
                            <p>Tu contribución ha sido registrada con éxito.</p>
                            <a id="confirmButton" href="donacionDeDinero.html" class="btn btn-success">OK</a>
                        </div>
                    </div>
                </div>
            </div>
        `;
        // Insertar el modal en el cuerpo del documento
        document.body.insertAdjacentHTML('beforeend', modalHTML);
    }

    // Mostrar el modal usando Bootstrap
    var myModal = new bootstrap.Modal(document.getElementById('myModal'));
    myModal.show();
}

function confirmarDonacion() {
    mostrarModalConfirmacion();
    return false; // Prevenir el envío del formulario
}