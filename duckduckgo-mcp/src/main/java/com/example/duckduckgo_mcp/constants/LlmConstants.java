package com.example.duckduckgo_mcp.constants;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class LlmConstants {
    public static final Pattern VAR_PATTERN = Pattern.compile("<([A-Za-z_][A-Za-z0-9_]*)>");
    // 프롬프트 템플릿 위치 (ai.prompts)
    public static final Map<String, String> PROMPTS = Map.ofEntries(
            Map.entry("prompt_sys", "classpath:prompts/prompt_sys.st"),
            Map.entry("prompt_user", "classpath:prompts/prompt_user.st"),
            Map.entry("risk_company", "classpath:prompts/risk_company.st")
    );

    // 기본 변수값 (ai.defaults)
    public static final Map<String, Object> DEFAULTS = Map.of(
            "maxItems", 5,
            "webRagItems", List.of(),
            "dartRagItems", List.of(),
            "financialData", "",
            "otherRiskInputs", List.of()
    );
}
