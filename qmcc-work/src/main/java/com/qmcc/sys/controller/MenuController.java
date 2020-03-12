package com.qmcc.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qmcc.sys.common.*;
import com.qmcc.sys.domain.Permission;
import com.qmcc.sys.domain.User;
import com.qmcc.sys.service.PermissionService;
import com.qmcc.sys.vo.PermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("loadIndexLeftMenuJson")
    public DataGridView loadIndexLeftMenuJson(PermissionVo permissionVo){
        // 查询所有菜单
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        // 设置只能查询菜单
        queryWrapper.eq("type", Constast.TYPE_MNEU);
        queryWrapper.eq("available", Constast.AVAILABLE_TRUE);

        User user = (User) WebUtils.getSession().getAttribute("user");
        List<Permission> list = null;
        if (user.getType() == Constast.USER_TYPE_SUPER) {//登录0代表超级用户
            list = permissionService.list(queryWrapper);
        } else {
            // 根据用户id + 角色 + 查询
            list = permissionService.list(queryWrapper);
        }
        List<TreeNode> treeNodes = new ArrayList<>();
        for (Permission p : list){
            Integer id = p.getId();
            Integer pid = p.getPid();
            String title = p.getTitle();
            String icon = p.getIcon();
            String href = p.getHref();
            Boolean spread = p.getOpen().equals(Constast.OPEN_TRUE);
            treeNodes.add(new TreeNode(id,pid,title,icon,href,spread));
        }
        List<TreeNode> treeNodeList = TreeNodeBuilder.build(treeNodes, 1);
        return new DataGridView(treeNodeList);
    }
}
