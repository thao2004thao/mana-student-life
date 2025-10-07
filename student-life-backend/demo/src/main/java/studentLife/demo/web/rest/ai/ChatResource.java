package studentLife.demo.web.rest.ai;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import studentLife.demo.service.business.chatai.ChatAIService;
import studentLife.demo.service.dto.chatai.ChatRequestDTO;
import studentLife.demo.service.dto.chatai.ChatResponseDTO;

@RestController
@RequestMapping("/api/chat")
public class ChatResource {
    private final ChatAIService chatAIService;

    public ChatResource(ChatAIService chatAIService) {
        this.chatAIService = chatAIService;
    }


    @PostMapping
    public ChatResponseDTO chat(@RequestBody ChatRequestDTO request) {
        return chatAIService.sendMessageToAI(request.getMessage());
    }
}
