package co.in.sagarkale.methodsecurity.entity;

import java.util.Set;

public enum Role {
    USER(Set.of(Permissions.READ_PERMISSION)),
    ADMIN(Set.of(Permissions.READ_PERMISSION, Permissions.WRITE_PERMISSION, Permissions.DELETE_PERMISSION));

    private final Set<Permissions> permissions;

    Role(Set<Permissions> permissions) {
        this.permissions = permissions;
    }

    public Set<Permissions> getPermissions() {
        return permissions;
    }
}
