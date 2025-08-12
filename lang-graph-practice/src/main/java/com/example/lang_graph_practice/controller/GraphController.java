package com.example.lang_graph_practice.controller;

import com.example.lang_graph_practice.dto.LangGraphDto;
import com.example.lang_graph_practice.service.GraphService;
import jakarta.validation.Valid;
import org.bsc.langgraph4j.CompiledGraph;
import org.bsc.langgraph4j.state.AgentState;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/graph")
public class GraphController {

    private final GraphService graphService;

    public GraphController(GraphService graphService) {
        this.graphService = graphService;
    }

    @PostMapping(path = "/run", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public LangGraphDto.GraphResult run(@Valid @RequestBody LangGraphDto.PromptRequest promptRequest) {
        return graphService.run(promptRequest);
    }
}
