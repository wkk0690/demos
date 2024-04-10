package com.example.demo2.utils;

import dev.langchain4j.model.openai.OpenAiChatModel;

/**
 * LangChain4j
 * https://github.com/langchain4j/langchain4j
 * @author wkk
 * @date 2024-03-11
 */
public class LangChain4j {

    public static void main(String[] args) {
        OpenAiChatModel model = OpenAiChatModel.withApiKey("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");
        String answer = model.generate("Hello world!");
        System.out.println(answer); // Hello! How can I assist you today?
    }
}
