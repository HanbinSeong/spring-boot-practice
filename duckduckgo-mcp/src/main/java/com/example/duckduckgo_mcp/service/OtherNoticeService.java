package com.example.duckduckgo_mcp.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.PropertyPlaceholderHelper;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class OtherNoticeService {

    private final String template;
    private final PropertyPlaceholderHelper helper =
            new PropertyPlaceholderHelper("{", "}");

    public OtherNoticeService(ResourceLoader loader) throws IOException {
        Resource r = loader.getResource("classpath:templates/other_notice_ko.txt");
        try (InputStream in = r.getInputStream()) {
            this.template = new String(in.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    public String otherNotice(String corpName) {
        Map<String, String> v = derive(corpName);
        return helper.replacePlaceholders(template, v::get);
    }

    // 전제: 회사명 내부에는 공백이 없고, "주식회사" 주변에만 공백 가능
    private Map<String, String> derive(String corpName) {
        String legal = corpName.strip(); // JDK 11+. JDK 8이면 trim()
        String simple = legal.replace("주식회사", "").strip();                 // "주식회사" 제거 후 좌우 공백 제거
        String abbr   = legal.replace("주식회사", "㈜").replace(" ", "").strip(); // "주식회사"→"㈜", 남은 스페이스 제거
        return Map.of(
                "LEGAL_NAME", legal,
                "SIMPLE_NAME", simple,
                "ABBR_NAME",   abbr
        );
    }
}
