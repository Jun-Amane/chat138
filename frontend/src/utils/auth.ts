export const ADMIN_ROLE = "ROLE_ADMIN";

export const isAdmin = (): boolean => {
    if (typeof window === "undefined") return false;
    const userRoleCode = localStorage.getItem("userRoleCode");
    return userRoleCode === ADMIN_ROLE;
};

export const checkAdminPermission = (): boolean => {
    return isAdmin();
};

export const checkManagerPermission = (): boolean => {
    return isAdmin();
};

export const getCurrentId = () => localStorage.getItem("userId");
