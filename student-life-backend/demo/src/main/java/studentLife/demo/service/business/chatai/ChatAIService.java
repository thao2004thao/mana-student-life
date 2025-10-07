package studentLife.demo.service.business.chatai;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import studentLife.demo.service.dto.chatai.ChatResponseDTO;

import java.util.HashMap;
import java.util.Map;

@Service
public class ChatAIService {

    private final WebClient webClient;

    public ChatAIService(@Value("${ollama.api.url}") String apiUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(apiUrl)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    public ChatResponseDTO sendMessageToAI(String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("model", "gemma2:2b");
        body.put("prompt", message);
        body.put("stream", false);      // Tắt stream để trả về kết quả 1 lần

        Map<String, Object> response = webClient.post()
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        String reply = response != null ? (String) response.get("response") : "Không có phản hồi từ AI.";

        ChatResponseDTO dto = new ChatResponseDTO();
        dto.setReply(reply);
        return dto;
    }
}
