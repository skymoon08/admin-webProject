package com.oppo.os.ads.admin.system.pojo;

public class LeftMenu {
    private String  id;
    private String type;
    private String leftmenu_name;
    private String sort;
    private String url;
    private String icon;
    private Handles handles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLeftmenu_name() {
        return leftmenu_name;
    }

    public void setLeftmenu_name(String leftmenu_name) {
        this.leftmenu_name = leftmenu_name;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Handles getHandles() {
        return handles;
    }

    public void setHandles(Handles handles) {
        this.handles = handles;
    }
}
