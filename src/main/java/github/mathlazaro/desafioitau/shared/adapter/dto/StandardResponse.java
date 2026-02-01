package github.mathlazaro.desafioitau.shared.adapter.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record StandardResponse(
        String message,
        Object data,
        Map<String, List<String>> errors
) {

}
