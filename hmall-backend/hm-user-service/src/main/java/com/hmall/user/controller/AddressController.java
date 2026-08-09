package com.hmall.user.controller;


import com.hmall.common.exception.BadRequestException;
import com.hmall.common.utils.BeanUtils;
import com.hmall.common.utils.CollUtils;
import com.hmall.user.domain.dto.AddressDTO;
import com.hmall.user.domain.po.Address;
import com.hmall.user.service.IAddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 收货地址管理模块
 */
@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
@Tag(name = "收货地址管理接口")
public class AddressController {

    private final IAddressService addressService;

    /**
     * 根据id查询地址
     * @param id 地址id
     * @return 地址信息
     */
    @Operation(summary = "根据id查询地址")
    @GetMapping("{addressId}")
    public AddressDTO findAddressById(@Parameter(description = "地址id") @PathVariable("addressId") Long id) {
        // 1.根据id查询
        Address address = addressService.getById(id);
        // 2.判断当前用户
        //TODO
        /*Long userId = UserContext.getUser();*/
        Long userId = 1L;
        if(!address.getUserId().equals(userId)){
            throw new BadRequestException("地址不属于当前登录用户");
        }
        return BeanUtils.copyBean(address, AddressDTO.class);
    }

    /**
     * 查询当前用户地址列表
     * @return 地址列表
     */
    @Operation(summary = "查询当前用户地址列表")
    @GetMapping
    public List<AddressDTO> findMyAddresses() {
        // 1.查询列表
        //TODO
        /*List<Address> list = addressService.query().eq("user_id", UserContext.getUser()).list();*/
        List<Address> list = addressService.query().eq("user_id", 1L).list();
        // 2.判空
        if (CollUtils.isEmpty(list)) {
            return CollUtils.emptyList();
        }
        // 3.转vo
        return BeanUtils.copyList(list, AddressDTO.class);
    }
}
