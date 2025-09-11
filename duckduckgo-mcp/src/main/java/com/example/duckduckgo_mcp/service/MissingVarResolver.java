package com.example.duckduckgo_mcp.service;

@FunctionalInterface
public interface MissingVarResolver {
    Object resolve(String name);
}