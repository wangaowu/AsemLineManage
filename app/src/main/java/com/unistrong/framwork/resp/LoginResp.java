package com.unistrong.framwork.resp;

import java.util.List;

/**
 * 登陆返回
 */
public class LoginResp {
    private int code;
    private String msg;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {

        private int roleId;
        private String roleName;
        private String token;
        private int tokenExpires;
        private String tokenTime;
        private String userBirth;
        private String userCommunityCode;
        private String userCommunityName;
        private int userId;
        private String userIdcard;
        private String userName;
        private String userOfficeCode;
        private String userOfficeName;
        private String userPoliceno;
        private String userRealname;
        private String userSex;
        private String userTelephone;
        private String userType;
        private List<MenuListBean> menuList;

        public int getRoleId() {
            return roleId;
        }

        public void setRoleId(int roleId) {
            this.roleId = roleId;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getTokenExpires() {
            return tokenExpires;
        }

        public void setTokenExpires(int tokenExpires) {
            this.tokenExpires = tokenExpires;
        }

        public String getTokenTime() {
            return tokenTime;
        }

        public void setTokenTime(String tokenTime) {
            this.tokenTime = tokenTime;
        }

        public String getUserBirth() {
            return userBirth;
        }

        public void setUserBirth(String userBirth) {
            this.userBirth = userBirth;
        }

        public String getUserCommunityCode() {
            return userCommunityCode;
        }

        public void setUserCommunityCode(String userCommunityCode) {
            this.userCommunityCode = userCommunityCode;
        }

        public String getUserCommunityName() {
            return userCommunityName;
        }

        public void setUserCommunityName(String userCommunityName) {
            this.userCommunityName = userCommunityName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserIdcard() {
            return userIdcard;
        }

        public void setUserIdcard(String userIdcard) {
            this.userIdcard = userIdcard;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserOfficeCode() {
            return userOfficeCode;
        }

        public void setUserOfficeCode(String userOfficeCode) {
            this.userOfficeCode = userOfficeCode;
        }

        public String getUserOfficeName() {
            return userOfficeName;
        }

        public void setUserOfficeName(String userOfficeName) {
            this.userOfficeName = userOfficeName;
        }

        public String getUserPoliceno() {
            return userPoliceno;
        }

        public void setUserPoliceno(String userPoliceno) {
            this.userPoliceno = userPoliceno;
        }

        public String getUserRealname() {
            return userRealname;
        }

        public void setUserRealname(String userRealname) {
            this.userRealname = userRealname;
        }

        public String getUserSex() {
            return userSex;
        }

        public void setUserSex(String userSex) {
            this.userSex = userSex;
        }

        public String getUserTelephone() {
            return userTelephone;
        }

        public void setUserTelephone(String userTelephone) {
            this.userTelephone = userTelephone;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public List<MenuListBean> getMenuList() {
            return menuList;
        }

        public void setMenuList(List<MenuListBean> menuList) {
            this.menuList = menuList;
        }

        public static class MenuListBean {
            private String menuComponent;
            private String menuDesc;
            private String menuIcon;
            private int menuId;
            private int menuLevel;
            private String menuName;
            private int menuPid;
            private int menuSort;
            private int menuType;
            private String menuUrl;

            public String getMenuComponent() {
                return menuComponent;
            }

            public void setMenuComponent(String menuComponent) {
                this.menuComponent = menuComponent;
            }

            public String getMenuDesc() {
                return menuDesc;
            }

            public void setMenuDesc(String menuDesc) {
                this.menuDesc = menuDesc;
            }

            public String getMenuIcon() {
                return menuIcon;
            }

            public void setMenuIcon(String menuIcon) {
                this.menuIcon = menuIcon;
            }

            public int getMenuId() {
                return menuId;
            }

            public void setMenuId(int menuId) {
                this.menuId = menuId;
            }

            public int getMenuLevel() {
                return menuLevel;
            }

            public void setMenuLevel(int menuLevel) {
                this.menuLevel = menuLevel;
            }

            public String getMenuName() {
                return menuName;
            }

            public void setMenuName(String menuName) {
                this.menuName = menuName;
            }

            public int getMenuPid() {
                return menuPid;
            }

            public void setMenuPid(int menuPid) {
                this.menuPid = menuPid;
            }

            public int getMenuSort() {
                return menuSort;
            }

            public void setMenuSort(int menuSort) {
                this.menuSort = menuSort;
            }

            public int getMenuType() {
                return menuType;
            }

            public void setMenuType(int menuType) {
                this.menuType = menuType;
            }

            public String getMenuUrl() {
                return menuUrl;
            }

            public void setMenuUrl(String menuUrl) {
                this.menuUrl = menuUrl;
            }
        }
    }
}
