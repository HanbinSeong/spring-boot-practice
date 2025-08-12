package com.example.lang_graph_practice.config;

import com.example.lang_graph_practice.dto.LangGraphDto;
import com.example.lang_graph_practice.service.LlmService;
import lombok.RequiredArgsConstructor;
import org.bsc.langgraph4j.CompiledGraph;
import org.bsc.langgraph4j.StateGraph;
import org.bsc.langgraph4j.GraphStateException;
import org.bsc.langgraph4j.action.AsyncNodeAction;
import org.bsc.langgraph4j.state.AgentState;
import org.bsc.langgraph4j.state.Channel;
import org.bsc.langgraph4j.state.Channels;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Configuration
public class GraphConfig {

    /**
     * 가장 작은 LangGraph 예제
     * State keys: input(String), output(String)
     * Flow: START -> llm -> END
     */
    @Bean
    public CompiledGraph<AgentState> demoGraph(LlmService llmService) throws GraphStateException {
        // 1) 상태 스키마(채널) 정의
        Map<String, Channel<?>> channels = new HashMap<>();
        channels.put("input", Channels.base(() -> new LangGraphDto.PromptRequest("")));
        channels.put("output", Channels.base(() -> new LangGraphDto.GraphResult("")));

        // 2) 그래프 생성 (상태 팩토리: new AgentState(map))
        StateGraph<AgentState> graph = new StateGraph<>(channels, AgentState::new);

        // 3) 노드 정의 (비동기 노드: LLM 호출 → output 업데이트)
        AsyncNodeAction<AgentState> llmNode = state -> CompletableFuture.supplyAsync(() -> {
            LangGraphDto.PromptRequest promptRequest = state.value("input")
                    .map(LangGraphDto.PromptRequest.class::cast)
                    .orElse(new LangGraphDto.PromptRequest(""));
            String answer = llmService.complete(promptRequest.getInput());
            return Map.of("output", new LangGraphDto.GraphResult(answer));
        });

        graph.addNode("llm", llmNode);
        // 4) 엣지 연결 (선형 흐름)
        graph.addEdge(StateGraph.START, "llm");
        graph.addEdge("llm", StateGraph.END);

        // 5) 컴파일해서 런너 리턴
        return graph.compile();
    }
}
