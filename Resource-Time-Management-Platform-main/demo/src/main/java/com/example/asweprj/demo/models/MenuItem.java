package com.example.asweprj.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;

@Entity
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String url;
    private Integer sortOrder;


    public MenuItem() {
    }

    public MenuItem(Long id, String name, String url, Integer sortOrder) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.sortOrder = sortOrder;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSortOrder() {
        return this.sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public MenuItem id(Long id) {
        setId(id);
        return this;
    }

    public MenuItem name(String name) {
        setName(name);
        return this;
    }

    public MenuItem url(String url) {
        setUrl(url);
        return this;
    }

    public MenuItem sortOrder(Integer sortOrder) {
        setSortOrder(sortOrder);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof MenuItem)) {
            return false;
        }
        MenuItem menuItem = (MenuItem) o;
        return Objects.equals(id, menuItem.id) && Objects.equals(name, menuItem.name) && Objects.equals(url, menuItem.url) && Objects.equals(sortOrder, menuItem.sortOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, url, sortOrder);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", url='" + getUrl() + "'" +
            ", sortOrder='" + getSortOrder() + "'" +
            "}";
    }
}
