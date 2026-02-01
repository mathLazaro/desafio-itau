package github.mathlazaro.desafioitau.shared.infra.handler;

import github.mathlazaro.desafioitau.shared.adapter.dto.StandardResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import static java.util.Objects.isNull;

@RestControllerAdvice
public class StandardResponseHandler implements ResponseBodyAdvice<Object> {

    private static final String[] IGNORED_PATHS = {
            "/v3/api-docs",
            "/swagger-ui",
            "/actuator"
    };

    @Override
    public boolean supports(
            MethodParameter returnType,
            Class<? extends HttpMessageConverter<?>> converterType
    ) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(
            Object body,
            MethodParameter returnType,
            MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType,
            ServerHttpRequest request,
            ServerHttpResponse response
    ) {
        if (body instanceof StandardResponse
                || isNull(body)
                || isIgnoredRoute(request, IGNORED_PATHS)
        ) {
            return body;
        }

        return new StandardResponse(
                "Success",
                body,
                null
        );
    }

    private boolean isIgnoredRoute(ServerHttpRequest request, String... paths) {
        String path = request.getURI().getPath();

        for (var p : paths) {
            if (path.contains(p)) {
                return true;
            }
        }

        return false;
    }
}
