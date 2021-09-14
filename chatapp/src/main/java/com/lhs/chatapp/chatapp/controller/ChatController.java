package com.lhs.chatapp.chatapp.controller;

import com.lhs.chatapp.chatapp.domain.Chat;
import com.lhs.chatapp.chatapp.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
public class ChatController {

    private final ChatRepository chatRepository;

    // 귓속말하기..
    @CrossOrigin
    @GetMapping(value = "/sender/{sender}/receiver/{receiver}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Chat> getMessage(@PathVariable String sender, @PathVariable String receiver){
        return chatRepository.mFindBySender(sender, receiver)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @CrossOrigin
    @PostMapping("/chat")
    public Mono<Chat> setMessage(@RequestBody Chat chat){
        chat.setCreatedAt(LocalDateTime.now());
        return chatRepository.save(chat);
    }

    @CrossOrigin
    @GetMapping(value = "/chat/roomNum/{roomNum}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Chat> findByRoomNum(@PathVariable Integer roomNum){
        return chatRepository.mFindByRoomNum(roomNum).subscribeOn(Schedulers.boundedElastic());
    }

}
