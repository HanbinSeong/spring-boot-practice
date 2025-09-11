package com.example.duckduckgo_mcp.service;

import reactor.core.publisher.Mono;

public interface DartService {
    Mono<String> run(String corpName);
}
