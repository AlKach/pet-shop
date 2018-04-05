import { SwaggerUIBundle, SwaggerUIStandalonePreset } from 'swagger-ui-dist'
import 'swagger-ui-dist/swagger-ui.css';

document.addEventListener('DOMContentLoaded',
	function() {
        window.ui = SwaggerUIBundle({
            url: "api-docs",
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
	}
);