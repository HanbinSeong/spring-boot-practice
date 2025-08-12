package com.example.lang_graph_practice.service;

import com.example.lang_graph_practice.dto.LangGraphDto;

public interface GraphService {
    LangGraphDto.GraphResult run(LangGraphDto.PromptRequest promptRequest);
}
