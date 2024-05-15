package com.aggrandizer.HandBookGuider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.core.io.Resource;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ShellComponent
public class SpringAssistantCommand {

    private static final Logger log = LoggerFactory.getLogger(ReferenceDocsLoader.class);
    private final ChatClient chatClient;
    private final VectorStore vectorStore;
    private final Resource hbPromptTemplate;

    public SpringAssistantCommand(ChatClient chatClient, VectorStore vectorStore, @Value("classpath:/prompts/hanbook.st") Resource hbPromptTemplate) {
        this.chatClient = chatClient;
        this.vectorStore = vectorStore;
        this.hbPromptTemplate = hbPromptTemplate;
    }

    @ShellMethod(value = "Ask a question", key = {"q"})
    public String question(@DefaultValue(value = "what is the name of this degree?") String message) {
        PromptTemplate promptTemplate = new PromptTemplate(hbPromptTemplate);
        Map<String, Object> promptParameters = new HashMap<>();
        promptParameters.put("input", message);
        promptParameters.put("documents", String.join("\n", findSimilarDocuments(message)));

        return chatClient.call(promptTemplate.create(promptParameters))
                .getResult()
                .getOutput()
                .getContent();
    }

    private List<String> findSimilarDocuments(String message) {
        List<Document> similarDocuments = vectorStore.similaritySearch(SearchRequest.query(message).withTopK(3));
        return similarDocuments.stream().map(Document::getContent).toList();
    }
}