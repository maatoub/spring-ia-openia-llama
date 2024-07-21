package com.example.spring_ia_openia_llama;

import java.util.List;

import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;

public class TestLlama {
        public static void main(String[] args) {
                OllamaApi ollamaApi = new OllamaApi();
                OllamaChatModel ollamaChatModel = new OllamaChatModel(
                                ollamaApi,
                                OllamaOptions.create()
                                                .withModel("tinyllama")
                                                .withTemperature(0F));

                String systemMessageText = """
                                Vous êtes un asistant spécialisé dans le domaine de l'analyse des sentiment.
                                Votre tâches est d'extraire à partir un commentaire le sentiment des différents aspects des ordinateurs achetés par des clients.
                                Les aspects qui nous sont intéressent sont :l'ecran, la processeur et le clavier.
                                les sentiments peut être positive, negative ou neutre.
                                Le résultat attendu sera au format JSON avec les champs suivants :
                                    - clavier: le sentiment relatif au clavier.
                                    - processeur : le sentiment relatif à la processeur.
                                    - ecran: le sentiment relatif à l'ecran.    """;
                String userInputText = """
                                je suis satisfait par la qualité de l'ecran, mais le clavier est mauvais alors que la souris est plutot moyenne.
                                par ailleur je pense que cet ordinateur consomme bcq d'énergie
                                """;

                String userInputText1 = """
                                   je suis satisfait par la qualité de l'ecran, mais le clavier est mauvais alors que la souris est plutot moyenne.
                                par ailleur je pense que cet ordinateur consomme bcq d'énergie
                                        """;
                String response1 = """
                                {
                                    "clavier" : "negative",
                                    "souris" : "neutre",
                                    "ecran" : "positive",
                                 }
                                """;
                AssistantMessage assistantMessage1 = new AssistantMessage(response1);
                SystemMessage systemMessage = new SystemMessage(systemMessageText);
                UserMessage userMessage = new UserMessage(userInputText);
                UserMessage userMessage1 = new UserMessage(userInputText1);
                Prompt zeroShopPrompt = new Prompt(List.of(systemMessage, userMessage));
                ChatResponse chatResponse = ollamaChatModel.call(zeroShopPrompt);
                System.out.println(chatResponse.getResult().getOutput().getContent());

        }
}
