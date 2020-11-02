import axios from 'axios';

const axiosUtils = axios.create({
    baseURL: '',
});

// TODO 요청 시 토큰
axiosUtils.interceptors.request.use();

// TODO 응답 처리
axiosUtils.interceptors.response.use();

export default axiosUtils;
