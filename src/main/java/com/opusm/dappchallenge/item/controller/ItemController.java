package com.opusm.dappchallenge.item.controller;

import com.opusm.dappchallenge.common.constant.Code;
import com.opusm.dappchallenge.common.dto.DataResponseDto;
import com.opusm.dappchallenge.config.GeneralException;
import com.opusm.dappchallenge.item.domain.Item;
import com.opusm.dappchallenge.item.dto.ItemRes;
import com.opusm.dappchallenge.item.dto.ItemSaveReq;
import com.opusm.dappchallenge.item.repository.ItemRepository;
import com.opusm.dappchallenge.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/items")
public class ItemController {
    private final ItemService itemService;
    private final ItemRepository itemRepository;

    @PostMapping(path = "/item")
    public DataResponseDto<Object> saveOne(@Validated ItemSaveReq itemSaveReq) throws Exception {
        Item item = itemRepository.save(itemSaveReq.toEntity());
        return DataResponseDto.of(new ItemRes(item));
    }

    @GetMapping(path = "/{itemId}")
    public DataResponseDto<Object> findById(@PathVariable("itemId") Long itemId) throws Exception {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new GeneralException(Code.BAD_REQUEST, "아이템이 없습니다."));
        return DataResponseDto.of(new ItemRes(item));
    }
}
