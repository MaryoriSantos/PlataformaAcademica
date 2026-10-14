/* Funciones compartidas para comunicarse con la API REST de Spring Boot. */
(function() {
    'use strict';

    async function request(url, options) {
        const settings = Object.assign({ method: 'GET', headers: {} }, options || {});
        if (settings.body && typeof settings.body !== 'string') {
            settings.body = JSON.stringify(settings.body);
        }
        if (settings.body && !settings.headers['Content-Type']) {
            settings.headers['Content-Type'] = 'application/json';
        }
        settings.headers = Object.assign({ 'Accept': 'application/json, text/plain, */*' }, settings.headers);

        let response;
        try {
            response = await fetch(url, settings);
        } catch (error) {
            throw new Error('No se pudo conectar con Spring Boot. Comprueba que la aplicación esté iniciada en localhost:8080.');
        }
        const text = await response.text();
        let data = null;
        if (text) {
            try { data = JSON.parse(text); } catch (_) { data = text; }
        }
        if (!response.ok) {
            let detail = typeof data === 'string' ? data : (data && (data.message || data.error || data.detail));
            if (!detail) detail = 'La API respondió con HTTP ' + response.status + ' ' + response.statusText + '.';
            throw new Error(detail);
        }
        return { status: response.status, data: data, raw: text, headers: response.headers };
    }

    function get(url) { return request(url, { method: 'GET' }); }
    function post(url, body) { return request(url, { method: 'POST', body: body }); }
    function put(url, body) { return request(url, { method: 'PUT', body: body }); }
    function remove(url, body) { return request(url, { method: 'DELETE', body: body }); }
    function escapeHtml(value) {
        return String(value == null ? '' : value).replace(/[&<>"']/g, function(char) {
            return ({ '&': '&amp;', '<': '&lt;', '>': '&gt;', '"': '&quot;', "'": '&#39;' })[char];
        });
    }
    function showNotice(element, message, type) {
        if (!element) return;
        element.textContent = message;
        element.className = 'notice ' + (type || 'info');
        element.hidden = false;
    }
    function initials(name) {
        const words = String(name || '').trim().split(/\s+/).filter(Boolean);
        if (!words.length) return 'PA';
        return words.slice(0, 2).map(function(word) { return word.charAt(0).toUpperCase(); }).join('');
    }

    /* Las rutas conocidas vienen de los controllers revisados durante el proyecto.
       Las rutas NRC/Inscripción/Calificación se deben comparar con sus controllers locales. */
    const modules = {
        users: { title: 'Usuarios', singular: 'usuario', endpoint: '/user', idKey: 'userId', getOne: function(id) { return '/user/id?id=' + encodeURIComponent(id); }, mode: 'readonly', columns: [['userId', 'ID'], ['firstName', 'Nombre'], ['lastName', 'Apellido'], ['email', 'Correo'], ['status', 'Estado']] },
        roles: { title: 'Roles', singular: 'rol', endpoint: '/role', idKey: 'id', getOne: function(id) { return '/role/id?id=' + encodeURIComponent(id); }, fields: [{ key: 'name', label: 'Nombre del rol', type: 'text', required: true }], columns: [['id', 'ID'], ['name', 'Nombre']], canDelete: false },
        subjects: { title: 'Asignaturas', singular: 'asignatura', endpoint: '/subjects', idKey: 'subjectId', fields: [{ key: 'name', label: 'Nombre', type: 'text', required: true }, { key: 'credits', label: 'Créditos', type: 'number', required: true }, { key: 'description', label: 'Descripción', type: 'textarea' }, { key: 'status', label: 'Estado (1 activo, 0 inactivo)', type: 'number', defaultValue: 1 }], columns: [['subjectId', 'ID'], ['name', 'Nombre'], ['credits', 'Créditos'], ['status', 'Estado']] },
        nrcs: { title: 'NRC', singular: 'NRC', endpoint: '/nrcs', idKey: 'nrcId', mode: 'json', columns: [['nrcId', 'ID'], ['name', 'Nombre'], ['nrcCode', 'Código']] },
        nrcEnrollments: { title: 'Inscripciones', singular: 'inscripción', endpoint: '/nrc-enrollments', idKey: 'nrcEnrollmentId', mode: 'json', columns: [['nrcEnrollmentId', 'ID'], ['status', 'Estado']] },
        deliverables: { title: 'Entregables', singular: 'entregable', endpoint: '/deliverables', idKey: 'deliverableId', fields: [{ key: 'name', label: 'Nombre', type: 'text', required: true }, { key: 'description', label: 'Descripción', type: 'textarea' }, { key: 'createdAt', label: 'Fecha de creación', type: 'datetime' }, { key: 'dueDate', label: 'Fecha límite', type: 'datetime' }, { key: 'percentage', label: 'Porcentaje', type: 'number' }, { key: 'status', label: 'Estado (1 activo, 0 inactivo)', type: 'number', defaultValue: 1 }, { key: 'type', label: 'Tipo', type: 'text' }, { key: 'nrc', label: 'ID del NRC relacionado', type: 'relation', relationKey: 'nrcId', required: true }, { key: 'user', label: 'ID del usuario creador', type: 'relation', relationKey: 'userId', required: true }], columns: [['deliverableId', 'ID'], ['name', 'Nombre'], ['type', 'Tipo'], ['percentage', 'Porcentaje'], ['dueDate', 'Fecha límite'], ['status', 'Estado']] },
        grades: { title: 'Calificaciones', singular: 'calificación', endpoint: '/grades', idKey: 'gradeId', mode: 'json', columns: [['gradeId', 'ID'], ['grade', 'Nota'], ['feedback', 'Observación']] },
        resources: { title: 'Recursos', singular: 'recurso', endpoint: '/resources', idKey: 'resourceId', fields: [{ key: 'title', label: 'Título', type: 'text', required: true }, { key: 'description', label: 'Descripción', type: 'textarea' }, { key: 'resourceType', label: 'Tipo de recurso', type: 'text' }, { key: 'resourceUrl', label: 'URL del recurso', type: 'url' }, { key: 'createdAt', label: 'Fecha de creación', type: 'datetime' }, { key: 'status', label: 'Estado (1 activo, 0 inactivo)', type: 'number', defaultValue: 1 }, { key: 'nrc', label: 'ID del NRC relacionado', type: 'relation', relationKey: 'nrcId', required: true }, { key: 'user', label: 'ID del usuario propietario', type: 'relation', relationKey: 'userId', required: true }], columns: [['resourceId', 'ID'], ['title', 'Título'], ['resourceType', 'Tipo'], ['resourceUrl', 'Enlace'], ['status', 'Estado']] },
        topics: { title: 'Temas', singular: 'tema', endpoint: '/topics', idKey: 'topicId', fields: [{ key: 'name', label: 'Nombre', type: 'text', required: true }, { key: 'description', label: 'Descripción', type: 'textarea' }], columns: [['topicId', 'ID'], ['name', 'Nombre'], ['description', 'Descripción']] },
        resourceRatings: { title: 'Valoraciones', singular: 'valoración', endpoint: '/resource-ratings', idKey: 'ratingId', fields: [{ key: 'rating', label: 'Calificación (según escala del proyecto)', type: 'number', required: true }, { key: 'comment', label: 'Comentario', type: 'textarea' }, { key: 'createdAt', label: 'Fecha de creación', type: 'datetime' }, { key: 'resource', label: 'ID del recurso', type: 'relation', relationKey: 'resourceId', required: true }, { key: 'user', label: 'ID del usuario', type: 'relation', relationKey: 'userId', required: true }], columns: [['ratingId', 'ID'], ['rating', 'Valoración'], ['comment', 'Comentario'], ['createdAt', 'Fecha']] },
        topicDifficulties: { title: 'Dificultades', singular: 'registro de dificultad', endpoint: '/topic-difficulties', idKey: 'topicDifficultyId', fields: [{ key: 'difficultyLevel', label: 'Nivel de dificultad', type: 'number', required: true }, { key: 'createdAt', label: 'Fecha de registro', type: 'datetime' }, { key: 'nrc', label: 'ID del NRC', type: 'relation', relationKey: 'nrcId', required: true }, { key: 'topic', label: 'ID del tema', type: 'relation', relationKey: 'topicId', required: true }, { key: 'user', label: 'ID del usuario', type: 'relation', relationKey: 'userId', required: true }], columns: [['topicDifficultyId', 'ID'], ['difficultyLevel', 'Nivel'], ['createdAt', 'Fecha']] },
        questions: { title: 'Preguntas', singular: 'pregunta', endpoint: '/questions', idKey: 'questionId', fields: [{ key: 'title', label: 'Título', type: 'text', required: true }, { key: 'description', label: 'Descripción', type: 'textarea' }, { key: 'createdAt', label: 'Fecha de creación', type: 'datetime' }, { key: 'status', label: 'Estado (1 activo, 0 inactivo)', type: 'number', defaultValue: 1 }, { key: 'nrc', label: 'ID del NRC', type: 'relation', relationKey: 'nrcId', required: true }, { key: 'topic', label: 'ID del tema', type: 'relation', relationKey: 'topicId', required: true }, { key: 'user', label: 'ID del usuario', type: 'relation', relationKey: 'userId', required: true }], columns: [['questionId', 'ID'], ['title', 'Título'], ['description', 'Descripción'], ['status', 'Estado']] },
        answers: { title: 'Respuestas', singular: 'respuesta', endpoint: '/answers', idKey: 'answerId', fields: [{ key: 'content', label: 'Contenido', type: 'textarea', required: true }, { key: 'createdAt', label: 'Fecha de creación', type: 'datetime' }, { key: 'status', label: 'Estado (1 activo, 0 inactivo)', type: 'number', defaultValue: 1 }, { key: 'question', label: 'ID de la pregunta', type: 'relation', relationKey: 'questionId', required: true }, { key: 'user', label: 'ID del usuario', type: 'relation', relationKey: 'userId', required: true }], columns: [['answerId', 'ID'], ['content', 'Contenido'], ['status', 'Estado']] },
        notifications: { title: 'Notificaciones', singular: 'notificación', endpoint: '/notifications', idKey: 'notificationId', fields: [{ key: 'title', label: 'Título', type: 'text', required: true }, { key: 'message', label: 'Mensaje', type: 'textarea', required: true }, { key: 'isRead', label: 'Leída (1 sí, 0 no)', type: 'number', defaultValue: 0 }, { key: 'sentAt', label: 'Fecha de envío', type: 'datetime' }, { key: 'deliverable', label: 'ID del entregable relacionado', type: 'relation', relationKey: 'deliverableId' }, { key: 'user', label: 'ID del usuario destinatario', type: 'relation', relationKey: 'userId', required: true }], columns: [['notificationId', 'ID'], ['title', 'Título'], ['message', 'Mensaje'], ['isRead', 'Leída']] },
        aiRecomendations: { title: 'Recomendaciones IA', singular: 'recomendación', endpoint: '/ai-recomendations', idKey: 'recommendationId', fields: [{ key: 'recommendation', label: 'Recomendación', type: 'textarea', required: true }, { key: 'recommendationType', label: 'Tipo de recomendación', type: 'text' }, { key: 'createdAt', label: 'Fecha de creación', type: 'datetime' }, { key: 'nrc', label: 'ID del NRC', type: 'relation', relationKey: 'nrcId', required: true }, { key: 'topic', label: 'ID del tema', type: 'relation', relationKey: 'topicId', required: true }, { key: 'user', label: 'ID del usuario', type: 'relation', relationKey: 'userId', required: true }], columns: [['recommendationId', 'ID'], ['recommendationType', 'Tipo'], ['recommendation', 'Recomendación'], ['createdAt', 'Fecha']] }
    };

    window.PAApi = { request: request, get: get, post: post, put: put, remove: remove, escapeHtml: escapeHtml, showNotice: showNotice, initials: initials, modules: modules };
})();
