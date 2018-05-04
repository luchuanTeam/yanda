package com.yanda.entity.generated;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "t_role_info")
public class RoleInfo implements Serializable {
    /**
     * 角色ID
     */
    @Id
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 角色英文名
     */
    private String ename;

    /**
     * 角色中文名
     */
    private String cname;

    /**
     * 角色描述
     */
    @Column(name = "role_desc")
    private String roleDesc;

    private static final long serialVersionUID = 1L;

    public RoleInfo(Integer roleId, String ename, String cname, String roleDesc) {
        this.roleId = roleId;
        this.ename = ename;
        this.cname = cname;
        this.roleDesc = roleDesc;
    }

    public RoleInfo() {
        super();
    }

    /**
     * 获取角色ID
     *
     * @return role_id - 角色ID
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置角色ID
     *
     * @param roleId 角色ID
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取角色英文名
     *
     * @return ename - 角色英文名
     */
    public String getEname() {
        return ename;
    }

    /**
     * 设置角色英文名
     *
     * @param ename 角色英文名
     */
    public void setEname(String ename) {
        this.ename = ename == null ? null : ename.trim();
    }

    /**
     * 获取角色中文名
     *
     * @return cname - 角色中文名
     */
    public String getCname() {
        return cname;
    }

    /**
     * 设置角色中文名
     *
     * @param cname 角色中文名
     */
    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    /**
     * 获取角色描述
     *
     * @return role_desc - 角色描述
     */
    public String getRoleDesc() {
        return roleDesc;
    }

    /**
     * 设置角色描述
     *
     * @param roleDesc 角色描述
     */
    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc == null ? null : roleDesc.trim();
    }

    public enum Col {
        roleId("role_id"),
        ename("ename"),
        cname("cname"),
        roleDesc("role_desc");

        private final String column;

        public String value() {
            return this.column;
        }

        public String getValue() {
            return this.column;
        }

        Col(String column) {
            this.column = column;
        }

        public String desc() {
            return this.column + " DESC";
        }

        public String asc() {
            return this.column + " ASC";
        }
    }
}