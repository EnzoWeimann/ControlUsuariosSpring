const modal = document.getElementById("modalAgregarCliente");
const formulario = document.getElementById("formularioModal");

modal.addEventListener('hidden.bs.modal', () => {
     formulario.reset();
})