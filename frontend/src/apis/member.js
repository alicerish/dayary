import axiosUtils from '@/utils/axios-utils';

export const getTest = async () => {
    return axiosUtils
        .get(`/api/keywords`)
        .then((res) => {
            return res.data;
        })
        .catch((e) => e);
};

export const signUp = async (obj) => {
    return axiosUtils.post(`/signUp`, obj).then((res) => {
        return res.data;
    });
};
