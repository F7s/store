package com.cy.store.service.impl;

import com.cy.store.entity.Address;
import com.cy.store.mapper.AddressMapper;
import com.cy.store.service.IAddressService;
import com.cy.store.service.IDistrictService;
import com.cy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author lizhenghao
 * @create 2022-02-21-11:15
 */
@Service
public class AddressServiceImpl implements IAddressService {
    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private IDistrictService districtService;

    @Value("${user.address.max-count}")
    private  Integer maxCount;

    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        Integer count = addressMapper.countByUid(uid);

        if (count >= maxCount){
            throw new AddressCountLimitException("用户收货地址超出上限");
        }
        
        address.setUid(uid);
        int IsDefault = (count == 0) ? 1 : 0;

        String provinceCode = address.getProvinceCode();
        String cityCode = address.getCityCode();
        String areaCode = address.getAreaCode();

        String provinceName = districtService.getNameByCode(provinceCode);
        String cityName = districtService.getNameByCode(cityCode);
        String areaName = districtService.getNameByCode(areaCode);

        address.setIsDefault(IsDefault);
        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);
        address.setCreatedUser(username);
        address.setModifiedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedTime(new Date());

        Integer rows = addressMapper.insert(address);

        if (rows != 1){
            throw new InsertException("插入用户收货地址时产生未知异常");
        }

    }

    @Override
    public List<Address> getByUid(Integer uid) {
        List<Address> list = addressMapper.findByUid(uid);
        for (Address address : list){
            address.setUid(null);
            address.setProvinceCode(null);
            address.setCityCode(null);
            address.setAreaCode(null);
            address.setTel(null );
            address.setModifiedUser(null);
            address.setModifiedTime(null);
            address.setCreatedTime(null);
            address.setCreatedUser(null);
        }

        return list;
    }

    @Override
    public void setDefault(Integer aid, Integer uid, String username) {
        Address result = addressMapper.findByAid(aid);
        if (result == null){
            throw new AddressNotFoundException("收货地址不存在");
        }

        if (!result.getUid().equals(uid)){
            throw new AccessDeniedException("非法数据访问");
        }

        Integer rows = addressMapper.updateNonDefaultByUid(uid);
        if (rows < 1){
            throw new UpdateException("更新数据产生未知异常");
        }

         rows = addressMapper.updateDefaultByAid(aid, username, new Date());

        if (rows != 1){
            throw new UpdateException("更新数据产生未知异常");
        }
    }

    @Override
    public void deleteAddress(Integer aid, Integer uid, String username) {
        Address result = addressMapper.findByAid(aid);
        if (result == null){
            throw new AddressNotFoundException("收货地址不存在");
        }
        if (!result.getUid().equals(uid)){
            throw new AccessDeniedException("非法数据访问");
        }

        Integer rows;

        if (result.getIsDefault() != 1){
            rows = addressMapper.deleteByAid(aid);

            if (rows != 1){
                throw new DeleteException("删除数据产生未知异常");
            }
        }else {
            rows = addressMapper.deleteByAid(aid);

            if (rows != 1){
                throw new DeleteException("删除数据产生未知异常");
            }

            Integer count = addressMapper.countByUid(uid);
            if (count == 0){
                return;
            }
            Address address = addressMapper.findLastModified(uid);

            rows = addressMapper.updateDefaultByAid(address.getAid(), username, new Date());

            if (rows != 1){
                throw new UpdateException("更新默认状态时候时出现异常");
            }
        }

    }

    @Override
    public Address getByAid(Integer aid, Integer uid) {
        Address result = addressMapper.findByAid(aid);
        if (result == null){
            throw new AddressNotFoundException("收货地址不存在");
        }
        if (!result.getUid().equals(uid)){
            throw new AccessDeniedException("非法数据访问");
        }
        result.setProvinceCode(null);
        result.setCityCode(null);
        result.setAreaCode(null);
        result.setModifiedTime(null);
        result.setModifiedUser(null);
        result.setCreatedUser(null);
        result.setCreatedTime(null);

        return result;

    }
}
