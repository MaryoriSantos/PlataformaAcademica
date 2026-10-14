/* Control del formulario para crear una cuenta de estudiante. */
(function () {
    'use strict';
    const form = document.getElementById('registerForm');
    if (!form) return;
    const message = document.getElementById('register-message');
    const submit = document.getElementById('register-submit');

    form.addEventListener('submit', async function (event) {
        event.preventDefault();
        message.hidden = true;
        if (!form.reportValidity()) return;

        const firstName = document.getElementById('firstName').value.trim();
        const lastName = document.getElementById('lastName').value.trim();
        const email = document.getElementById('registerEmail').value.trim();
        const password = document.getElementById('registerPassword').value;
        const confirmPassword = document.getElementById('confirmPassword').value;

        if (password.length < 6) {
            PAApi.showNotice(message, 'La contraseña debe tener al menos 6 caracteres.', 'error');
            return;
        }
        if (password !== confirmPassword) {
            PAApi.showNotice(message, 'Las contraseñas no coinciden.', 'error');
            return;
        }

        // El payload respeta los atributos de User y la relación con Role.
        // El ID 1 se usa como rol estudiante solo si existe en la base de datos.
        const payload = {
            firstName: firstName,
            lastName: lastName,
            email: email,
            password: password,
            createdAt: new Date().toISOString(),
            status: 1,
            role: { id: 1 }
        };
        submit.disabled = true;
        submit.innerHTML = '<span>Creando cuenta...</span><span aria-hidden="true">···</span>';
        try {
            const result = await PAApi.post('/user', payload);
            // El servicio actual devuelve null cuando el correo ya existe.
            if (result.data === null || result.raw.trim() === '' || result.raw.trim() === 'null') {
                throw new Error('No se creó la cuenta. Puede que el correo ya esté registrado; verifica el correo e inténtalo de nuevo.');
            }
            sessionStorage.setItem('pa_registration_message', 'Cuenta creada. Ya puedes iniciar sesión.');
            window.location.href = 'index.html';
        } catch (error) {
            let details = error.message || 'No se pudo completar el registro.';
            if (/foreign key|constraint|constraintviolation|roles/i.test(details)) {
                details = 'No se pudo asignar el rol de estudiante. Verifica en MySQL que exista el rol con id 1 y que corresponda a Estudiante.';
            }
            PAApi.showNotice(message, details, 'error');
        } finally {
            submit.disabled = false;
            submit.innerHTML = '<span>Crear cuenta</span><span aria-hidden="true">↗</span>';
        }
    });
})();
