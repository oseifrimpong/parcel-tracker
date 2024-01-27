package com.hrs.parceltracker.controller;


import com.hrs.parceltracker.common.api.ApiCode;
import com.hrs.parceltracker.common.api.ApiResult;
import com.hrs.parceltracker.dto.GuestVO;
import com.hrs.parceltracker.entity.Guest;
import com.hrs.parceltracker.service.IGuestService;
import com.hrs.parceltracker.util.BindingResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "parcel controller", tags = "v1")
@RestController
@RequestMapping("/v1/guest")
@Slf4j
public class GuestController {

    private final IGuestService iGuestService;

    public GuestController(IGuestService iGuestService) {
        this.iGuestService = iGuestService;
    }

    @PostMapping("")
    @ApiOperation(value = "", response = ApiResult.class)
    public ApiResult<Guest> createGuest(@RequestBody @Validated GuestVO guestVO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return ApiResult.fail(ApiCode.BAD_REQUEST.getCode(), BindingResultUtil.getBindingErrorMessage(bindingResult));
        }
        return ApiResult.ok(iGuestService.createGuest(guestVO));
    }
}
