package com.example.duckduckgo_mcp.tools;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;
import org.springframework.ai.tool.annotation.Tool;

@Component
public class TimeTools {

    @Tool(name = "time.now", description = "현재 시각을 ISO-8601 문자열로 반환")
    public String now() {
        return ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }
}