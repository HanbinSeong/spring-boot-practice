package com.example.duckduckgo_mcp.controller;

import com.example.duckduckgo_mcp.service.DartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ai.chat.client.ChatClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequiredArgsConstructor
class DemoController {

    @Qualifier("chatPlain")
    private final ChatClient chatPlain;

    @Qualifier("chatWithMcp")
    private final ChatClient chatWithMcp;

    private final DartService dartService;

    @GetMapping("/plain")
    public Mono<String> plain(@RequestParam(defaultValue = "간단 자기소개") String q) {
        // 동기/블로킹 호출을 boundedElastic로 옮겨서 실행
        return Mono.fromCallable(() -> chatPlain.prompt(q).call().content())
                .subscribeOn(Schedulers.boundedElastic());
    }

    @GetMapping("/mcp")
    public Mono<String> mcp(@RequestParam(defaultValue = "OpenSearch 최신 변화 요약(출처 포함)") String q) {
        // 필요 시 LLM이 MCP 툴을 호출하지만, 여기서도 최종 .call() 은 블로킹이라 동일 처리
        return Mono.fromCallable(() -> chatWithMcp.prompt(q).call().content())
                .subscribeOn(Schedulers.boundedElastic());
    }

    @PostMapping("/dart")
    public Mono<String> dart(@RequestBody String corpName) {
        return dartService.run(corpName);
    }
}
