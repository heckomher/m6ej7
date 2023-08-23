document.addEventListener("DOMContentLoaded", function() {
    var tipoSelect = document.getElementById("tipoUsuario");

    tipoSelect.addEventListener("change", function() {
        var selectedOption = tipoSelect.value;

        var formCliente = document.getElementById("form-cliente");
        var formProfesional = document.getElementById("form-profesional");
        var formAdministrativo = document.getElementById("form-administrativo");

        switch (selectedOption) {
            case "Cliente":
                formCliente.style.display = "block";
                formProfesional.style.display = "none";
                formAdministrativo.style.display = "none";
                break;

            case "Profesional":
                formProfesional.style.display = "block";
                formCliente.style.display = "none";
                formAdministrativo.style.display = "none";
                break;

            case "Administrativo":
                formAdministrativo.style.display = "block";
                formProfesional.style.display = "none";
                formCliente.style.display = "none";
                break;
        }
    });
});