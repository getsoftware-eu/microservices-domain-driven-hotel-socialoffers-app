//package eu.getsoftware.hotelico.infrastructure.hotel.plugin.menu.application.port.out;
//
//public interface MenuService {
//
//    MenuMessageMappedEntity convertFromDTO(ChatMsgDTO msgDTO);
//
//    MenuUserMappedEntity convertFromDTO(CustomerDTO msgDTO);
//
//    ChatMsgDTO convertToDTO(MenuMessageMappedEntity entity);
//
//    CustomerDTO convertToDTO(MenuUserMappedEntity entity);
//
//    MenuMessageMappedEntity createMenuMessageFromDTO(ChatMsgDTO msgDTO);
//
//    MenuUserMappedEntity updateCustomerFromDTO(CustomerDTO customerDTO);
//
//    ChatMsgDTO addMenuMessage(ChatMsgDTO chatMessageDto);
//
//    List<ChatMsgDTO> getMessagesByCustomerId(long customerId, int receiverId);
//
//    Set<CustomerDTO> getNotHotelChatPartners(long customerId, String city, long hotelId);
//
//    Set<CustomerDTO> getAllContactChatPartners(long customerId, String city, long hotelId);
//
//    void markMessageRead(long customerId, String messageIds);
//
//    Set<CustomerDTO> getAllNotChatPartners(long customerId, String city, long hotelId, int pageNumber);
//
//    void markChatRead(long customerId, int senderId, long maxSeenMenuMessageId);
//
//    ChatMsgDTO addUpdateMenuMessage(ChatMsgDTO dto);
//}
