package com.qmcc.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qmcc.sys.common.DataGridView;
import com.qmcc.sys.common.ResultObj;
import com.qmcc.sys.domain.Loginfo;
import com.qmcc.sys.service.LoginfoService;
import com.qmcc.sys.vo.LoginfoVo;
import com.qmcc.sys.vo.LoginfoWeVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author nyc
 * @since 2020-03-12
 */
@RestController
@RequestMapping("loginfo")
public class LoginfoController {

    @Autowired
    private LoginfoService loginfoService;

    /**
     * 全查询
     */
    @RequestMapping("loadAllLoginfo")
    public DataGridView loadAllLoginfo(LoginfoVo loginfoVo){
        IPage<Loginfo> page = new Page<>(loginfoVo.getPage(), loginfoVo.getLimit());
        QueryWrapper<Loginfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(loginfoVo.getLoginname()),"loginname", loginfoVo.getLoginname());
        queryWrapper.like(StringUtils.isNotBlank(loginfoVo.getLoginip()),"loginip", loginfoVo.getLoginip());
        queryWrapper.ge(loginfoVo.getStartTime()!=null, "logintime", loginfoVo.getStartTime());
        queryWrapper.le(loginfoVo.getEndTime()!=null, "logintime", loginfoVo.getEndTime());
        queryWrapper.orderByDesc("logintime");
        this.loginfoService.page(page, queryWrapper);

        return new DataGridView(page.getTotal(), page.getRecords());
    }

    /**
     * 微信小程序全查询
     */
    @RequestMapping("weloadAllLoginfo")
    public DataGridView we1loadAllLoginfo(LoginfoVo loginfoVo){
        List<Loginfo> loginfoList = new ArrayList<>();
        QueryWrapper<Loginfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("logintime");
        loginfoList = this.loginfoService.list(queryWrapper);


        return new DataGridView(loginfoList);
    }

    /**
     * 删除
     */
    @RequestMapping("deleteLoginfo")
    public ResultObj deleteLoginfo(Integer id){
        try {
            this.loginfoService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除
     */
    @RequestMapping("batchDeleteLoginfo")
    public ResultObj batchDeleteLoginfo(LoginfoVo loginfoVo){
        try {
            Collection<Serializable> idList = new ArrayList<Serializable>();
            for (Integer id : loginfoVo.getIds()){
                idList.add(id);
            }
            this.loginfoService.removeByIds(idList);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

}

