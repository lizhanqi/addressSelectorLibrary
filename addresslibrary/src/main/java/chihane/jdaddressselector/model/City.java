package chihane.jdaddressselector.model;

import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

public class City extends BaseModel {
    private String id;
    private String areaName;
    private List<County> children;
    private String Fid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public List<County> getChildren() {
        return children;
    }

    public void setChildren(List<County> children) {
        this.children = children;
    }

    public String getFid() {
        return Fid;
    }

    public void setFid(String fid) {
        Fid = fid;
    }
}