package com.example.spring_ia_openia_llama.web;

import java.io.IOException;
import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Media;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_ia_openia_llama.records.Sentiment;

import reactor.core.publisher.Flux;

@RestController
public class ChatRestController {

    private ChatClient chatClient;

    @Value("classpath:/prompts/system-message.st")
    private Resource systemMessageResource;

    public ChatRestController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @GetMapping(value = "/chat", produces = MediaType.TEXT_PLAIN_VALUE)
    public String chat(String message) {
        String content = chatClient.prompt().user(message).call().content();
        return content;
    }

    @GetMapping(value = "/chat2", produces = MediaType.TEXT_PLAIN_VALUE)
    public Flux<String> chat2(String message) {
        Flux<String> content = chatClient.prompt().user(message).stream().content();
        return content;
    }

    @PostMapping(value = "/sentiment")
    public Sentiment sentiment(String review) {
        Sentiment content = chatClient.prompt()
                .system(systemMessageResource)
                .user(review).call().entity(Sentiment.class);
        return content;
    }

    @GetMapping("/describe")
    public String depenses() throws IOException {
        String userMessageText = """
                Ton rôle est la reconnaissance optique de texte qui se trouve dans l'image.
                """;
        byte[] data = new ClassPathResource("depences.jpg").getContentAsByteArray();
        UserMessage userMessage = new UserMessage(userMessageText, List.of(
                new Media(MimeTypeUtils.IMAGE_JPEG, data)));
        Prompt prompt = new Prompt(userMessage);
        return chatClient.prompt(prompt).call().content();
    }

}
