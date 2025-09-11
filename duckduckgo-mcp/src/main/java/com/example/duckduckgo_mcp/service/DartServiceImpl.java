package com.example.duckduckgo_mcp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DartServiceImpl implements DartService {
    @Qualifier("chatWithMcp")
    private final ChatClient chatClient;
    private final PromptCatalogService catalog;

    @Override
    public Mono<String> run(String corpName) {

        Map<String, Object> vars = Map.of("corpName", corpName);

        Prompt sys = catalog.createSystemPrompt("prompt_sys", vars);
        Prompt user=  catalog.createPrompt("prompt_user", vars);

        List<Message> reviseMsgs = new ArrayList<>(sys.getInstructions());
        reviseMsgs.addAll(user.getInstructions());

        return Mono.fromCallable(() -> chatClient.prompt(new Prompt(reviseMsgs)).call().content())
                .subscribeOn(Schedulers.boundedElastic());
    }
}
