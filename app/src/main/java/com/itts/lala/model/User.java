package com.itts.lala.model;

import com.huawei.hms.support.api.entity.auth.Scope;

import java.util.Set;

public class User {
    private String openId;
    private String uid;
    private String photoUriString;
    private String displayName;
    private String accessToken;
    private String serviceCountryCode;
    private int gender;
    private int status;
    private String serverAuthCode;
    private Set<Scope> grantedScopes;
    private String unionId;
    private String countryCode;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPhotoUriString() {
        return photoUriString;
    }

    public void setPhotoUriString(String photoUriString) {
        this.photoUriString = photoUriString;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getServiceCountryCode() {
        return serviceCountryCode;
    }

    public void setServiceCountryCode(String serviceCountryCode) {
        this.serviceCountryCode = serviceCountryCode;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getServerAuthCode() {
        return serverAuthCode;
    }

    public void setServerAuthCode(String serverAuthCode) {
        this.serverAuthCode = serverAuthCode;
    }

    public Set<Scope> getGrantedScopes() {
        return grantedScopes;
    }

    public void setGrantedScopes(Set<Scope> grantedScopes) {
        this.grantedScopes = grantedScopes;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
