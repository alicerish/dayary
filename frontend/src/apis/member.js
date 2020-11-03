import axiosUtils from '@/utils/axios-utils';

export const signUp = async (obj) => {
    return axiosUtils.post(`/signUp`, obj).then((res) => {
        return res.data;
    });
};
