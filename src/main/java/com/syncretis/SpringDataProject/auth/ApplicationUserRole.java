//package com.syncretis.SpringDataProject.auth;
//
//
//import java.util.List;
//
//import static com.syncretis.SpringDataProject.auth.ApplicationUserPermission.*;
//
//
//public enum ApplicationUserRole {
//    PERSON(List.of(PERSON_READ)),
//    ADMIN(List.of(ADMIN_READ,ADMIN_WRITE));
//
//    private final List<ApplicationUserPermission> permissions;
//
//    ApplicationUserRole(List<ApplicationUserPermission> permissions) {
//        this.permissions = permissions;
//    }
//
//    public List<ApplicationUserPermission> getPermissions() {
//        return permissions;
//    }
//}
