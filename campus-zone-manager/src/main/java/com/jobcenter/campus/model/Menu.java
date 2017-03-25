package com.jobcenter.campus.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.jobcenter.campus.common.utils.JsonMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by xiayun on 25/3/17.
 */
public class Menu {

    private String name;
    private String url;
    private Boolean isDisplay=false;
    private Boolean isPermission=false;
    private Vector<Menu> children;

    static public final Enumeration<Menu> EMPTY_ENUMERATION
            = new Enumeration<Menu>() {
        public boolean hasMoreElements() { return false; }
        public Menu nextElement() {
            throw new NoSuchElementException("No more elements");
        }
    };

    public Menu(){

    }

    public Menu(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public Menu(String name, String url, List<Menu> children) {
        this.name = name;
        this.url = url;
        this.children = new Vector<Menu>(children);
    }
    public Menu(String name, String url, Vector<Menu> children) {
        this.name = name;
        this.url = url;
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public Vector<Menu> getChildren() {
        return children;
    }

    public boolean hasChildren() {
        return !(children == null || children.isEmpty());
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Enumeration<Menu> children(){
        if (children == null) {
            return EMPTY_ENUMERATION;
        } else {
            return children.elements();
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("\nMenu:");
        sb.append(name).append(":").append(url);
        if(children!=null && !children.isEmpty()){
            sb.append("  ").append("  children:").append(children);
        }
        return sb.toString();
    }


    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Enumeration<Menu> breadthFirstEnumeration() {
        return new BreadthFirstEnumeration(this);
    }

    private class BreadthFirstEnumeration implements Enumeration<Menu>{
        protected Queue queue;

        public BreadthFirstEnumeration(Menu rootNode) {
            Vector v = new Vector(1);
            v.add(rootNode);
            queue = new LinkedList();
            queue.offer(v.elements());
        }
        @Override
        public boolean hasMoreElements() {
            return (!queue.isEmpty() &&
                    ((Enumeration)queue.peek()).hasMoreElements());
        }

        public Menu nextElement() {
            Enumeration iter = (Enumeration)queue.peek();
            Menu node = (Menu)iter.nextElement();
            Enumeration children = node.children();

            if (!iter.hasMoreElements()) {
                queue.remove();
            }
            if (children.hasMoreElements()) {
                queue.offer(children);
            }
            return node;
        }
    }


    public static void main(String[] args) throws IOException {
        Menu m = new Menu("test1","url2");
        String file = "/Users/xiayun/workspace/campus-zone/campus-zone-service/src/main/resources/config/menu.json";
        String s = FileUtils.readFileToString(new File(file));
        System.out.println(s);
        List<Menu> list = JsonMapper.buildNonDefaultBinder().fromJson(s, new TypeReference<List<Menu>>(){});

        System.out.println(list);
    }

    public Boolean getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(Boolean isDisplay) {
        this.isDisplay = isDisplay;
    }

    public Boolean getIsPermission() {
        return isPermission;
    }

    public void setIsPermission(Boolean isPermission) {
        this.isPermission = isPermission;
    }
}
