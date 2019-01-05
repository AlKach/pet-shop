import { SwaggerUIBundle, SwaggerUIStandalonePreset } from 'swagger-ui-dist'
import 'swagger-ui-dist/swagger-ui.css';

document.addEventListener('DOMContentLoaded',
	function() {
        fetch('api-docs')
            .then(spec => spec.text())
            .then(spec => JSON.parse(spec))
            .then(spec => {
                spec.host = window.location.host;
                window.ui = SwaggerUIBundle({
                    spec: spec,
                    dom_id: '#swagger-ui',
                    deepLinking: true,
                    defaultModelsExpandDepth: -1,
                    displayRequestDuration: true,
                    docExpansion: 'none',
                    filter: true,
                    tagsSorter: 'alpha',
                    validatorUrl: null,
                    presets: [
                        SwaggerUIBundle.presets.apis,
                        SwaggerUIStandalonePreset
                    ],
                    plugins: [
                        SwaggerUIBundle.plugins.DownloadUrl
                    ],
                    layout: "StandaloneLayout"
                });
            });
	}
);