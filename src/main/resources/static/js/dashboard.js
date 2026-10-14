/* Navegación, resumen y gestión de los módulos de la Plataforma Académica. */
(function() {
    'use strict';
    if (localStorage.getItem('pa_authenticated') !== 'true') {
        window.location.replace('index.html');
        return;
    }

    const content = document.getElementById('app-content');
    const titleElement = document.getElementById('topbar-title');
    const nav = document.getElementById('side-nav');
    const toastRegion = document.getElementById('toast-region');
    const userEmail = localStorage.getItem('pa_user_email') || 'Usuario';
    const userName = localStorage.getItem('pa_user_name') || userEmail.split('@')[0];
    document.getElementById('current-user-name').textContent = userName;
    document.getElementById('current-user-email').textContent = userEmail;
    document.getElementById('avatar-initials').textContent = PAApi.initials(userName);

    let currentView = 'overview';
    let currentRows = [];
    let currentModuleKey = null;
    let searchText = '';

    const icons = { users: '♙', roles: '▣', subjects: '▤', nrcs: '◫', nrcEnrollments: '♧', deliverables: '▣', grades: '✓', resources: '▧', topics: '◉', resourceRatings: '☆', topicDifficulties: '⌁', questions: '?', answers: '↳', notifications: '♧', aiRecomendations: '✧' };
    const commonModuleOrder = ['users', 'roles', 'subjects', 'nrcs', 'nrcEnrollments', 'deliverables', 'grades', 'resources', 'topics', 'resourceRatings', 'topicDifficulties', 'questions', 'answers', 'notifications', 'aiRecomendations'];

    function toast(message, type) {
        const item = document.createElement('div');
        item.className = 'toast ' + (type || 'info');
        item.textContent = message;
        toastRegion.appendChild(item);
        window.setTimeout(function() { item.remove(); }, 4800);
    }
    function getModule(key) { return PAApi.modules[key]; }
    function getId(row, module) { return row && row[module.idKey] != null ? row[module.idKey] : ''; }
    function formatValue(value) {
        if (value == null || value === '') return '—';
        if (typeof value === 'object') {
            if (Array.isArray(value)) return value.length + ' registro(s)';
            const id = value.id != null ? value.id : (value.userId != null ? value.userId : (value.topicId != null ? value.topicId : (value.resourceId != null ? value.resourceId : (value.nrcId != null ? value.nrcId : null))));
            return id != null ? 'ID ' + id : '[Relacionado]';
        }
        if (typeof value === 'string' && /^\d{4}-\d\d-\d\dT/.test(value)) {
            const date = new Date(value);
            if (!Number.isNaN(date.getTime())) return date.toLocaleString('es-CO');
        }
        if (value === 1 && /status|isRead/i.test(this && this.key || '')) return 'Sí / activo';
        return String(value);
    }
    function shortText(value, max) {
        const text = formatValue(value);
        return text.length > (max || 70) ? text.slice(0, (max || 70) - 1) + '…' : text;
    }
    function esc(value) { return PAApi.escapeHtml(value); }
    function setActiveNav(view) {
        nav.querySelectorAll('[data-view]').forEach(function(button) { button.classList.toggle('active', button.dataset.view === view); });
    }
    function goView(view) {
        currentView = view;
        const mod = getModule(view);
        titleElement.textContent = mod ? mod.title : 'Inicio';
        setActiveNav(view);
        document.getElementById('sidebar').classList.remove('open');
        if (view === 'overview') renderOverview();
        else if (mod) renderModule(view);
    }

    async function safeCount(endpoint) {
        try {
            const result = await PAApi.get(endpoint);
            return Array.isArray(result.data) ? result.data.length : (result.data == null ? 0 : 1);
        } catch (_) { return null; }
    }
    function statCard(title, count, icon, caption) {
        const shown = count == null ? '—' : count;
        return '<article class="stat-card"><div class="stat-top"><span>' + esc(title) + '</span><span class="stat-icon">' + icon + '</span></div><div class="stat-number">' + shown + '</div><div class="stat-caption">' + esc(caption) + '</div></article>';
    }
    function renderOverviewShell() {
        content.innerHTML = '<div class="welcome-strip"><div><span class="eyebrow">TU ESPACIO ACADÉMICO</span><h1>¡Hola, ' + esc(userName) + '! <span aria-hidden="true">✦</span></h1><p>Este es el resumen de tu plataforma. Accede a los módulos para consultar y gestionar la información registrada en la API.</p></div><span class="welcome-tag">PANEL PRINCIPAL</span></div><div class="stats-grid" id="stats-grid">' + statCard('Usuarios', '…', '♙', 'Registros disponibles') + statCard('Asignaturas', '…', '▤', 'Oferta académica') + statCard('Recursos', '…', '▧', 'Material de apoyo') + statCard('Preguntas', '…', '?', 'Comunidad académica') + '</div><div class="dashboard-grid"><section class="panel"><div class="panel-header"><div><h3>Accesos rápidos</h3><p>Abre un módulo para ver sus registros y operaciones.</p></div></div><div class="quick-links">' + [['subjects', 'Asignaturas', 'Materias y créditos'], ['deliverables', 'Entregables', 'Actividades académicas'], ['resources', 'Recursos', 'Material de estudio'], ['questions', 'Preguntas', 'Participación académica'], ['topics', 'Temas', 'Organización temática'], ['notifications', 'Notificaciones', 'Avisos del sistema']].map(function(entry) { return '<button class="quick-link" data-view="' + entry[0] + '"><span class="quick-link-icon">' + icons[entry[0]] + '</span><span><strong>' + esc(entry[1]) + '</strong><small>' + esc(entry[2]) + '</small></span></button>'; }).join('') + '</div></section><section class="panel"><div class="panel-header"><div><h3>Estado de conexión</h3><p>Comprobación rápida de la API.</p></div></div><div class="endpoint-list" id="connection-list"><div class="endpoint-row"><span class="endpoint-dot"></span><div class="endpoint-copy"><strong>Spring Boot</strong><small>Comprobando endpoints...</small></div></div></div><div class="callout blue" style="margin-top:20px;margin-bottom:0">Los módulos muestran los datos de tu base de datos local. Si una tabla está vacía, la lista aparecerá vacía.</div></section></div>';
        content.querySelectorAll('[data-view]').forEach(function(button) { button.addEventListener('click', function() { goView(button.dataset.view); }); });
    }
    async function renderOverview() {
        renderOverviewShell();
        const targets = [
            ['Usuarios', '/user'], ['Asignaturas', '/subjects'], ['Recursos', '/resources'], ['Preguntas', '/questions']
        ];
        const counts = await Promise.all(targets.map(function(item) { return safeCount(item[1]); }));
        const stats = document.getElementById('stats-grid');
        if (stats) stats.innerHTML = statCard('Usuarios', counts[0], '♙', 'Registros disponibles') + statCard('Asignaturas', counts[1], '▤', 'Oferta académica') + statCard('Recursos', counts[2], '▧', 'Material de apoyo') + statCard('Preguntas', counts[3], '?', 'Comunidad académica');
        const list = document.getElementById('connection-list');
        if (list) list.innerHTML = targets.map(function(item, index) {
            const ok = counts[index] != null;
            return '<div class="endpoint-row"><span class="endpoint-dot" style="background:' + (ok ? '#24a36c' : '#d99b22') + '"></span><div class="endpoint-copy"><strong>' + esc(item[0]) + '</strong><small>' + esc(item[1]) + '</small></div><span class="status-pill ' + (ok ? '' : 'offline') + '">' + (ok ? 'Disponible' : 'Revisar') + '</span></div>';
        }).join('');
    }
    function renderModule(key) {
        const module = getModule(key);
        currentModuleKey = key;
        searchText = '';
        content.innerHTML = '<div class="page-heading"><div><span class="eyebrow blue-eyebrow">GESTIÓN DE INFORMACIÓN</span><h1>' + esc(module.title) + '</h1><p>Consulta los registros de ' + esc(module.title.toLowerCase()) + ' y utiliza las operaciones disponibles del backend.</p></div><button id="refresh-button" class="btn btn-secondary">↻ Actualizar</button></div>' + (key === 'users' ? '<div class="callout blue">Por seguridad, esta vista solo consulta usuarios y no muestra contraseñas. El registro se realiza desde la pantalla Crear cuenta.</div>' : '') + (module.mode === 'json' ? '<div class="callout">Este módulo utiliza un editor JSON porque todavía falta confirmar el esquema exacto de sus campos en la entidad. Respeta los nombres y relaciones de tu código Java.</div>' : '') + '<div class="panel"><div class="toolbar"><input class="filter-input" id="table-search" type="search" placeholder="Buscar en los registros…" aria-label="Buscar registros">' + (module.mode === 'readonly' ? '' : '<button class="btn btn-primary" id="new-record-button">＋ Nuevo registro</button>') + '</div><div id="module-table"><div class="empty-state"><strong>Cargando datos...</strong>Consultando la API de Spring Boot.</div></div></div><div id="module-message" class="field-help" style="margin-top:12px"></div>';
        document.getElementById('refresh-button').addEventListener('click', function() { loadModuleRows(key); });
        const search = document.getElementById('table-search');
        search.addEventListener('input', function() { searchText = search.value.toLowerCase(); paintTable(); });
        const newButton = document.getElementById('new-record-button');
        if (newButton) newButton.addEventListener('click', function() { openForm(key, 'create', null); });
        loadModuleRows(key);
    }
    async function loadModuleRows(key) {
        const module = getModule(key);
        const table = document.getElementById('module-table');
        if (!table) return;
        table.innerHTML = '<div class="loading-line"></div><div class="empty-state"><strong>Consultando registros…</strong>Un momento, por favor.</div>';
        try {
            const result = await PAApi.get(module.endpoint);
            currentRows = Array.isArray(result.data) ? result.data : (result.data == null ? [] : [result.data]);
            if (key === 'users') currentRows = currentRows.map(function(row) { const safe = Object.assign({}, row); delete safe.password; return safe; });
            if (key === currentModuleKey) paintTable();
        } catch (error) {
            currentRows = [];
            table.innerHTML = '<div class="empty-state"><strong>No se pudo consultar ' + esc(module.title.toLowerCase()) + '.</strong><span>' + esc(error.message) + '</span><p>Comprueba que la aplicación esté iniciada y que la ruta del controller coincida con <code>' + esc(module.endpoint) + '</code>.</p><button class="btn btn-secondary" id="retry-load">Intentar de nuevo</button></div>';
            const retry = document.getElementById('retry-load');
            if (retry) retry.addEventListener('click', function() { loadModuleRows(key); });
        }
    }
    function paintTable() {
        const key = currentModuleKey;
        const module = getModule(key);
        const table = document.getElementById('module-table');
        if (!table || !module) return;
        const rows = currentRows.filter(function(row) {
            if (!searchText) return true;
            return JSON.stringify(row).toLowerCase().includes(searchText);
        });
        const columns = module.columns || Object.keys(rows[0] || {}).filter(function(keyName) { return keyName !== 'password'; }).slice(0, 5).map(function(name) { return [name, name]; });
        if (!rows.length) {
            table.innerHTML = '<div class="empty-state"><strong>No hay registros para mostrar</strong>' + (searchText ? 'Prueba con otro término de búsqueda.' : 'La consulta funcionó, pero la tabla puede estar vacía.') + '</div>';
            return;
        }
        const headers = columns.map(function(column) { return '<th>' + esc(column[1]) + '</th>'; }).join('');
        const body = rows.map(function(row) {
            const originIndex = currentRows.indexOf(row);
            const cells = columns.map(function(column) { return '<td>' + esc(shortText(row[column[0]], 85)) + '</td>'; }).join('');
            const actionButtons = module.mode === 'readonly' ? '' : '<button class="table-link" data-edit="' + originIndex + '">Editar</button>' + (module.canDelete === false ? '' : '<button class="btn btn-danger btn-small" data-delete="' + originIndex + '">Eliminar</button>');
            return '<tr>' + cells + '<td><div class="row-actions">' + actionButtons + (module.mode === 'readonly' ? '<button class="table-link" data-show="' + originIndex + '">Ver</button>' : '') + '</div></td></tr>';
        }).join('');
        table.innerHTML = '<div class="table-wrap"><table class="data-table"><thead><tr>' + headers + '<th>Acciones</th></tr></thead><tbody>' + body + '</tbody></table></div><div class="table-footer"><span>' + rows.length + ' registro(s)</span><span>Origen: ' + esc(module.endpoint) + '</span></div>';
        table.querySelectorAll('[data-edit]').forEach(function(button) { button.addEventListener('click', function() { openForm(key, 'edit', currentRows[Number(button.dataset.edit)]); }); });
        table.querySelectorAll('[data-delete]').forEach(function(button) { button.addEventListener('click', function() { deleteRow(key, currentRows[Number(button.dataset.delete)]); }); });
        table.querySelectorAll('[data-show]').forEach(function(button) { button.addEventListener('click', function() { showDetail(key, currentRows[Number(button.dataset.show)]); }); });
    }
    function showDetail(key, row) {
        const module = getModule(key);
        const backdrop = document.createElement('div');
        backdrop.className = 'modal-backdrop';
        backdrop.innerHTML = '<section class="modal-card" role="dialog" aria-modal="true"><div class="modal-head"><div><h2>Detalle del registro</h2><p>' + esc(module.title) + '</p></div><button class="modal-close" aria-label="Cerrar">×</button></div><div class="modal-form"><pre class="json-editor"></pre><div class="form-actions"><button class="btn btn-secondary" data-close> Cerrar </button></div></div></section>';
        backdrop.querySelector('pre').textContent = JSON.stringify(row, null, 2);
        document.body.appendChild(backdrop);
        const close = function() { backdrop.remove(); };
        backdrop.querySelector('.modal-close').addEventListener('click', close);
        backdrop.querySelector('[data-close]').addEventListener('click', close);
        backdrop.addEventListener('click', function(event) { if (event.target === backdrop) close(); });
    }
    function localDateValue(value) {
        if (!value) return '';
        const date = new Date(value);
        if (Number.isNaN(date.getTime())) return String(value).slice(0, 16);
        const local = new Date(date.getTime() - date.getTimezoneOffset() * 60000);
        return local.toISOString().slice(0, 16);
    }
    function openForm(key, mode, row) {
        const module = getModule(key);
        if (module.mode === 'json') return openJsonForm(key, mode, row);
        const editing = mode === 'edit';
        const fields = (module.fields || []).map(function(field) {
            const isId = field.key === module.idKey;
            const baseValue = editing && row ? row[field.key] : (field.defaultValue != null ? field.defaultValue : '');
            let value = baseValue;
            let type = field.type || 'text';
            if (type === 'relation') value = editing && row && row[field.key] ? row[field.key][field.relationKey] : '';
            if (type === 'datetime') value = localDateValue(value);
            let input;
            if (type === 'textarea') {
                input = '<textarea class="form-control textarea" name="' + esc(field.key) + '" ' + (field.required ? 'required' : '') + '>' + esc(value == null ? '' : value) + '</textarea>';
            } else {
                const inputType = type === 'datetime' ? 'datetime-local' : (type === 'relation' ? 'number' : type);
                input = '<input class="form-control" name="' + esc(field.key) + '" type="' + esc(inputType) + '" value="' + esc(value == null ? '' : value) + '" ' + (field.required ? 'required' : '') + ' ' + (type === 'number' || type === 'relation' ? 'step="any"' : '') + '>';
            }
            const required = field.required ? ' <span style="color:#b42318">*</span>' : '';
            const help = type === 'relation' ? '<div class="field-help">Escribe un ID existente. La relación se enviará como <code>' + esc(field.key) + ': {' + esc(field.relationKey) + ': ID}</code>.</div>' : '';
            return '<div class="field-group ' + (type === 'textarea' ? 'field-full' : '') + '"><label for="field-' + esc(field.key) + '">' + esc(field.label) + required + '</label>' + input.replace('name="' + esc(field.key) + '"', 'id="field-' + esc(field.key) + '" name="' + esc(field.key) + '"') + help + '</div>';
        }).join('');
        const backdrop = document.createElement('div');
        backdrop.className = 'modal-backdrop';
        backdrop.innerHTML = '<section class="modal-card" role="dialog" aria-modal="true" aria-labelledby="modal-title"><div class="modal-head"><div><h2 id="modal-title">' + (editing ? 'Editar registro' : 'Crear registro') + '</h2><p>' + esc(module.title) + ' · Los campos con * son obligatorios.</p></div><button class="modal-close" type="button" aria-label="Cerrar">×</button></div><form class="modal-form" id="record-form"><div class="form-fields">' + fields + '</div><div id="form-error" class="notice error" hidden></div><div class="form-actions"><button class="btn btn-secondary" type="button" data-cancel>Cancelar</button><button class="btn btn-primary" type="submit" id="save-record">' + (editing ? 'Guardar cambios' : 'Crear registro') + '</button></div></form></section>';
        document.body.appendChild(backdrop);
        const close = function() { backdrop.remove(); };
        backdrop.querySelector('.modal-close').addEventListener('click', close);
        backdrop.querySelector('[data-cancel]').addEventListener('click', close);
        backdrop.addEventListener('click', function(event) { if (event.target === backdrop) close(); });
        const form = backdrop.querySelector('#record-form');
        form.addEventListener('submit', async function(event) {
            event.preventDefault();
            if (!form.reportValidity()) return;
            const payload = {};
            (module.fields || []).forEach(function(field) {
                const input = form.elements[field.key];
                if (!input) return;
                let value = input.value.trim();
                if (value === '') return;
                if (field.type === 'relation') {
                    payload[field.key] = {};
                    payload[field.key][field.relationKey] = Number(value);
                } else if (field.type === 'number') {
                    payload[field.key] = Number(value);
                } else if (field.type === 'datetime') {
                    payload[field.key] = new Date(value).toISOString();
                } else {
                    payload[field.key] = value;
                }
            });
            if (editing && row) payload[module.idKey] = getId(row, module);
            const save = form.querySelector('#save-record');
            const errorBox = form.querySelector('#form-error');
            save.disabled = true;
            save.textContent = 'Guardando…';
            try {
                const url = module.endpoint;
                const result = editing ? await PAApi.put(url, payload) : await PAApi.post(url, payload);
                if (result.data === null || result.raw.trim() === 'null') {
                    throw new Error('El servicio no confirmó el guardado. Verifica los datos y si ya existe un registro con el mismo identificador.');
                }
                close();
                toast(editing ? 'Registro actualizado.' : 'Registro creado.', 'success');
                await loadModuleRows(key);
            } catch (error) {
                PAApi.showNotice(errorBox, error.message, 'error');
            } finally {
                save.disabled = false;
                save.textContent = editing ? 'Guardar cambios' : 'Crear registro';
            }
        });
    }
    function openJsonForm(key, mode, row) {
        const module = getModule(key);
        const editing = mode === 'edit';
        const initial = editing ? JSON.stringify(row, null, 2) : '{\n  \n}';
        const backdrop = document.createElement('div');
        backdrop.className = 'modal-backdrop';
        backdrop.innerHTML = '<section class="modal-card" role="dialog" aria-modal="true"><div class="modal-head"><div><h2>' + (editing ? 'Editar registro JSON' : 'Crear registro JSON') + '</h2><p>' + esc(module.title) + ' · Usa los nombres exactos de la entidad Java.</p></div><button class="modal-close" type="button" aria-label="Cerrar">×</button></div><form class="modal-form" id="json-record-form"><label for="record-json" class="field-help">Cuerpo JSON</label><textarea id="record-json" class="json-editor" spellcheck="false" required></textarea><div class="field-help">Para las relaciones se envían objetos con identificadores existentes. En PUT conserva el identificador del registro.</div><div id="json-error" class="notice error" hidden></div><div class="form-actions"><button class="btn btn-secondary" type="button" data-cancel>Cancelar</button><button class="btn btn-primary" type="submit" id="save-json-record">' + (editing ? 'Guardar cambios' : 'Crear registro') + '</button></div></form></section>';
        document.body.appendChild(backdrop);
        backdrop.querySelector('#record-json').value = initial;
        const close = function() { backdrop.remove(); };
        backdrop.querySelector('.modal-close').addEventListener('click', close);
        backdrop.querySelector('[data-cancel]').addEventListener('click', close);
        backdrop.addEventListener('click', function(event) { if (event.target === backdrop) close(); });
        backdrop.querySelector('form').addEventListener('submit', async function(event) {
            event.preventDefault();
            const editor = backdrop.querySelector('#record-json');
            const errorBox = backdrop.querySelector('#json-error');
            let payload;
            try { payload = JSON.parse(editor.value); }
            catch (_) { PAApi.showNotice(errorBox, 'El JSON no es válido. Revisa comas, comillas y llaves.', 'error'); return; }
            if (editing && row && payload[module.idKey] == null) payload[module.idKey] = getId(row, module);
            const save = backdrop.querySelector('#save-json-record');
            save.disabled = true;
            save.textContent = 'Guardando…';
            try {
                const result = editing ? await PAApi.put(module.endpoint, payload) : await PAApi.post(module.endpoint, payload);
                if (result.data === null || result.raw.trim() === 'null') throw new Error('El servicio no confirmó el guardado. Revisa los datos enviados.');
                close(); toast(editing ? 'Registro actualizado.' : 'Registro creado.', 'success'); await loadModuleRows(key);
            } catch (error) { PAApi.showNotice(errorBox, error.message, 'error'); }
            finally { save.disabled = false; save.textContent = editing ? 'Guardar cambios' : 'Crear registro'; }
        });
    }
    async function deleteRow(key, row) {
        const module = getModule(key);
        const id = getId(row, module);
        if (!window.confirm('¿Seguro que deseas eliminar el registro con ID ' + id + '? Esta operación depende de las reglas del backend.')) return;
        const payload = {}; payload[module.idKey] = id;
        try {
            await PAApi.remove(module.endpoint, payload);
            toast('Solicitud de eliminación enviada correctamente.', 'success');
            await loadModuleRows(key);
        } catch (error) {
            toast('No se pudo eliminar: ' + error.message, 'error');
        }
    }

    nav.querySelectorAll('[data-view]').forEach(function(button) { button.addEventListener('click', function() { goView(button.dataset.view); }); });
    document.getElementById('logout-button').addEventListener('click', function() {
        localStorage.removeItem('pa_authenticated');
        localStorage.removeItem('pa_user_email');
        localStorage.removeItem('pa_user_name');
        window.location.href = 'index.html';
    });
    document.getElementById('mobile-menu-button').addEventListener('click', function() { document.getElementById('sidebar').classList.toggle('open'); });
    document.addEventListener('keydown', function(event) { if (event.key === 'Escape') document.getElementById('sidebar').classList.remove('open'); });
    goView('overview');
})();
