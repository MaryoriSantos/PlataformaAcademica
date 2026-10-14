/* Control del formulario de inicio de sesión. */
(function() {
    'use strict';
    const form = document.getElementById('loginForm');
    if (!form) return;
    const message = document.getElementById('login-message');
    const submit = document.getElementById('login-submit');

    // Muestra u oculta la contraseña cuando se pulsa Ver/Ocultar.
    document.querySelectorAll('[data-toggle-password]').forEach(function(button) {
        button.addEventListener('click', function() {
            const input = document.getElementById(button.dataset.togglePassword);
            if (!input) return;
            const showing = input.type === 'text';
            input.type = showing ? 'password' : 'text';
            button.textContent = showing ? 'Ver' : 'Ocultar';
            button.setAttribute('aria-label', showing ? 'Mostrar contraseña' : 'Ocultar contraseña');
        });
    });

    // Muestra el resultado del registro anterior, si existe.
    const registered = sessionStorage.getItem('pa_registration_message');
    if (registered) {
        PAApi.showNotice(message, registered, 'success');
        sessionStorage.removeItem('pa_registration_message');
    }

    form.addEventListener('submit', async function(event) {
        event.preventDefault();
        message.hidden = true;
        if (!form.reportValidity()) return;
        const email = document.getElementById('email').value.trim();
        const password = document.getElementById('password').value;
        submit.disabled = true;
        submit.innerHTML = '<span>Verificando...</span><span class="spinner" aria-hidden="true">···</span>';
        try {
            // Este endpoint coincide con el UserController actual del proyecto,
            // que recibe email y password como RequestParam.
            const url = '/user/login?email=' + encodeURIComponent(email) + '&password=' + encodeURIComponent(password);
            await PAApi.request(url, { method: 'POST' });
            localStorage.setItem('pa_authenticated', 'true');
            localStorage.setItem('pa_user_email', email);
            localStorage.setItem('pa_user_name', email.split('@')[0]);
            window.location.href = 'dashboard.html';
        } catch (error) {
            PAApi.showNotice(message, error.message || 'Correo o contraseña incorrectos.', 'error');
        } finally {
            submit.disabled = false;
            submit.innerHTML = '<span>Iniciar sesión</span><span aria-hidden="true">↗</span>';
        }
    });
})();
