import axiosUtils from '@/utils/axios-utils';

export const logIn = async (obj) => {
    return axiosUtils.post(`/logIn`, obj).then((res) => {
        return res.data;
    });
};

export const signUp = async (obj) => {
    return axiosUtils.post(`/signUp`, obj).then((res) => {
        return res.data;
    });
};
