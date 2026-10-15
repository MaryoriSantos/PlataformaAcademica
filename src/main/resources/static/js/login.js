
/* Control del formulario de inicio de sesión. */
(function () {
    'use strict';

    const form = document.getElementById('loginForm');

    if (!form) return;

    const message = document.getElementById('login-message');
    const submit = document.getElementById('login-submit');

    // Muestra u oculta la contraseña.
    document.querySelectorAll('[data-toggle-password]').forEach(function (button) {
        button.addEventListener('click', function () {
            const input = document.getElementById(button.dataset.togglePassword);

            if (!input) return;

            const showing = input.type === 'text';
            input.type = showing ? 'password' : 'text';
            button.textContent = showing ? 'Ver' : 'Ocultar';
            button.setAttribute(
                'aria-label',
                showing ? 'Mostrar contraseña' : 'Ocultar contraseña'
            );
        });
    });

    // Muestra un aviso si el usuario acaba de registrarse.
    const registered = sessionStorage.getItem('pa_registration_message');

    if (registered) {
        PAApi.showNotice(message, registered, 'success');
        sessionStorage.removeItem('pa_registration_message');
    }

    // Envía las credenciales al backend.
    form.addEventListener('submit', async function (event) {
        event.preventDefault();
        message.hidden = true;

        if (!form.reportValidity()) return;

        const email = document.getElementById('email').value.trim();
        const password = document.getElementById('password').value;

        submit.disabled = true;
        submit.innerHTML =
            '<span>Verificando...</span><span class="spinner" aria-hidden="true">···</span>';

        try {
            // Envía el correo y la contraseña en el cuerpo JSON.
            const response = await PAApi.request('/user/login', {
                method: 'POST',
                body: {
                    email: email,
                    password: password
                }
            });

            // Comprueba que el backend haya devuelto el token JWT.
            const token = response.data && response.data.token;

            if (!token) {
                throw new Error('El servidor no devolvió el token de autenticación.');
            }

            // Guarda el token para las solicitudes autenticadas.
            sessionStorage.setItem('pa_token', token);

            // Conserva los datos necesarios para mostrar el panel.
            localStorage.setItem('pa_authenticated', 'true');
            localStorage.setItem('pa_user_email', response.data.email || email);
            localStorage.setItem('pa_user_name', email.split('@')[0]);

            // Abre el panel del estudiante.
            window.location.href = 'dashboard.html';

        } catch (error) {
            PAApi.showNotice(
                message,
                error.message || 'Correo o contraseña incorrectos.',
                'error'
            );
        } finally {
            submit.disabled = false;
            submit.innerHTML =
                '<span>Iniciar sesión</span><span aria-hidden="true">↗</span>';
        }
    });
})();
