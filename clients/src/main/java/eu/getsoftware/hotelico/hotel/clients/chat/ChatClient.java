package eu.getsoftware.hotelico.hotel.clients.chat;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name ="chat"
//        , notification = "${clients.chat.url}" # in case of k8s services naming
)
public interface ChatClient
{

    @GetMapping(path = "api/v1/messages/sender/{customerId}/receiver/{receiverId}")
    List<ChatMsgDTO> getMessagesByCustomerlId(
            @PathVariable("customerId") Integer customerId);

}
