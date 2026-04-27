package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Builder
@Value
public class PatchDataRequest{
    @JsonProperty("addition")
    private dto.Addition addition;
    @JsonProperty("verified")
    private List<Integer> importantNumbers;
    @JsonProperty("title")
    private String title;
    @JsonProperty("verified")
    private boolean verified;
}