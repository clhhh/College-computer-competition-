package com.example.demo.result;



import com.example.demo.pojo.Menu;
import com.example.demo.pojo.Userdomain.CurrentUser;
import com.example.demo.pojo.Userdomain.Permission;
import com.example.demo.pojo.Userdomain.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@ToString
public class ResultToken {

    private String username;
    private int uid;
    private String token;
    private Map<String, Object> Roles = new HashMap<>();
    private Set<Menu> Menus = new HashSet<>();
    public ResultToken(CurrentUser cuer, Set<Menu> menus, String token){
        this.username = cuer.getUsername();
        this.uid = cuer.getPid();
        this.Menus = menus;
        this.token = token;
        Set<Permission> Permissions = new HashSet<>();
        Set<String> roleNames = new HashSet<>();

        for (Role role : cuer.getRoles()) {
            roleNames.add(role.getRname());
            for (Permission permission : role.getPermissions()) {
                Permissions.add(permission);
            }
        }
        this.Roles.put("RoleName",roleNames);
        this.Roles.put("Permission",Permissions);
    }
    public ResultToken(Set<Menu> menus,String token){
        this.Menus = menus;
        this.token = token;
    }
}
