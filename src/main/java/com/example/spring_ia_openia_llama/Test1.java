/*
 * package com.example.spring_ia_openia_llama;
 * 
 * import java.util.List;
 * 
 * import org.springframework.ai.chat.messages.AssistantMessage;
 * import org.springframework.ai.chat.messages.SystemMessage;
 * import org.springframework.ai.chat.messages.UserMessage;
 * import org.springframework.ai.chat.model.ChatResponse;
 * import org.springframework.ai.chat.prompt.Prompt;
 * import org.springframework.ai.openai.OpenAiChatModel;
 * import org.springframework.ai.openai.OpenAiChatOptions;
 * import org.springframework.ai.openai.api.OpenAiApi;
 * 
 * public class Test1 {
 * public static void main(String[] args) {
 * OpenAiApi openAiApi = new
 * OpenAiApi("sk-proj-Ix2DBLW1L4UjZKT1KLUnT3BlbkFJqkOT 605ZEN8XUqUXN3NI");
 * OpenAiChatModel aiChatModel = new OpenAiChatModel(openAiApi,
 * OpenAiChatOptions.builder()
 * .withModel("gpt-4o")
 * .withTemperature(0F)
 * .withMaxTokens(300)
 * .build());
 * String systemMessageText = """
 * Vous êtes un asistant spécialié dans le domaine de l'analyse des sentiment.
 * 
 * Votre tâches est d'extraire à partir un commentaire le sentiment des
 * différents aspects des ordinateurs achetés par des clients. Les aspects qui
 * nous sont intéressent sont :
 * l'écran, la souris et le clavier. le setiment peut être positive, negative ou
 * neutre Le résultat attendu sera au format JSON avec les champs suivants :
 * clavier: le sentiment relatif au clavier
 * 
 * souris : le sentiment relatif à la souris
 * 
 * ecran: le sentiment relatif à l'écran
 * """;
 * String userInputText = """
 * je suis satisfait par la qualité de l'ecran, mais le clavier est mauvais
 * alors que la souris est plutot moyenne.
 * par ailleur je pense que cet ordinateur consomme bcq d'énergie
 * """;
 * 
 * String userInputText1 = """
 * je suis satisfait par la qualité de l'ecran, mais le clavier est mauvais
 * alors que la souris est plutot moyenne.
 * par ailleur je pense que cet ordinateur consomme bcq d'énergie
 * """;
 * String response1 = """
 * {
 * "clavier" : "negative",
 * "souris" : "neutre",
 * "ecran" : "positive",
 * }
 * """;
 * AssistantMessage assistantMessage1 = new AssistantMessage(response1);
 * SystemMessage systemMessage = new SystemMessage(systemMessageText);
 * UserMessage userMessage1 = new UserMessage(userInputText1);
 * UserMessage userMessage = new UserMessage(userInputText1);
 * Prompt zeroShopPrompt = new Prompt(List.of(systemMessage, userMessage,
 * assistantMessage1, userMessage1));
 * ChatResponse chatResponse = aiChatModel.call(zeroShopPrompt);
 * System.out.println(chatResponse.getResult().getOutput().getContent());
 * }
 * }
 */